import request from '@/utils/request'

// 查询分享列表
export function listSha(query) {
  return request({
    url: '/share/sha/list',
    method: 'get',
    params: query
  })
}

// 查询分享详细
export function getSha(id) {
  return request({
    url: '/share/sha/' + id,
    method: 'get'
  })
}

// 新增分享
export function addSha(data) {
  return request({
    url: '/share/sha',
    method: 'post',
    data: data
  })
}

// 修改分享
export function updateSha(data) {
  return request({
    url: '/share/sha',
    method: 'put',
    data: data
  })
}

// 删除分享
export function delSha(id) {
  return request({
    url: '/share/sha/' + id,
    method: 'delete'
  })
}
