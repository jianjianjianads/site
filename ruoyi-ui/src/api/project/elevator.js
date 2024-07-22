import request from '@/utils/request'

// 查询工程电梯管理列表
export function listElevator(query) {
  return request({
    url: '/project/elevator/list',
    method: 'get',
    params: query
  })
}

// 查询工程电梯管理详细
export function getElevator(elevatorId) {
  return request({
    url: '/project/elevator/' + elevatorId,
    method: 'get'
  })
}

// 新增工程电梯管理
export function addElevator(data) {
  return request({
    url: '/project/elevator',
    method: 'post',
    data: data
  })
}

// 修改工程电梯管理
export function updateElevator(data) {
  return request({
    url: '/project/elevator',
    method: 'put',
    data: data
  })
}

// 删除工程电梯管理
export function delElevator(elevatorId) {
  return request({
    url: '/project/elevator/' + elevatorId,
    method: 'delete'
  })
}
