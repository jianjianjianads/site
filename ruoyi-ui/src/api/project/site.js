import request from '@/utils/request'

// 查询工地项目列表
export function listSite(query) {
  return request({
    url: '/project/site/list',
    method: 'get',
    params: query
  })
}
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
// 查询工地项目详细
export function getSite(siteId) {
  return request({
    url: '/project/site/' + siteId,
    method: 'get'
  })
}

// 新增工地项目
export function addSite(data) {
  return request({
    url: '/project/site',
    method: 'post',
    data: data
  })
}

// 修改工地项目
export function updateSite(data) {
  return request({
    url: '/project/site',
    method: 'put',
    data: data
  })
}

// 删除工地项目
export function delSite(siteId) {
  return request({
    url: '/project/site/' + siteId,
    method: 'delete'
  })
}
