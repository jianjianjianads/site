import request from '@/utils/request'

// 查询塔机管理列表
export function listTower(query) {
  return request({
    url: '/project/tower/list',
    method: 'get',
    params: query
  })
}

// 查询塔机管理详细
export function getTower(towerId) {
  return request({
    url: '/project/tower/' + towerId,
    method: 'get'
  })
}

// 新增塔机管理
export function addTower(data) {
  return request({
    url: '/project/tower',
    method: 'post',
    data: data
  })
}

// 修改塔机管理
export function updateTower(data) {
  return request({
    url: '/project/tower',
    method: 'put',
    data: data
  })
}

// 删除塔机管理
export function delTower(towerId) {
  return request({
    url: '/project/tower/' + towerId,
    method: 'delete'
  })
}
