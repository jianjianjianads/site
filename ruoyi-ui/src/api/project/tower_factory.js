import request from '@/utils/request'

// 查询塔机生产厂家管理列表
export function listTower_factory(query) {
  return request({
    url: '/project/tower_factory/list',
    method: 'get',
    params: query
  })
}

// 查询塔机生产厂家管理详细
export function getTower_factory(factoryId) {
  return request({
    url: '/project/tower_factory/' + factoryId,
    method: 'get'
  })
}

// 新增塔机生产厂家管理
export function addTower_factory(data) {
  return request({
    url: '/project/tower_factory',
    method: 'post',
    data: data
  })
}

// 修改塔机生产厂家管理
export function updateTower_factory(data) {
  return request({
    url: '/project/tower_factory',
    method: 'put',
    data: data
  })
}

// 删除塔机生产厂家管理
export function delTower_factory(factoryId) {
  return request({
    url: '/project/tower_factory/' + factoryId,
    method: 'delete'
  })
}
