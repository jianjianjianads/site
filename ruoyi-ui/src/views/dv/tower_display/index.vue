<template>
  <body>
  <header>
    <h1>塔机信息可视化</h1>
    <div class="showTime">
      <el-cascader :options="selectTowerOptions"  v-model="towerId" clearable ></el-cascader>
      <el-button @click="submisson">选择</el-button>
      <el-button @click="reset">重置</el-button>
    </div>
  </header>
  <section class="mainbox">
    <div class="column">
      <div class="panel bar" >
        <h2>塔机所属工地总览</h2>
        <div class="chart" id="echart1"></div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>塔机高度</h2>
        <div class="chart" id="echart3">图表</div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>塔机六限位</h2>
        <div class="chart" id="echart5">图表</div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
    <div class="column">
      <div class="panel bar">
        <h2>塔机状态总览</h2>
        <div class="chart" id="echart2"></div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>当前塔机状态</h2>
        <div class="chart" id="echart4">图表</div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>当前风速</h2>
        <div class="chart" id="echart6">图表</div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
  </section>
  </body>
</template>
<style lang="scss" scoped>
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
.mainbox {
  display: flex;
  min-width: 1024px;
  max-width: 1920px;
  margin: 0 auto;
  padding: 10px 10px 0;
  .column {
    flex: 3;
  }
  .panel {
    position: relative;
    height: 310px;
    padding:0 15px 40px;
    border: 1px solid rgba(25, 186, 139, 0.17);
    margin-bottom:15px;
    background: url(../site_display/images/line.png) rgba(255, 255, 255, 0.03);
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
      height: 36px;
      color: #fff;
      line-height: 36px;
      text-align: center;
      font-size: 20px;
      font-weight: 400;
    }
    .chart{
      height: 240px;
      color: #1ab394;
    }
  }
}
</style>
<script src="js/index.js"></script>
<script>
import 'echarts-gl';
import * as echarts from 'echarts';
import $echarts from "echarts/lib/echarts";
import Vue from "vue";
import {getTower} from "@/api/dv/tower_display";
import {getTowerStatus} from "@/api/dv/tower_display";
import {getTowerDistribution} from "@/api/dv/tower_display";
import {getTowerContractorsList} from "@/api/dv/tower_display";
import {getSelectOptions} from "@/api/dv/tower_display";
import {addError_tower} from "@/api/project/error_tower";
import Link from "@/layout/components/Sidebar/Link";
//引入echarts
Vue.prototype.$echarts = echarts //引入组件
export default {
  // components: {Link},
  components: {},
  data(){
    // this.myChart1=null
    return{
      //3.27新增
      selectTowerOptions: [],
      datalist:[],
      towerId: ["中兴国际", 1],
    }
  },
  //3.27新增
  computed: {},
  watch: {},
  methods:{
    //3.27新增
    submisson() {
      // console.log(this.towerId[1])
      // console.log(this.towerId)
      getTower(this.towerId[1]).then((res)=>{
        console.log(res.data)
        // console.log(res.data.cartLocation)
        this.drawEcharts3(res.data)
        this.drawEcharts4(res.data)
        this.drawEcharts5(res.data)
        this.drawEcharts6(res.data)
        this.checkTowerLimits(res.data)
      })
    },
    reset() {//重置表单
      this.towerId = ["中兴国际", 1];
      this.submisson()
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
              children: []  // 创建一个空数组用于存储 towerId 的键值对
            };
            this.selectTowerOptions.push(siteData);
          }
          // 将 towerId 的键值对添加到 siteData 的 childrenOption 数组中
          siteData.children.push({
            value: row.towerId,
            label: row.towerId
          });
        });
        // console.log(this.selectTowerOptions);
      })
    },
    // 定义检测函数，检查塔机的六限位是否超过预警值
    checkTowerLimits(datalist) {
      try {
        // 检查塔机的各项限位是否超过预警值
        const warningLimits = this.checkWarningLimits(datalist);
        // 如果有超过预警值的限位，则弹出提醒窗口
        if (warningLimits.length > 0) {
          // 构建弹窗信息
          const message = warningLimits.map(limit => `${limit.name} 已经超过阈值 (${limit.value} > ${limit.max}).`).join('\n');
          alert(`Tower ${datalist.towerId} 的六限位存在属性超过阈值,请联系管理员:\n${message}`);
          // 将异常塔机的信息添加到数据库的errorDataTower表中
          warningLimits.forEach(limit => {
            // 创建一个新的 Date 对象，它将自动设置为当前时间
            const errorTower = {
              towerId: datalist.towerId,
              name: limit.name,
              value: limit.value,
              max: limit.max,
            };
            console.log(errorTower);
            // 调用函数将异常塔机的信息添加到数据库的error_tower表中
            addError_tower(errorTower).then((res)=>{
              console.log('666')
            });
          });
        }
      } catch (error) {
        console.error("Error checking tower limits:", error);
      }
    },
    checkWarningLimits(datalist) {
       // 这里进行了六限位的检查，并返回超过预警值的限位信息数组
       // 根据塔机的具体六限位数据来进行检查
      const warningHeight=200;
      const warningMaxLoad=9999;
      const warningJibAngle=100;
      const warningMoment=2000;
      const warningWeedSpeed=99;
      const warningTiltAngle=10;
     const warningLimits = [];
     if (datalist.towerHeight > warningHeight) {
        warningLimits.push({ name: "towerHeight", value: datalist.towerHeight, max: warningHeight });
        }
     if (datalist.maxLoad > warningMaxLoad) {
        warningLimits.push({ name: "maxLoad", value: datalist.maxLoad, max: warningMaxLoad });
        }
      if (datalist.jibAngle > warningJibAngle) {
        warningLimits.push({ name: "jibAngle", value: datalist.jibAngle, max: warningJibAngle });
      }
      if (datalist.moment > warningMoment) {
        warningLimits.push({ name: "moment", value: datalist.moment, max: warningMoment });
      }
      if (datalist.windSpeed > warningWeedSpeed) {
        warningLimits.push({ name: "windSpeed", value: datalist.windSpeed, max: warningWeedSpeed });
      }
      if (datalist.tiltAngle > warningTiltAngle) {
        warningLimits.push({ name: "tiltAngle", value: datalist.tiltAngle, max: warningTiltAngle });
      }
        return warningLimits;
},
//三维高度图数据
    drawEcharts3(datalist){
      const indexX = datalist.cartLocation.indexOf('X');
      const indexY = datalist.cartLocation.indexOf('Y');
      var xAxis = datalist.cartLocation.substring(indexX + 1, indexY)*1;
      var yAxis = datalist.cartLocation.substring(indexY + 1)*1;
      let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
      var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
        '7a', '8a', '9a', '10a', '11a',
        '12p', '1p', '2p', '3p', '4p', '5p',
        '6p', '7p', '8p', '9p', '10p', '11p'];
// prettier-ignore
      var days = ['Saturday', 'Friday', 'Thursday',
        'Wednesday', 'Tuesday', 'Monday', 'Sunday'];
      var data=[[xAxis,yAxis,datalist.towerHeight]]
// prettier-ignore
      this.myChart3.setOption({
          tooltip: {},
          visualMap: {
            max: 20,
            inRange: {
              color: [
                '#313695',
                '#4575b4',
                '#74add1',
                '#abd9e9',
                '#e0f3f8',
                '#ffffbf',
                '#fee090',
                '#fdae61',
                '#f46d43',
                '#d73027',
                '#a50026'
              ]
            }
          },
          xAxis3D: {
            type: 'category',
            data: hours,
            axisLabel: { // 修改 Z 轴字体颜色
              color: '#fff'
            }
          },
          yAxis3D: {
            type: 'category',
            data: days,
            axisLabel: { // 修改 Z 轴字体颜色
              color: '#fff'
            }
          },
          zAxis3D: {
            type: 'value',
            axisLabel: { // 修改 Z 轴字体颜色
              color: '#fff'
            }
          },
          grid3D: {
            boxWidth: 120,
            boxDepth: 80,
            light: {
              main: {
                intensity: 1.2
              },
              ambient: {
                intensity: 0.3
              }
            }
          },
          series: [
            {
              name:'塔机坐标',
              type: 'bar3D',
              data: data.map(function (item) {
                return {
                  value: [item[1], item[0], item[2]]
                };
              }),
              // data:[[xAxis],[yAxis],[datalist.towerHeight]],
              shading: 'color',
              label: {
                show: false,
                fontSize: 16,
                borderWidth: 1
              },
              itemStyle: {
                opacity: 0.4
              },
              emphasis: {
                label: {
                  fontSize: 20,
                  color: '#900'
                },
                itemStyle: {
                  color: '#900'
                }
              }
            }
          ]
      },
        )},
    drawEcharts4(datalist){
      let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
      this.myChart4.setOption({
        color: [
          "#8B78F6",
          "#ed8884",],
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '塔机状态',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 1, name: datalist.status == 0 ? '正常' : '停用' },
            ]
          }
        ]
      },
      )},
    drawEcharts6(datalist){
      this.myChart6.setOption({
        series: [
          {
            type: 'gauge',
            axisLine: {
              lineStyle: {
                width: 30,
                color: [
                  [0.3, '#67e0e3'],
                  [0.7, '#37a2da'],
                  [1, '#fd666d']
                ]
              }
            },
            pointer: {
              itemStyle: {
                color: 'auto'
              }
            },
            axisTick: {
              distance: -30,
              length: 8,
              lineStyle: {
                color: '#fff',
                width: 2
              }
            },
            splitLine: {
              distance: -30,
              length: 30,
              lineStyle: {
                color: '#fff',
                width: 4
              }
            },
            axisLabel: {
              color: 'inherit',
              distance: 2,
              fontSize: 13
            },
            detail: {
              valueAnimation: true,
              formatter: '{value} m/s',
              color: 'inherit'
            },
            // data:datalist.map((item)=>item.windSpeed)
            data:[
              {value:datalist.windSpeed.toFixed(2)}
            ]
            // data: [
            //   {
            //     value: +(Math.random() * 100).toFixed(2)
            //   }
            // ]
          }
        ],
        // ,
        },
      )},
    drawEcharts2(datalist){
      let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
      this.myChart2.setOption({
        color: [
          "#60cda0",
          "#ed8884",],
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            // name: '塔机状态',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data:datalist.map((item)=> {
              return {value:item.num,name:item.name}
            }),
          }
        ]
      },)
    },
    drawEcharts1(datalist){
      this.myChart1.setOption({
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
        grid:{
          bottom:"5%"
        },
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
            name: "数量",
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
      },)
    },
    drawEcharts5(datalist){
      this.myChart5.setOption({
        legend: {
          data: ['塔机数值', '预警值']
        },
        radar: {
          // shape: 'circle',
          // const warningHeight=200;
          // const warningMaxLoad=9999;
          // const warningJibAngle=100;
          // const warningMoment=2000;
          // const warningWeedSpeed=99;
          // const warningTiltAngle=10;
          indicator: [
            { name: '载重', max: 9999 },
            { name: '塔机高度', max: 200 },
            { name: '大臂角度', max: 100 },
            { name: '力矩', max: 2000 },
            { name: '风速', max: 99 },
            { name: '塔机倾斜角度', max: 10 }
          ]
        },
        tooltip: {
          trigger: 'item',
          formatter: function(params) {
            return params.seriesName + '<br>' + params.marker + params.data.name + '：' + params.data.value;
          }
        },
        series: [
          {
            name: '塔机六限位数据',
            type: 'radar',
            data: [
              {
                value: [
                  datalist.maxLoad,
                  datalist.towerHeight,
                  datalist.jibAngle,
                  datalist.moment,
                  datalist.windSpeed,
                  datalist.tiltAngle],
                name: '塔机数值',
                itemStyle: {
                  color: "rgba(29,234,118,0.5)", // 设置塔机数值的颜色为蓝色
                }
              },
              {
                value: [9999, 200, 100, 2000, 99, 10],
                name: '预警值',
                itemStyle: {
                  color: "rgba(225,10,10,0.5)", // 设置预警值的颜色为红色
                }
              }
            ]
          }
        ]
      },)
    },
    // getData2(){
    //   getTowerContractorsList().then((res)=>{
    //     // console.log(res)
    //     this.drawEcharts2(res.rows)
    //   })
    // },
    // getData1(){
    //   getTowerList().then((res)=>{
    //     // console.log(res)
    //     this.drawEcharts1(res.rows)
    //   })
    // },
    getData2(){
      getTowerStatus().then((res)=>{
        // console.log(res)
        //有数据 无内容 封装问题？
        this.drawEcharts2(res.rows)
      })
    },
    getData1(){
      getTowerDistribution().then((res)=>{
        // console.log(res)
        //有数据 无内容 封装问题？
        this.drawEcharts1(res.rows)
      })
    },
  },
  mounted() {
    this.myChart1=$echarts.init(document.getElementById('echart1'))
    this.myChart2=$echarts.init(document.getElementById('echart2'))
    this.myChart3=$echarts.init(document.getElementById('echart3'))
    this.myChart4=$echarts.init(document.getElementById('echart4'))
    this.myChart5=$echarts.init(document.getElementById('echart5'))
    this.myChart6=$echarts.init(document.getElementById('echart6'))
    //获取塔机六限位数据
    // this.getData1()
    //获取塔机所属制造商数据
    // this.getData2()
    //获取下拉框数据
    this.getOptions()
    //获取塔机所属工地分布数据
    this.getData1()
    //获取塔机状态分布数据
    this.getData2()
    this.submisson()
    // this.myChart1.resize();
    // this.myChart2.resize();
    // this.myChart4.resize();
    // window.addEventListener("resize", function() {
    //   this.myChart1.resize();
    //   this.myChart2.resize();
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


