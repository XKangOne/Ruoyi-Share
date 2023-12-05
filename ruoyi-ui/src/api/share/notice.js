import request from '@/utils/request'

// 查询信息列表
export function listNotice(query) {
  return request({
    url: '/share/notice/list',
    method: 'get',
    params: query
  })
}

// 查询信息详细
export function getNotice(id) {
  return request({
    url: '/share/notice/' + id,
    method: 'get'
  })
}

// 新增信息
export function addNotice(data) {
  return request({
    url: '/share/notice',
    method: 'post',
    data: data
  })
}

// 修改信息
export function updateNotice(data) {
  return request({
    url: '/share/notice',
    method: 'put',
    data: data
  })
}

// 删除信息
export function delNotice(id) {
  return request({
    url: '/share/notice/' + id,
    method: 'delete'
  })
}
