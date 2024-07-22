<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="siteName">
        <el-input
          v-model="queryParams.siteName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目进度%" prop="progress">
        <el-input
          v-model="queryParams.progress"
          placeholder="请输入项目进度%"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="承包商" >
        <el-input
          v-model="queryParams['siteContractor.contractorName']"
          placeholder="请输入承包商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目经理" prop="manager">
        <el-input
          v-model="queryParams.manager"
          placeholder="请输入项目经理"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择项目类型" clearable>
          <el-option
            v-for="dict in dict.type.project_site_st"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="项目当前状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择项目当前状态" clearable>
          <el-option
            v-for="dict in dict.type.project_site_ss"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['project:site:add']"
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
          v-hasPermi="['project:site:edit']"
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
          v-hasPermi="['project:site:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:site:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="siteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工地项目ID" align="center" prop="siteId" width="80"/>
      <el-table-column label="项目名称" align="center" prop="siteName" min-width="150"/>
      <el-table-column label="项目描述" align="center" prop="description" />
      <el-table-column label="项目进度%" align="center" prop="progress" />
      <el-table-column label="承包商" align="center" prop="siteContractor.contractorName" min-width="200"/>
      <el-table-column label="承包商编码" align="center" prop="siteContractor.contractorCode" />
      <el-table-column label="项目经理" align="center" prop="manager" />
      <el-table-column label="开始日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.project_site_st" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="项目预算" align="center" prop="budget" />
      <el-table-column label="项目地点" align="center" prop="location" />
      <el-table-column label="项目当前状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.project_site_ss" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:site:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:site:remove']"
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

    <!-- 添加或修改工地项目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="siteName">
          <el-input v-model="form.siteName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入项目描述" />
        </el-form-item>
        <el-form-item label="项目进度%" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入项目进度%" />
        </el-form-item>
        <el-form-item label="承包商" prop="contractorId">
          <el-select v-model="form.contractorId" placeholder="请选择承包商">
            <el-option
              v-for="item in contractorList"
              :key="item.contractorId"
              :label="item.contractorName"
              :value="item.contractorId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目经理" prop="manager">
          <el-input v-model="form.manager" placeholder="请输入项目经理" />
        </el-form-item>
        <el-form-item label="开始日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="endDate">
          <el-date-picker clearable
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目类型">
          <el-radio-group v-model="form.type">
            <el-radio
              v-for="dict in dict.type.project_site_st"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="项目预算" prop="budget">
          <el-input v-model="form.budget" placeholder="请输入项目预算" />
        </el-form-item>
        <el-form-item label="项目地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入项目地点" />
        </el-form-item>
        <el-form-item label="项目当前状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.project_site_ss"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listSite, getSite, delSite, addSite, updateSite } from "@/api/project/site";
import { listSite_contractor } from "@/api/project/site_contractor";


export default {
  name: "Site",
  dicts: ['project_site_st', 'project_site_ss'],
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
      // 工地项目表格数据
      siteList: [],
      //承包商数据
      contractorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteName: null,
        progress: null,
        contractorId: null,
        manager: null,
        type: null,
        status: null,
        'siteContractor.contractorName':null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getContractorList();
  },
  methods: {
    /** 查询工地项目列表 */
    getList() {
      this.loading = true;
      listSite(this.queryParams).then(response => {
        this.siteList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getContractorList() {
      listSite_contractor().then(response => {
        this. contractorList = response.rows;
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
        siteId: null,
        siteName: null,
        description: null,
        progress: null,
        contractorId: null,
        manager: null,
        startDate: null,
        endDate: null,
        type: "0",
        budget: null,
        location: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        'siteContractor.contractorName':null
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
      this.ids = selection.map(item => item.siteId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工地项目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const siteId = row.siteId || this.ids
      getSite(siteId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工地项目";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.siteId != null) {
            updateSite(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSite(this.form).then(response => {
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
      const siteIds = row.siteId || this.ids;
      this.$modal.confirm('是否确认删除工地项目编号为"' + siteIds + '"的数据项？').then(function() {
        return delSite(siteIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/site/export', {
        ...this.queryParams
      }, `site_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
