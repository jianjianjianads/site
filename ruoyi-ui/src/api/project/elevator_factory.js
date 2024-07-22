import request from '@/utils/request'

// 查询电梯生产厂家管理列表
export function listElevator_factory(query) {
  return request({
    url: '/project/elevator_factory/list',
    method: 'get',
    params: query
  })
}

// 查询电梯生产厂家管理详细
export function getElevator_factory(factoryId) {
  return request({
    url: '/project/elevator_factory/' + factoryId,
    method: 'get'
  })
}

// 新增电梯生产厂家管理
export function addElevator_factory(data) {
  return request({
    url: '/project/elevator_factory',
    method: 'post',
    data: data
  })
}

// 修改电梯生产厂家管理
export function updateElevator_factory(data) {
  return request({
    url: '/project/elevator_factory',
    method: 'put',
    data: data
  })
}

// 删除电梯生产厂家管理
export function delElevator_factory(factoryId) {
  return request({
    url: '/project/elevator_factory/' + factoryId,
    method: 'delete'
  })
}
