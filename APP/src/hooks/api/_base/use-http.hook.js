import { useAxios } from "./use-axios.hook"
import { useGlobalUser } from "../../../context"

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers)
  const [,setUser] = useGlobalUser()

  async function get(url) {
    try {
      const response = await instance.get(url)
  
      return response.data
    } catch(error) {
      if(error.response.status === 401) {
        setUser({})
        localStorage.removeItem('user')
        throw error
      }
    }
  }

  async function postlogin(url, data, headers) {
    const response = await instance.post(url, data, headers)
    const token =  response?.headers["x-auth-token"]
    const chave = {token}
    return chave
  }

  async function post(url, data,headers) {
    const response = await instance.post(url, data)

    return response.data
  }

  async function put(url, data) {
    const response = await instance.put(url, data)

    return response.data
  }

  async function del(url, data) {
    const response = await instance.delete(url, data)

    return response.data
  }

  return {
    get,
    postlogin,
    post,
    put,
    del
  }
}
