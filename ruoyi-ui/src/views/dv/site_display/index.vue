<script src="js/index.js"></script>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/3.4.0/echarts.min.js"></script>-->
<!--<script src="http://echarts.baidu.com/resource/echarts-liquidfill-1.0.4/dist/echarts-liquidfill.js"></script>-->
<script>
import * as echarts from 'echarts';
import $echarts from "echarts/lib/echarts";
import Vue from "vue";
import {siteOptions} from "@/api/dv/site_display";
import {getSiteType} from "@/api/dv/site_display";
import {getSiteTower} from "@/api/dv/site_display";
import {getSiteWeather} from "@/api/dv/site_display";
import {getSiteErrorDataDetail} from "@/api/dv/site_display";
import {getSiteErrorData} from "@/api/dv/site_display";
import {getSiteProgress} from "@/api/dv/site_display";
import {getSiteStatus} from "@/api/dv/site_display";
import {getSiteBudget} from "@/api/dv/site_display";
import Link from "@/layout/components/Sidebar/Link";
// import 'dv/site_display/js/index.js';
//引入echarts
Vue.prototype.$echarts = echarts //引入组件
export default {
  components: {Link},
  data(){
    this.myChart1=null
    return{
      datalist:[],
      options: [],
      siteId:3001,
      tableData:[],
      tableDataError:[],
      weather:[],
      progress:45,
    }
  },
  methods:{
    getStatusText(status) {
      if (status == 0) {
        return '正常';
      } else if (status == 1) {
        return '停用';
      }
    },
    drawEcharts2(datalist){
      let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
        this.myChart2.setOption({
          grid: {
            top: "10%",
            left: "22%",
            bottom: "10%"
            // , containLabel: true
            // 刻度标签
          },
          // 不显示x轴的相关信息
          xAxis: {
            show: false
          },
          yAxis: [
            {
              type: "category",
              inverse: true,
              //是否翻转数据
              data:datalist.map((item)=>item.siteName),
              // 不显示y轴的线
              axisLine: {
                show: false
              },
              // 不显示刻度
              axisTick: {
                show: false
              },
              // 把刻度标签里面的文字颜色设置为白色
              axisLabel: {
                color: "#fff"
              }
            },
            {
              data: [100, 100, 100, 100, 100],
              inverse: true,
              // 不显示y轴的线
              axisLine: {
                show: false
              },
              // 不显示刻度
              axisTick: {
                show: false
              },
              // 把刻度标签里面的文字颜色设置为白色
              axisLabel: {
                color: "#fff"
              }
            }
          ],
          series: [
            {
              name: "条",
              type: "bar",
              data:datalist.map((item)=>item.progress),
              // y轴第一个对象
              yAxisIndex: 0,
              // 修改第一组柱子的圆角
              itemStyle: {
                barBorderRadius: 20,
                // 此时的color 可以修改柱子的颜色
                color: function(params) {
                  // params 传进来的是柱子对象
                  // console.log(params);
                  // dataIndex 是当前柱子的索引号
                  return myColor[params.dataIndex];
                }
              },
              // 柱子之间的距离
              barCategoryGap: 50,
              //柱子的宽度
              barWidth: 10,
              // 显示柱子内的文字
              label: {
                show: true,
                position: "inside",
                // {c} 会自动的解析为 数据  data里面的数据
                formatter: "{c}%"
              }
            },
            {
              name: "框",
              type: "bar",
              barCategoryGap: 50,
              barWidth: 15,
              // y轴第二个对象
              yAxisIndex: 1,
              data: [100, 100, 100, 100, 100],
              itemStyle: {
                color: "none",
                borderColor: "#00c1de",
                borderWidth: 3,
                barBorderRadius: 15
              }
            }
          ]
      },)
    },
    drawEcharts3(datalist){
        this.myChart3.setOption({
          color: ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"],
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          legend: {
            bottom: "0%",
            // 修改小图标的大小
            itemWidth: 10,
            itemHeight: 10,
            // 修改图例组件的文字为 12px
            textStyle: {
              color: "rgba(255,255,255,.5)",
              fontSize: "12"
            }
          },
          series: [
            {
              name: "项目状态",
              type: "pie",
              // 这个radius可以修改饼形图的大小
              // radius 第一个值是内圆的半径 第二个值是外圆的半径
              radius: ["40%", "70%"],
              center: ["50%", "45%"],
              //位置
              avoidLabelOverlap: false,
              // 图形上的文字
              label: {
                show: false,
                position: "center"
              },
              // 链接文字和图形的线是否显示
              labelLine: {
                show: false
              },
              data:datalist.map((item)=> {
                return {value:item.num,name:item.name}
              }),
            }
          ]
        })
    },
    drawEcharts4(datalist){
        this.myChart4.setOption({
          color: [
            "#006cff",
            "#60cda0",
            "#ed8884",
            "#ff9f7f",
            "#0096ff",
            "#9fe6b8",
            "#32c5e9",
            "#1d9dff"
          ],
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            bottom: "0%",
            itemWidth: 8,
            itemHeight: 8,
            textStyle: {
              color: "rgba(255,255,255,.5)",
              fontSize: "12"
            }
          },
          series: [
            {
              name: "预算",
              type: "pie",
              radius: ["10%", "60%"],
              center: ["50%", "50%"],
              //展示模式
              roseType: "radius",
              // 图形的文字标签
              label: {
                fontSize: 10
              },
              // 链接图形和文字的线条
              labelLine: {
                // length 链接图形的线条
                length: 6,
                // length2 链接文字的线条
                length2: 8
              },
              data:datalist.map((item)=> {
                return {value:item.num,name:item.name}
              }),
            }
          ]
        })
    },
    drawLiquidFillChart(datalist){
        this.myChart4.setOption({
          graphic: [{
            type: 'group',
            id: 'waterGroup',
            left: 'center',
            top: 'middle',
            children: [{
              type: 'circle',
              shape: {
                cx: 0,
                cy: 0,
                r: 100
              },
              style: {
                fill: '#E0F2F1'
              }
            }, {
              type: 'circle',
              shape: {
                cx: 0,
                cy: 0,
                r: 80
              },
              style: {
                fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: '#80D8FF' // 浅蓝色
                }, {
                  offset: 1,
                  color: '#448AFF' // 深蓝色
                }])
              }
            }, {
              type: 'circle',
              shape: {
                cx: 0,
                cy: 0,
                r: 60
              },
              style: {
                fill: '#fff'
              }
            }, {
              type: 'text',
              style: {
                text: this.progress + '%',
                x: 0,
                y: 0,
                fill: '#000',
                fontSize: 40,
                textAlign: 'center',
                textVerticalAlign: 'middle'
              }
            }]
          }]
        })
    },
    // drawEcharts1(datalist){
    //   let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
    //   this.myChart1.setOption({
    //     color:["#3398D8"],
    //     title: {
    //       // text: '项目类型统计'
    //     },
    //     tooltip: {
    //       trigger:'axis',
    //       axisPointer:{
    //         type:"shadow"
    //       }
    //     },
    //     legend: {
    //     },
    //     // 修改图表的大小
    //     grid: {
    //       left: "0%",
    //       top: "10px",
    //       right: "0%",
    //       bottom: "4%",
    //       containLabel: true
    //       //y轴刻度标签
    //     },
    //     xAxis: {
    //       type:"category",
    //       data:datalist.map((item)=>item.name),
    //       axislable:{
    //         color: "rgba(255,255,255,.5)",
    //         fontSize: "17"},
    //       // 不显示x坐标轴的样式
    //       axisLine: {
    //         show: false
    //       }
    //     },
    //     yAxis: {
    //       axislable:{
    //         color: "rgba(255,255,255,.5)",
    //         fontSize: "12"},
    //       // 不显示x坐标轴的样式
    //       axisLine: {
    //         lineStyle: {
    //           color: "rgba(255,255,255,.2)",
    //           // width: 2
    //         }
    //       },
    //       // y轴分割线的颜色
    //       splitLine: {
    //         lineStyle: {
    //           color: "rgba(255,255,255,.1)"
    //         }
    //       }
    //     },
    //     series: [
    //       {
    //         name:"数量",
    //         type: 'bar',
    //         data:datalist.map((item)=>item.num),
    //         itemStyle: {
    //           // 修改柱子圆角
    //           barBorderRadius: 15
    //         }
    //       }
    //     ]
    //   },)
    // },
    drawEcharts1(datalist){
      let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
      this.myChart1.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        lineStyle: {  // 添加lineStyle属性来设置线条样式
          color: "#56D0E3"  // 设置线条颜色为蓝色
        },
        legend: {
          data: ['Search Engine']
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            axisLabel: {
              textStyle: {
                color: '#fdfdfe' // 修改y轴文字颜色为白色
              }
            },
            type: 'category',
            boundaryGap: false,
            data: datalist.map((item)=>item.name)
          }
        ],
        yAxis: [
          {
            axisLabel: {
              textStyle: {
                color: '#fdfdfe' // 修改y轴文字颜色为白色
              }
            },
            type: 'value'
          }
        ],
        series: [
          {
            name:'异常信息',
            type: 'line',
            stack: 'Total',
            label: {
              show: true,
              position: 'top'
            },
            areaStyle: {
              color:"#56D0E3"
            },
            emphasis: {
              focus: 'series'
            },
            data:datalist.map((item)=>item.num)
          }
        ]
      },)
    },
    getData2(){
      getSiteProgress().then((res)=>{
        // console.log(res)
        this.drawEcharts2(res.rows)
      })
    },
    // 获取当前工地异常信息总体数据
    getData1(){
      getSiteErrorData(this.siteId).then((res)=>{
        // console.log(res)
        this.drawEcharts1(res)
      })
    },
    // 获取当前工地异常信息详细数据
    getData3(){
      getSiteErrorDataDetail(this.siteId).then((res)=>{
        // console.log(res)
        this.tableDataError=res
        // console.log(this.tableDataError)
        // this.drawEcharts3(res.rows)
      })
    },
    // getData4(){
    //   getSiteBudget().then((res)=>{
    //     // console.log(res)
    //     this.drawEcharts4(res.rows)
    //   })
    // },
    //获取工地天气数据
    //获取工地天气数据
    getWeather(){
      getSiteWeather(this.siteId).then((res)=>{
        // console.log(res.data)
        //设备运行状态数据
        this.weather=res.data
        console.log(this.weather)
      })
    },
    getProgress(){
      this.options.forEach(item => {
        if (item.siteId === this.siteId) {
          // 如果找到了与 site_id 相匹配的对象，执行相关操作
          this.progress = item.progress;
          // 这里可以根据需要执行进一步的操作，比如输出 progress
          console.log( this.progress);
          console.log(echarts.version);
        }
      });
    },
    submisson() {
      //获取工地天气数据,有bug 获取数据条数问题  4.18明天继续改
      this.getWeather()
      // 获取当前工地异常信息总体数据
      this.getData1()
      // 获取当前工地异常信息详细数据
      this.getData3()

      //获取当前工地项目进度
      this.getProgress()
      this.drawLiquidFillChart()
      // this.getProgress()
      // console.log(this.siteId)
      // 获取当前工地设备运行状态数据
      getSiteTower(this.siteId).then((res)=>{
        // console.log(res)
        // 设备运行状态数据
        this.tableData=res
      })
    },
    reset() {//重置表单
      this.siteId =3001;
      this.submisson()
    },
    getOptions(){
      siteOptions().then((res)=>{
        console.log(res.rows)
        // console.log(echarts.version);
        this.options=res.rows;
        // this.options.map(option => {
        //   console.log(option.siteId);
        // });
        }
      )
    }
  },
  mounted() {
    this.myChart1=$echarts.init(document.getElementById('echart1'))
    // this.myChart2=$echarts.init(document.getElementById('echart2'))
    // this.myChart3=$echarts.init(document.getElementById('echart3'))
    this.myChart4=$echarts.init(document.getElementById('echart4'))
    // this.drawLiquidFillChart();
    //获取工地项目进度数据
    // this.getData2()
    // 获取工地类型数据
    this.getData1()

    //获取工地异常详细信息
    this.getData3()

    // 加载项目进度数据
    // this.drawLiquidFillChart()

    //获取下拉框选项
    this.getOptions()
    //提交加载函数
    this.submisson();

    // this.myChart1.resize();
    // this.myChart2.resize();
    // this.myChart4.resize();
    // window.addEventListener("resize", function() {
    //   this.myChart.resize();
    // });
  }
}
</script>
<!--<script>-->
<!--var t = null;-->
<!--t = setTimeout(time, 1000); //開始运行-->
<!--function time() {-->
<!--  clearTimeout(t); //清除定时器-->
<!--  dt = new Date();-->
<!--  var y = dt.getFullYear();-->
<!--  var mt = dt.getMonth() + 1;-->
<!--  var day = dt.getDate();-->
<!--  var h = dt.getHours(); //获取时-->
<!--  var m = dt.getMinutes(); //获取分-->
<!--  var s = dt.getSeconds(); //获取秒-->
<!--  document.querySelector(".showTime").innerHTML =-->
<!--    "当前时间：" +-->
<!--    y +-->
<!--    "年" +-->
<!--    mt +-->
<!--    "月" +-->
<!--    day +-->
<!--    "-" +-->
<!--    h +-->
<!--    "时" +-->
<!--    m +-->
<!--    "分" +-->
<!--    s +-->
<!--    "秒";-->
<!--  t = setTimeout(time, 1000); //设定定时器，循环运行-->
<!--}-->
<!--</script>-->

<template>
<body>
  <header>
    <h1>工地项目数据可视化</h1>
    <div class="showTime">
      <el-select v-model="siteId" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.siteId"
          :label="item.siteName"
          :value="item.siteId">
        </el-option>
      </el-select>
      <el-button @click="submisson">选择</el-button>
      <el-button @click="reset">重置</el-button>
    </div>
  </header>
  <section class="mainbox">
    <div class="column">
      <div class="panel bar" >
        <h2>工地异常信息概览</h2>
        <div class="chart" id="echart1"></div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>异常详细信息</h2>
        <div class="chart" id="echart3">
          <el-table :data="tableDataError" height="250" border style="width: 100%">
            <el-table-column
              prop="towerId"
              label="设备id"
              width="80"
              align="center">
            </el-table-column>
            <el-table-column
              prop="name"
              label="异常属性"
              width="80"
              align="center">
            </el-table-column>
            <el-table-column
              prop="value"
              label="异常值"
              width="80"
              align="center">
            </el-table-column>
            <el-table-column
              prop="max"
              label="异常阈值"
              width="80"
              align="center">
            </el-table-column>
            <el-table-column
              prop="errorTime"
              label="发生时间"
              width="80"
              align="center">
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
    <div class="column">
      <div class="panel bar" >
        <h2>工地</h2>
        <div class="chart" id="site">
        </div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>设备状态</h2>
        <div class="chart" id="echart5">
<!--          <el-table-->
<!--            :data="tableData"-->
<!--            :header-cell-style="{-->
<!--            color: '#fff',-->
<!--            background: '#0a3370',-->
<!--            fontWeight: '700',-->
<!--          }"-->
<!--            max-height="720"-->
<!--          >-->
<!--          </el-table>-->
          <el-table :data="tableData" height="250" border style="width: 100%" >
            <el-table-column
              prop="towerId"
              label="设备id"
              width="160"
              align="center">
            </el-table-column>
            <el-table-column
              prop="towerModel"
              label="设备型号"
              width="160"
              align="center">
            </el-table-column>
            <el-table-column prop="status" label="设备状态" width="160" align="center">
              <template slot-scope="scope">
                {{ getStatusText(scope.row.status) }}
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
    <div class="column">
      <div class="panel bar">
        <h2>环境监测</h2>
        <div class="chart" id="echart2">
          <div class="weather">
            <div class="weatherBox" id="weather1">
              <span>{{ this.weather.windSpeed }} m/s 风速</span><br>
<!--              <span>东 2级</span>-->
            </div>
            <div class="weatherBox" id="weather2">
              <span>{{ this.weather.temperature }}°C 温度</span><br>
<!--              <span>夏季施工 防暑降温</span>-->
            </div>
          </div>
          <div class="weather">
            <div class="weatherBox" id="weather3">
              <span>{{ this.weather.pm25 }}ug/m³ PM2.5</span>
<!--              <br>-->
<!--              <span>27.0ug/m³</span><span>PM10</span>-->
            </div>
            <div class="weatherBox" id="weather4">
              <span>{{ this.weather.noise }}db 噪音</span>
<!--              <br>-->
<!--              <span>27.0%</span><span>湿度</span>-->
            </div>
          </div>
        </div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>当前项目进度</h2>
        <div class="chart" id="echart4">
<!--          <span>{{ this.progress }}</span>-->
        </div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
  </section>
</body>
</template>
<style lang="scss" scoped>
.liquidfill-container {
  width: 100%;
  height: 100%;
}
body {
  background: url(../site_display/images/bg.jpg)
  no-repeat top center;
  line-height: 1.15
}
header {
  position: relative;
  height: 100px;
  background: url(../site_display/images/head_bg.png) no-repeat;
  background-size: 100% 100%;
  h1 {
    font-size: 38px;
    color: #fff;
    text-align: center;
    line-height: 80px;
  }
  .showTime {
    position: absolute;
    right: 30px;
    top: 0;
    line-height: 75px;
    color: rgba(255, 255, 255, 0.7);
    font-size: 20px;
  }
}
div.weatherBox{
  position: relative;
  text-align: center; /* 水平居中 */
}
span {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  display: inline-block; /* 将 span 设置为内联块级元素 */
}
.weather{
  display: flex;
  height: 150px;
  border: 1px solid rgba(25, 186, 139, 0.17);
  padding: 10px 10px 0;
  .weatherBox{
    background: #1e1e1e;
   flex: 2;
  }
}
.mainbox {
  display: flex;
  min-width: 1024px;
  max-width: 1920px;
  margin: 0 auto;
  padding: 10px 10px 0;
  .column:nth-child(2){
    flex: 4;
  }
  .panel {
    position: relative;
    height: 400px;
    padding:0 10px 40px;
    border: 1px solid rgba(25, 186, 139, 0.17);
    margin-bottom:15px;
    background: url(../site_display/images/line.png) rgba(255, 255, 255, 0.03);
    #site {
      background: url(../site_display/images/site.jpg)
    }
    &::before{
      position: absolute;
      top: 0;
      left: 0;
      width: 10px;
      height: 10px;
      border-left: 2px solid #02a6b5;
      border-top: 2px solid #02a6b5;
      content: "";
    }
    &::after{
      position: absolute;
      top: 0;
      right: 0;
      width: 10px;
      height: 10px;
      border-right: 2px solid #02a6b5;
      border-top: 2px solid #02a6b5;
      content: "";
    }
    .panel-footer {
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      &::before {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 10px;
        height: 10px;
        border-left: 2px solid #02a6b5;
        border-bottom: 2px solid #02a6b5;
        content: "";
      }
      &::after {
        position: absolute;
        bottom: 0;
        right: 0;
        width: 10px;
        height: 10px;
        border-right: 2px solid #02a6b5;
        border-bottom: 2px solid #02a6b5;
        content: "";
      }
    }
    h2{
      height: 40px;
      color: #fff;
      line-height: 48px;
      text-align: center;
      font-size: 25px;
      font-weight: 400;
    }
    .chart{
      height: 300px;
      color: #1ab394;
    }
  }
}
.mainbox .column {
  flex: 3;
}

// 表格部分样式
// 最外层透明
::v-deep .el-table,
::v-deep .el-table__expanded-cell {
  background-color: transparent;
  color: #93dcfe;
  font-size: 24px;
}

// 表格内背景颜色
::v-deep .el-table th,
::v-deep .el-table tr,
::v-deep .el-table td {
  background-color: transparent;
  border: 0px;
  color: #93dcfe;
  font-size: 15px;
  height: 15px;
  font-family: Source Han Sans CN Normal, Source Han Sans CN Normal-Normal;
  font-weight: Normal;
}

// 去掉最下面的那一条线
//.el-table::before {
//  height: 0px;
//}
//背景图片
//.el-table{
//  background-image:url(../site_display/images/table_background.jpg);
//}
// 设置表格行高度
::v-deep .el-table__body tr,
::v-deep .el-table__body td {
  padding: 0;
  height: 20px;
}

// 修改高亮当前行颜色
::v-deep .el-table tbody tr:hover > td {
  background: #063570 !important;
}

// 取消当前行高亮
// ::v-deep .el-table tbody tr {
//   pointer-events: none;
// }

// 修改表头样式-加边框
::v-deep .el-table__header-wrapper {
  border: solid 1px #04c2ed;
  // box-sizing: border-box;
}

// 表格斑马自定义颜色
::v-deep .el-table__row.warning-row {
  background: #063570;
}

// 滚动条样式
::v-deep .el-table__body-wrapper::-webkit-scrollbar-track {
  background-color: #063570;
}

::v-deep .el-table__body-wrapper::-webkit-scrollbar {
  width: 10px;
  opacity: 0.5;
}

::v-deep .el-table__body-wrapper::-webkit-scrollbar-thumb {
  border-radius: 15px;
  background-color: #0257aa;
}

</style>
