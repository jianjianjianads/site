<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="异常塔机id" prop="towerId">
        <el-input
          v-model="queryParams.towerId"
          placeholder="请输入异常塔机id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="异常属性名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入异常属性名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="异常值" prop="value">
        <el-input
          v-model="queryParams.value"
          placeholder="请输入异常值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="异常阈值" prop="max">
        <el-input
          v-model="queryParams.max"
          placeholder="请输入异常阈值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="异常时间" prop="errorTime">
        <el-date-picker clearable
          v-model="queryParams.errorTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择异常时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:error_tower:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:error_tower:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:error_tower:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:error_tower:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="error_towerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="异常id" align="center" prop="errorId" />
      <el-table-column label="异常塔机id" align="center" prop="towerId" />
      <el-table-column label="异常属性名" align="center" prop="name" />
      <el-table-column label="异常值" align="center" prop="value" />
      <el-table-column label="异常阈值" align="center" prop="max" />
      <el-table-column label="异常时间" align="center" prop="errorTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.errorTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:error_tower:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:error_tower:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改异常信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="异常塔机id" prop="towerId">
          <el-input v-model="form.towerId" placeholder="请输入异常塔机id" />
        </el-form-item>
        <el-form-item label="异常属性名" prop="name">
          <el-input v-model="form.name" placeholder="请输入异常属性名" />
        </el-form-item>
        <el-form-item label="异常值" prop="value">
          <el-input v-model="form.value" placeholder="请输入异常值" />
        </el-form-item>
        <el-form-item label="异常阈值" prop="max">
          <el-input v-model="form.max" placeholder="请输入异常阈值" />
        </el-form-item>
        <el-form-item label="异常时间" prop="errorTime">
          <el-date-picker clearable
            v-model="form.errorTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择异常时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listError_tower, getError_tower, delError_tower, addError_tower, updateError_tower } from "@/api/project/error_tower";

export default {
  name: "Error_tower",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 异常信息管理表格数据
      error_towerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        towerId: null,
        name: null,
        value: null,
        max: null,
        errorTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        errorTime: [
          { required: true, message: "异常时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询异常信息管理列表 */
    getList() {
      this.loading = true;
      listError_tower(this.queryParams).then(response => {
        this.error_towerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        errorId: null,
        towerId: null,
        name: null,
        value: null,
        max: null,
        errorTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.errorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加异常信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const errorId = row.errorId || this.ids
      getError_tower(errorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改异常信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.errorId != null) {
            updateError_tower(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addError_tower(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const errorIds = row.errorId || this.ids;
      this.$modal.confirm('是否确认删除异常信息管理编号为"' + errorIds + '"的数据项？').then(function() {
        return delError_tower(errorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/error_tower/export', {
        ...this.queryParams
      }, `error_tower_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
