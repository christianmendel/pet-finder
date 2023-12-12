import { useMemo } from "react";
import { BASE_SOCIAL_FLIX } from "../../../constants";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../_base/use-http.hook";

export function useFuncionalidadesApi() {
  const [user] = useGlobalUser();
  const httpInstance = useHttp(BASE_SOCIAL_FLIX, {
    "X-Auth-Token": user.token,
  });

  

  async function postPostagem(postagem) {
    const response = await httpInstance.post(`/petfinder/criar/postagem`, {...postagem});

    return response;
  }


  async function postComentario(comentario) {
    const response = await httpInstance.post(`/petfinder/adicionar/comentario`, {...comentario});

    return response;
  }

  async function getListarPostagensPerdidos() {
    const response = await httpInstance.get(`/petfinder/listar/postagens/perdidos`);

    return response;
  }

  async function getListarPostagens() {
    const response = await httpInstance.get(`/petfinder/listar/postagens`);

    return response;
  }

  async function getListarPostagemID(id) {
    const response = await httpInstance.get(`/petfinder/listar/postagem/${id}`);

    return response;
  }

  async function getListarPostagensUsuario() {
    const response = await httpInstance.get(`/petfinder/listar/postagens/usuario`);

    return response;
  }

  async function getUsuarioLogado() {
    const response = await httpInstance.get(`/petfinder/listar/perfil/logado`);

    return response;
  }

  async function getListarPostagemPorCidadeEstado(cidade,estado) {
    const response = await httpInstance.get(`/petfinder/listar/postagens/${cidade}/${estado}`);

    return response;
  }

  async function putAlterarStatusPostagem(id) {
    const response = await httpInstance.put(`/petfinder/alterar/postagem/${id}/status`);

    return response;
  }

  async function deletarPostagem(id) {
    const response = await httpInstance.del(`/petfinder/deletar/postagem/${id}`);

    return response;
  }

  async function deletarPostagem(id) {
    const response = await httpInstance.del(`/petfinder/deletar/postagem/${id}`);

    return response;
  }

  async function deletarComentarioPostagem(idPostagem,idComentario) {
    const response = await httpInstance.del(`/petfinder/deletar/postagem/${idPostagem}/comentario/${idComentario}`);

    return response;
  }

  async function posLogout() {
    const response = await httpInstance.post(
      `/logout`
    );

    return response;
  }

  return useMemo(
    () => ({
      postPostagem,
      postComentario,
      getListarPostagensPerdidos,
      getListarPostagens,
      getListarPostagemID,
      getListarPostagemPorCidadeEstado,
      getListarPostagensUsuario,
      putAlterarStatusPostagem,
      deletarPostagem,
      posLogout,
      deletarComentarioPostagem,
      getUsuarioLogado,
      
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [user]
  );
}
