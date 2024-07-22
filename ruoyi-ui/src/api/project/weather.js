import request from '@/utils/request'

// 查询工地环境情况管理列表
export function listWeather(query) {
  return request({
    url: '/project/weather/list',
    method: 'get',
    params: query
  })
}

// 查询工地环境情况管理详细
export function getWeather(siteId) {
  return request({
    url: '/project/weather/' + siteId,
    method: 'get'
  })
}

// 新增工地环境情况管理
export function addWeather(data) {
  return request({
    url: '/project/weather',
    method: 'post',
    data: data
  })
}

// 修改工地环境情况管理
export function updateWeather(data) {
  return request({
    url: '/project/weather',
    method: 'put',
    data: data
  })
}

// 删除工地环境情况管理
export function delWeather(siteId) {
  return request({
    url: '/project/weather/' + siteId,
    method: 'delete'
  })
}
