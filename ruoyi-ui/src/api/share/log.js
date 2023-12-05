import request from '@/utils/request'

// 查询积分变更记录列表
export function listLog(query) {
  return request({
    url: '/share/log/list',
    method: 'get',
    params: query
  })
}

// 查询积分变更记录详细
export function getLog(id) {
  return request({
    url: '/share/log/' + id,
    method: 'get'
  })
}

// 新增积分变更记录
export function addLog(data) {
  return request({
    url: '/share/log',
    method: 'post',
    data: data
  })
}

// 修改积分变更记录
export function updateLog(data) {
  return request({
    url: '/share/log',
    method: 'put',
    data: data
  })
}

// 删除积分变更记录
export function delLog(id) {
  return request({
    url: '/share/log/' + id,
    method: 'delete'
  })
}
