import request from '@/utils/request'

// 查询工地承包商管理列表
export function listSite_contractor(query) {
  return request({
    url: '/project/site_contractor/list',
    method: 'get',
    params: query
  })
}

// 查询工地承包商管理详细
export function getSite_contractor(contractorId) {
  return request({
    url: '/project/site_contractor/' + contractorId,
    method: 'get'
  })
}

// 新增工地承包商管理
export function addSite_contractor(data) {
  return request({
    url: '/project/site_contractor',
    method: 'post',
    data: data
  })
}

// 修改工地承包商管理
export function updateSite_contractor(data) {
  return request({
    url: '/project/site_contractor',
    method: 'put',
    data: data
  })
}

// 删除工地承包商管理
export function delSite_contractor(contractorId) {
  return request({
    url: '/project/site_contractor/' + contractorId,
    method: 'delete'
  })
}
