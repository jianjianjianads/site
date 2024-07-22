<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工地id" prop="siteId">
        <el-input
          v-model="queryParams.siteId"
          placeholder="请输入工地id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="风速" prop="windSpeed">
        <el-input
          v-model="queryParams.windSpeed"
          placeholder="请输入风速"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="温度" prop="temperature">
        <el-input
          v-model="queryParams.temperature"
          placeholder="请输入温度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="pm2.5浓度" prop="pm25">
        <el-input
          v-model="queryParams.pm25"
          placeholder="请输入pm2.5浓度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="pm10浓度" prop="pm10">
        <el-input
          v-model="queryParams.pm10"
          placeholder="请输入pm10浓度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="噪音分贝" prop="noise">
        <el-input
          v-model="queryParams.noise"
          placeholder="请输入噪音分贝"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="湿度" prop="humidity">
        <el-input
          v-model="queryParams.humidity"
          placeholder="请输入湿度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期" prop="weatherDate">
        <el-date-picker clearable
          v-model="queryParams.weatherDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择日期">
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
          v-hasPermi="['project:weather:add']"
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
          v-hasPermi="['project:weather:edit']"
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
          v-hasPermi="['project:weather:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:weather:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="weatherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工地id" align="center" prop="siteId" />
      <el-table-column label="风速" align="center" prop="windSpeed" />
      <el-table-column label="温度" align="center" prop="temperature" />
      <el-table-column label="pm2.5浓度" align="center" prop="pm25" />
      <el-table-column label="pm10浓度" align="center" prop="pm10" />
      <el-table-column label="噪音分贝" align="center" prop="noise" />
      <el-table-column label="湿度" align="center" prop="humidity" />
      <el-table-column label="日期" align="center" prop="weatherDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.weatherDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:weather:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:weather:remove']"
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

    <!-- 添加或修改工地环境情况管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工地id" prop="siteId">
          <el-input v-model="form.siteId" placeholder="请输入工地id" />
        </el-form-item>
        <el-form-item label="风速" prop="windSpeed">
          <el-input v-model="form.windSpeed" placeholder="请输入风速" />
        </el-form-item>
        <el-form-item label="温度" prop="temperature">
          <el-input v-model="form.temperature" placeholder="请输入温度" />
        </el-form-item>
        <el-form-item label="pm2.5浓度" prop="pm25">
          <el-input v-model="form.pm25" placeholder="请输入pm2.5浓度" />
        </el-form-item>
        <el-form-item label="pm10浓度" prop="pm10">
          <el-input v-model="form.pm10" placeholder="请输入pm10浓度" />
        </el-form-item>
        <el-form-item label="噪音分贝" prop="noise">
          <el-input v-model="form.noise" placeholder="请输入噪音分贝" />
        </el-form-item>
        <el-form-item label="湿度" prop="humidity">
          <el-input v-model="form.humidity" placeholder="请输入湿度" />
        </el-form-item>
        <el-form-item label="日期" prop="weatherDate">
          <el-date-picker clearable
            v-model="form.weatherDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择日期">
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
import { listWeather, getWeather, delWeather, addWeather, updateWeather } from "@/api/project/weather";

export default {
  name: "Weather",
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
      // 工地环境情况管理表格数据
      weatherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteId: null,
        windSpeed: null,
        temperature: null,
        pm25: null,
        pm10: null,
        noise: null,
        humidity: null,
        weatherDate: null
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
  },
  methods: {
    /** 查询工地环境情况管理列表 */
    getList() {
      this.loading = true;
      listWeather(this.queryParams).then(response => {
        this.weatherList = response.rows;
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
        siteId: null,
        windSpeed: null,
        temperature: null,
        pm25: null,
        pm10: null,
        noise: null,
        humidity: null,
        weatherDate: null
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
      this.title = "添加工地环境情况管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const siteId = row.siteId || this.ids
      getWeather(siteId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工地环境情况管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.siteId != null) {
            updateWeather(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWeather(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除工地环境情况管理编号为"' + siteIds + '"的数据项？').then(function() {
        return delWeather(siteIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/weather/export', {
        ...this.queryParams
      }, `weather_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
