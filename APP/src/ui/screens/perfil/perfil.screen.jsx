import { useCallback, useEffect, useState } from "react";
import { MdDeleteForever } from "react-icons/md";
import { useFuncionalidadesApi } from "../../../hooks/api/funcionalidades/use-funcionalidades-api.hook";
import { HeaderComponent } from "../../components";
import { ModalConfirmComponent } from "../../components/modalConfirm/modalConfirm.screen";
import { PostComponent } from "../../components/postagem/post.screen";
import { DataFormatada } from "../../utils/funcoes/dataFormatada";
import "./perfil.style.css";


export function PerfilScreen() {
  const [postagens, setPostagens] = useState([]);
  const [usuario, setUsuario] = useState({});
  const [delPostagem, setDelPostagem] = useState({
    postagemId: ""
  });

  const [abrir, setAbrir] = useState(false);
  const modalApagarComentario = "Deseja Apagar Essa Postagem?";


  const { deletarPostagem, getListarPostagensUsuario, getUsuarioLogado } =
    useFuncionalidadesApi();

  const buscarPostagens = useCallback(async () => {
    const response = await getListarPostagensUsuario();

    setPostagens(response);
  }, [getListarPostagensUsuario]);

  const buscarUsuarioLogado = useCallback(async () => {
    const response = await getUsuarioLogado();

    setUsuario(response);
  }, [getUsuarioLogado]);

  useEffect(() => {
    buscarPostagens();
    buscarUsuarioLogado();
  }, [buscarPostagens, buscarUsuarioLogado]);

  function deletarPostagemId() {
    async function apagarPostagem() {
      try {
        const resposta = await deletarPostagem(
          delPostagem.postagemId
        );
        buscarPostagens();
        setAbrir(!abrir);
      } catch (error) {}
    }
    apagarPostagem();
  }

  function abrirModal(idPostagem) {
    setDelPostagem({ postagemId: idPostagem});
    setAbrir(!abrir);
  }

  return (
    <section className="section_perfil">
      <HeaderComponent />
      <div className="perfil_div margin_bottom_10">
        <div className="perfil_ordenar_p_div">
          <p className="perfil_p">Nome: {usuario.nome}</p>
          <p className="perfil_p">Email: {usuario.email}</p>
          <p className="perfil_p">Apelido: {usuario.apelido}</p>
          <p className="perfil_p">
            Data de Nascimento: {DataFormatada(usuario.data_de_nascimento)}
          </p>
        </div>
        <img
          src={usuario.foto_perfil}
          alt="foto_perfil"
          className="foto_perfil"
        />
      </div>
      {!!postagens && postagens.length !== 0 ? (
        <div className="postagens_perfil lista_scroll">
          {!!abrir && (
            <ModalConfirmComponent
              text={modalApagarComentario}
              cancel={abrirModal}
              confirm={() => {
                deletarPostagemId();
              }}
            />
          )}          
          {postagens.map((postagem) => {
            return (
              <PostComponent
                postagem={postagem}
                buscarPostagens={buscarPostagens}
                key={postagem.id}
              >
                <button
                  onClick={() => {
                    deletarPostagemId(postagem.id);
                    abrirModal(postagem.id);
                    
                  }}
                  className="margin_top_10 margin_bottom_10 deletar_postagem_button"
                >
                  <MdDeleteForever className="MdDeleteForever" />
                </button>
              </PostComponent>
            );
          })}
        </div>
      ) : (
        <div>
          <h1>Você não fez nenhuma publicação</h1>
        </div>
      )}
    </section>
  );
}
