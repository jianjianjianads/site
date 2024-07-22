<script src="js/index.js"></script>
<script>
import * as echarts from 'echarts';
import $echarts from "echarts/lib/echarts";
import Vue from "vue";
import {listSite} from "@/api/project/site";
import {getSiteType} from "@/api/dv/site_display";
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
      datalist:[]
    }
  },
  methods:{
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
    drawEcharts1(datalist){
      let myColor=["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
      this.myChart1.setOption({
        color:["#3398D8"],
        title: {
          // text: '项目类型统计'
        },
        tooltip: {
          trigger:'axis',
          axisPointer:{
            type:"shadow"
          }
        },
        legend: {
        },
        // 修改图表的大小
        grid: {
          left: "0%",
          top: "10px",
          right: "0%",
          bottom: "4%",
          containLabel: true
          //y轴刻度标签
        },
        xAxis: {
          type:"category",
          data:datalist.map((item)=>item.name),
          axislable:{
            color: "rgba(255,255,255,.5)",
            fontSize: "17"},
          // 不显示x坐标轴的样式
          axisLine: {
            show: false
          }
        },
        yAxis: {
          axislable:{
            color: "rgba(255,255,255,.5)",
            fontSize: "12"},
          // 不显示x坐标轴的样式
          axisLine: {
            lineStyle: {
              color: "rgba(255,255,255,.2)",
              // width: 2
            }
          },
          // y轴分割线的颜色
          splitLine: {
            lineStyle: {
              color: "rgba(255,255,255,.1)"
            }
          }
        },
        series: [
          {
            name:"数量",
            type: 'bar',
            data:datalist.map((item)=>item.num),
            itemStyle: {
              // 修改柱子圆角
              barBorderRadius: 15
            }
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
    getData1(){
      getSiteType().then((res)=>{
        // console.log(res)
        this.drawEcharts1(res.rows)
      })
    },
    getData3(){
      getSiteStatus().then((res)=>{
        console.log(res)
        this.drawEcharts3(res.rows)
      })
    },
    getData4(){
      getSiteBudget().then((res)=>{
        // console.log(res)
        this.drawEcharts4(res.rows)
      })
    }
  },
  mounted() {
    this.myChart1=$echarts.init(document.getElementById('echart1'))
    this.myChart2=$echarts.init(document.getElementById('echart2'))
    this.myChart3=$echarts.init(document.getElementById('echart3'))
    this.myChart4=$echarts.init(document.getElementById('echart4'))
    //获取工地项目进度数据
    this.getData2()
    // 获取工地类型数据
    this.getData1()
    //获取工地项目状态数据
    this.getData3()
    // 获取工地项目预算数据
    this.getData4()
    this.myChart1.resize();
    this.myChart2.resize();
    this.myChart4.resize();
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
    <h1>工地信息管理系统</h1>
    <div class="showTime"></div>
  </header>
  <section class="mainbox">
    <div class="column">
      <div class="panel bar" >
        <h2>工地项目类型总览</h2>
        <div class="chart" id="echart1"></div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>工地项目状态总览</h2>
        <div class="chart" id="echart3">图表</div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
    <div class="column">
      <div class="panel bar">
        <h2>工地项目进度排行</h2>
        <div class="chart" id="echart2"></div>
        <div class="panel-footer">
        </div>
      </div>
      <div class="panel line">
        <h2>工地项目预算总览</h2>
        <div class="chart" id="echart4">图表</div>
        <div class="panel-footer">
        </div>
      </div>
    </div>
  </section>
  </body>
</template>
<style lang="scss" scoped>
body {
  background: url(../views/images/bg.jpg)
  no-repeat top center;
  line-height: 1.15
}
header {
  position: relative;
  height: 100px;
  background: url(../views/images/head_bg.png) no-repeat;
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
  padding: 0.125rem 0.125rem 0;
  .panel {
    position: relative;
    height: 400px;
    padding:0 10px 40px;
    border: 1px solid rgba(25, 186, 139, 0.17);
    margin-bottom:15px;
    background: url(../views/images/line.png) rgba(255, 255, 255, 0.03);
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
</style>
