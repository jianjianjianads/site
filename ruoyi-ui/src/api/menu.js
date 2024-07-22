import request from '@/utils/request'

// 获取路由
//根据用户权限获取动态路由
export const getRouters = () => {
  return request({
    url: '/getRouters',
    method: 'get'
  })
}
