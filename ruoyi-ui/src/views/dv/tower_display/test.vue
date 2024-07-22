<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="塔机" prop="selectTower">
            <el-cascader v-model="formData.selectTower" :options="selectTowerOptions"
                         :props="selectTowerProps" :style="{width: '100%'}" placeholder="请选择塔机" clearable></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios';
import {getSelectOptions} from "@/api/dv/tower_display";
export default {
  // 组件配置...
  data() {
    return {
      formData: {
        selectTower: [],
      },
      rules: {
        selectTower: [{
          required: true,
          type: 'array',
          message: '请至少选择一个塔机',
          trigger: 'change'
        }],
      },
      selectTowerOptions: [],
      selectTowerProps: {
        "multiple": false,
        "value": "siteName",
        "label": "label",
        "children": "towerIds"
      },
    };
  },
  mounted() {
    this.getOptions()
  },
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {//重置表单
      this.$refs['elForm'].resetFields()
    },
    getOptions() {
      getSelectOptions().then(res=> {
        // TODO 发起请求获取数据
        console.log(res)
        res.rows.forEach(row => {
          // 在 selectTowerOptions 中查找是否已存在对应 siteName 的对象
          let siteData = this.selectTowerOptions.find(option => option.value === row.siteName);
          // 如果不存在，则创建一个新的对象并添加到 selectTowerOptions 数组中
          if (!siteData) {
            siteData = {
              value: row.siteName,
              label: row.siteName,
              towerIds: []  // 创建一个空数组用于存储 towerId 的键值对
            };
            this.selectTowerOptions.push(siteData);
          }
          // 将 towerId 的键值对添加到 siteData 的 childrenOption 数组中
          siteData.towerIds.push({
            value: row.towerId,
            label: row.towerId
          });
        });
        console.log(this.selectTowerOptions);
      })
    },
  },
};
</script>
