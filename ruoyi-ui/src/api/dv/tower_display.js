import request from '@/utils/request'
// 3.24查询塔机分布工地及其数量
export function getTowerDistribution(query) {
  return request({
    url: '/project/tower/distributionList',
    method: 'get',
    params: query
  })
}
//3.24 查询塔机状态及其数量
export function getTowerStatus(query) {
  return request({
    url: '/project/tower/statusList',
    method: 'get',
    params: query
  })
}
//3.24 查询塔机状态及其数量
export function getTowerList(query) {
  return request({
    url: '/project/tower/list',
    method: 'get',
    params: query
  })
}
//3.25 查询塔机制造商列表
export function getTowerContractorsList(query) {
  return request({
    url: '/project/tower/contractorsList',
    method: 'get',
    params: query
  })
}
//3.27新增塔机查询选项数据
export function getSelectOptions(query) {
  return request({
    url: '/project/tower/towerOptions',
    method: 'get',
    params: query
  })
}
//3.28新增
// 查询塔机管理详细
export function getTower(towerId) {
  return request({
    url: '/project/tower/' + towerId,
    method: 'get'
  })
}
