
import { useMemo } from 'react'
import { useHttp } from '../_base/use-http.hook'
import { BASE_SOCIAL_FLIX } from '../../../constants'

export function useUserApi() {
  const httpInstance = useHttp(BASE_SOCIAL_FLIX)

  async function login(email, password) {
    const response = await httpInstance.postlogin('/login',{},{auth:{ username:email, password }} )

    return response
  }

  async function cadastro(cadastro) {
    const response = await httpInstance.post('/cadastro',cadastro)

    return response
  }

  return useMemo(() => ({
    login,
    cadastro
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }), [])
}
