import axios from 'axios'

export function useAxios(baseUrl, headers) {
  return axios.create({
    baseURL: baseUrl,
    headers,
  })
}

