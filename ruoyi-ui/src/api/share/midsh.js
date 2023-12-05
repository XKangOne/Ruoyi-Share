import request from '@/utils/request'

// 查询用户-分享中间【描述用户购买的分享】列表
export function listMidsh(query) {
  return request({
    url: '/share/midsh/list',
    method: 'get',
    params: query
  })
}

// 查询用户-分享中间【描述用户购买的分享】详细
export function getMidsh(id) {
  return request({
    url: '/share/midsh/' + id,
    method: 'get'
  })
}

// 新增用户-分享中间【描述用户购买的分享】
export function addMidsh(data) {
  return request({
    url: '/share/midsh',
    method: 'post',
    data: data
  })
}

// 修改用户-分享中间【描述用户购买的分享】
export function updateMidsh(data) {
  return request({
    url: '/share/midsh',
    method: 'put',
    data: data
  })
}

// 删除用户-分享中间【描述用户购买的分享】
export function delMidsh(id) {
  return request({
    url: '/share/midsh/' + id,
    method: 'delete'
  })
}
