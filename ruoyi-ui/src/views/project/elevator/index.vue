<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="电梯ID" prop="elevatorId">
        <el-input
          v-model="queryParams.elevatorId"
          placeholder="请输入电梯ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电梯型号" prop="elevatorModel">
        <el-input
          v-model="queryParams.elevatorModel"
          placeholder="请输入电梯型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="制造商" >
        <el-input
          v-model="queryParams['elevatorFactory.factoryName']"
          placeholder="请输入制造商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属工地项目" >
        <el-input
          v-model="queryParams['projectSite.siteName']"
          placeholder="请输入所属工地"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择当前状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
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
          v-hasPermi="['project:elevator:add']"
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
          v-hasPermi="['project:elevator:edit']"
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
          v-hasPermi="['project:elevator:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:elevator:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="elevatorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="电梯ID" align="center" prop="elevatorId" width="80"/>
      <el-table-column label="电梯型号" align="center" prop="elevatorModel" />
      <el-table-column label="电梯速度(M/S)" align="center" prop="elevatorSpeed" />
      <el-table-column label="最大承载重量(KG)" align="center" prop="maxLoad" />
      <el-table-column label="制造商名称" align="center" prop="elevatorFactory.factoryName" min-width="200"/>
      <el-table-column label="制造商编码" align="center" prop="elevatorFactory.factoryCode" />
      <el-table-column label="购买日期" align="center" prop="purchaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="使用情况记录" align="center" prop="usageLog" />
      <el-table-column label="位置" align="center" prop="location" />
      <el-table-column label="操作人员" align="center" prop="operate" />
      <el-table-column label="所属工地" align="center" prop="projectSite.siteName" />
      <el-table-column label="当前状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:elevator:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:elevator:remove']"
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

    <!-- 添加或修改工程电梯管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="电梯型号" prop="elevatorModel">
          <el-input v-model="form.elevatorModel" placeholder="请输入电梯型号" />
        </el-form-item>
        <el-form-item label="电梯速度(M/S)" prop="elevatorSpeed">
          <el-input v-model="form.elevatorSpeed" placeholder="请输入电梯速度(M/S)" />
        </el-form-item>
        <el-form-item label="最大承载重量(KG)" prop="maxLoad">
          <el-input v-model="form.maxLoad" placeholder="请输入最大承载重量(KG)" />
        </el-form-item>
        <el-form-item label="制造商" prop="factoryId">
          <el-select v-model="form.factoryId " placeholder="请选择制造商">
            <el-option v-for="item in factoryList"
                       :key="item.factoryId"
                       :label="item.factoryName"
                       :value="item.factoryId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="购买日期" prop="purchaseDate">
          <el-date-picker clearable
            v-model="form.purchaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择购买日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="使用情况记录" prop="usageLog">
          <el-input v-model="form.usageLog" placeholder="请输入使用情况记录" />
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入位置" />
        </el-form-item>
        <el-form-item label="操作人员" prop="operate">
          <el-input v-model="form.operate" placeholder="请输入操作人员" />
        </el-form-item>
        <el-form-item label="所属工地" prop="siteId">
          <el-select v-model="form.siteId" placeholder="请选择所属工地">
            <el-option v-for="item in siteList"
                       :key="item.siteId"
                       :label="item.siteName"
                       :value="item.siteId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="当前状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listElevator, getElevator, delElevator, addElevator, updateElevator } from "@/api/project/elevator";
import { listElevator_factory } from "@/api/project/elevator_factory";
import {listSite} from "@/api/project/site";

export default {
  name: "Elevator",
  dicts: ['sys_normal_disable'],
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
      // 电梯生产厂家数据
      factoryList:[],
      // 总条数
      total: 0,
      // 工程电梯管理表格数据
      elevatorList: [],
      //工地项目数据
      siteList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        elevatorId: null,
        elevatorModel: null,
        factory_id: null,
        'elevatorFactory.factoryName':null,
        'projectSite.siteName':null,
        location: null,
        siteId: null,
        status: null,
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
    this.getFactoryList();
    this.getSiteList();
  },
  methods: {
    /** 查询工程电梯管理列表 */
    getList() {
      this.loading = true;
      listElevator(this.queryParams).then(response => {
        this.elevatorList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getFactoryList() {
      listElevator_factory().then(response => {
        this.factoryList = response.rows;
      });
    },
    getSiteList() {
      listSite().then(response => {
        this.siteList = response.rows;
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
        elevatorId: null,
        elevatorModel: null,
        elevatorSpeed: null,
        maxLoad: null,
        factory_id: null,
        purchaseDate: null,
        usageLog: null,
        location: null,
        operate: null,
        siteId: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.elevatorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工程电梯管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const elevatorId = row.elevatorId || this.ids
      getElevator(elevatorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工程电梯管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.elevatorId != null) {
            updateElevator(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addElevator(this.form).then(response => {
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
      const elevatorIds = row.elevatorId || this.ids;
      this.$modal.confirm('是否确认删除工程电梯管理编号为"' + elevatorIds + '"的数据项？').then(function() {
        return delElevator(elevatorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/elevator/export', {
        ...this.queryParams
      }, `elevator_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
