<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="塔机型号" prop="towerModel">
        <el-input
          v-model="queryParams.towerModel"
          placeholder="请输入塔机型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="塔机高度(M)" prop="towerHeight">
        <el-input
          v-model="queryParams.towerHeight"
          placeholder="请输入塔机高度(M)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="载重(KG)" prop="maxLoad">
        <el-input
          v-model="queryParams.maxLoad"
          placeholder="请输入载重(KG)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="大臂角度°" prop="jibAngle">
        <el-input
          v-model="queryParams.jibAngle"
          placeholder="请输入大臂角度°"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="力矩" prop="moment">
        <el-input
          v-model="queryParams.moment"
          placeholder="请输入力矩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="风速(M/S)" prop="windSpeed">
        <el-input
          v-model="queryParams.windSpeed"
          placeholder="请输入风速(M/S)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="塔机倾斜角度" prop="tiltAngle">
        <el-input
          v-model="queryParams.tiltAngle"
          placeholder="请输入塔机倾斜角度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小车位置" prop="cartLocation">
        <el-input
          v-model="queryParams.cartLocation"
          placeholder="请输入小车位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="制造商">
        <el-input
          v-model="queryParams['towerFactory.factoryName']"
          placeholder="请输入制造商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人员" prop="operate">
        <el-input
          v-model="queryParams.operate"
          placeholder="请输入操作人员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属工地">
        <el-input
          v-model="queryParams['projectSite.siteName']"
          placeholder="请输入所属工地"
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
          v-hasPermi="['project:tower:add']"
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
          v-hasPermi="['project:tower:edit']"
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
          v-hasPermi="['project:tower:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:tower:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="towerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="塔机ID" align="center" prop="towerId" />
      <el-table-column label="塔机型号" align="center" prop="towerModel" />
      <el-table-column label="塔机高度(M)" align="center" prop="towerHeight" />
      <el-table-column label="载重(KG)" align="center" prop="maxLoad" />
      <el-table-column label="大臂角度°" align="center" prop="jibAngle" />
      <el-table-column label="力矩" align="center" prop="moment" />
      <el-table-column label="风速(M/S)" align="center" prop="windSpeed" />
      <el-table-column label="塔机倾斜角度" align="center" prop="tiltAngle" />
      <el-table-column label="小车位置" align="center" prop="cartLocation" />
      <el-table-column label="制造商" align="center" prop="towerFactory.factoryName" />
      <el-table-column label="制造商编码" align="center" prop="towerFactory.factoryCode" />
      <el-table-column label="购买日期" align="center" prop="purchaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['project:tower:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:tower:remove']"
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

    <!-- 添加或修改塔机管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="塔机型号" prop="towerModel">
          <el-input v-model="form.towerModel" placeholder="请输入塔机型号" />
        </el-form-item>
        <el-form-item label="塔机高度(M)" prop="towerHeight">
          <el-input v-model="form.towerHeight" placeholder="请输入塔机高度(M)" />
        </el-form-item>
        <el-form-item label="载重(KG)" prop="maxLoad">
          <el-input v-model="form.maxLoad" placeholder="请输入载重(KG)" />
        </el-form-item>
        <el-form-item label="大臂角度°" prop="jibAngle">
          <el-input v-model="form.jibAngle" placeholder="请输入大臂角度°" />
        </el-form-item>
        <el-form-item label="力矩" prop="moment">
          <el-input v-model="form.moment" placeholder="请输入力矩" />
        </el-form-item>
        <el-form-item label="风速(M/S)" prop="windSpeed">
          <el-input v-model="form.windSpeed" placeholder="请输入风速(M/S)" />
        </el-form-item>
        <el-form-item label="塔机倾斜角度" prop="tiltAngle">
          <el-input v-model="form.tiltAngle" placeholder="请输入塔机倾斜角度" />
        </el-form-item>
        <el-form-item label="小车位置" prop="cartLocation">
          <el-input v-model="form.cartLocation" placeholder="请输入小车位置" />
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
        <el-form-item label="操作人员" prop="operate">
          <el-input v-model="form.operate" placeholder="请输入操作人员" />
        </el-form-item>
        <el-form-item label="所属工地" prop="siteId">
          <el-select v-model="form.siteId " placeholder="请输入所属工地">
            <el-option v-for="item in siteList"
                       :key="item.siteId"
                       :label="item.siteName"
                       :value="item.siteId"
            ></el-option>
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
import { listTower, getTower, delTower, addTower, updateTower } from "@/api/project/tower";
import {listTower_factory} from "@/api/project/tower_factory";
import {listSite} from "@/api/project/site";

export default {
  name: "Tower",
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
      // 总条数
      total: 0,
      // 塔机管理表格数据
      towerList: [],
      //塔机制造商数据
      factoryList:[],
      //工地信息
      siteList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        towerModel: null,
        towerHeight: null,
        maxLoad: null,
        jibAngle: null,
        moment: null,
        windSpeed: null,
        tiltAngle: null,
        cartLocation: null,
        factoryId: null,
        operate: null,
        siteId: null,
        status: null,
        'towerFactory.factoryName':null,
        'projectSite.siteName':null
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
    this.getSiteList();
    this.getFactoryList()
  },
  methods: {
    /** 查询塔机管理列表 */
    getList() {
      this.loading = true;
      listTower(this.queryParams).then(response => {
        this.towerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getFactoryList() {
      listTower_factory().then(response => {
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
        towerId: null,
        towerModel: null,
        towerHeight: null,
        maxLoad: null,
        jibAngle: null,
        moment: null,
        windSpeed: null,
        tiltAngle: null,
        cartLocation: null,
        factoryId: null,
        purchaseDate: null,
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
      this.ids = selection.map(item => item.towerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加塔机管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const towerId = row.towerId || this.ids
      getTower(towerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改塔机管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.towerId != null) {
            updateTower(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTower(this.form).then(response => {
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
      const towerIds = row.towerId || this.ids;
      this.$modal.confirm('是否确认删除塔机管理编号为"' + towerIds + '"的数据项？').then(function() {
        return delTower(towerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/tower/export', {
        ...this.queryParams
      }, `tower_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
