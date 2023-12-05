import request from '@/utils/request'

// 查询分享列表
export function listUser(query) {
  return request({
    url: '/share/user/list',
    method: 'get',
    params: query
  })
}

// 查询分享详细
export function getUser(id) {
  return request({
    url: '/share/user/' + id,
    method: 'get'
  })
}

// 新增分享
export function addUser(data) {
  return request({
    url: '/share/user',
    method: 'post',
    data: data
  })
}

// 修改分享
export function updateUser(data) {
  return request({
    url: '/share/user',
    method: 'put',
    data: data
  })
}

// 删除分享
export function delUser(id) {
  return request({
    url: '/share/user/' + id,
    method: 'delete'
  })
}
