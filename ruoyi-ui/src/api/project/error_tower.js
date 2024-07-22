import request from '@/utils/request'

// 查询异常信息管理列表
export function listError_tower(query) {
  return request({
    url: '/project/error_tower/list',
    method: 'get',
    params: query
  })
}

// 查询异常信息管理详细
export function getError_tower(errorId) {
  return request({
    url: '/project/error_tower/' + errorId,
    method: 'get'
  })
}

// 新增异常信息管理
export function addError_tower(data) {
  return request({
    url: '/project/error_tower',
    method: 'post',
    data: data
  })
}

// 修改异常信息管理
export function updateError_tower(data) {
  return request({
    url: '/project/error_tower',
    method: 'put',
    data: data
  })
}

// 删除异常信息管理
export function delError_tower(errorId) {
  return request({
    url: '/project/error_tower/' + errorId,
    method: 'delete'
  })
}
