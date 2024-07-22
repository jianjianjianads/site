import request from '@/utils/request'
// 3.23查询工地项目类型及其数量
export function getSiteType(query) {
  return request({
    url: '/project/site/typeList',
    method: 'get',
    params: query
  })
}
//3.24 查询工地项目状态及其数量
export function getSiteStatus(query) {
  return request({
    url: '/project/site/statusList',
    method: 'get',
    params: query
  })
}
//3.25 查询工地项目预算分布
export function getSiteBudget(query) {
  return request({
    url: '/project/site/budgetList',
    method: 'get',
    params: query
  })
}
//3.26 查询工地项目进度top5
export function getSiteProgress(query) {
  return request({
    url: '/project/site/progressList',
    method: 'get',
    params: query
  })
}
//4.13 获取选项
export function siteOptions(query) {
  return request({
    url: '/project/site/siteOptions',
    method: 'get',
    params: query
  })
}
// 4.14新增
// 获取工地所属塔机设备状态
export function getSiteTower(siteId) {
  return request({
    url: '/project/tower/site/' + siteId,
    method: 'get'
  })
}
// 4.14新增
// 获取工地所属异常信息数据
export function getSiteErrorDataDetail(siteId) {
  return request({
    url: '/project/error_tower/site/' + siteId,
    method: 'get'
  })
}
// 4.14新增
// 获取工地所属异常信息数据
export function getSiteErrorData(siteId) {
  return request({
    url: '/project/error_tower/siteAll/' + siteId,
    method: 'get'
  })
}
//4.15新增获取单日工地环境数据
export function getSiteWeather(siteId) {
  return request({
    url: '/project/weather/site/' + siteId,
    method: 'get'
  })
}
