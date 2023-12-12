import { useState } from "react";
import { MdDeleteForever } from "react-icons/md";
import { useFuncionalidadesApi } from "../../../hooks/api/funcionalidades/use-funcionalidades-api.hook";
import {
  DataEHoraFormatada,
  DataFormatada,
} from "../../utils/funcoes/dataFormatada";
import { PalavraFormatada } from "../../utils/funcoes/palavraFormatada";
import { ModalConfirmComponent } from "../modalConfirm/modalConfirm.screen";
import "./post.style.css";
export function PostComponent({ postagem, buscarPostagens, children }) {
  const [comentario, setComentario] = useState({
    id: "",
    comentario: "",
  });

  const [delComentario, setDelComentario] = useState({
    postagemId: "",
    comentarioId: "",
  });

  const [abrir, setAbrir] = useState(false);

  const { deletarComentarioPostagem, postComentario } = useFuncionalidadesApi();

  const modalApagarComentario = "Deseja Apagar Esse Comentario?";

  function handleComentario(e) {
    const { name, value } = e.target;

    setComentario({ ...comentario, [name]: value });
  }

  function postagemId(id) {
    setComentario({ ...comentario, id: id });
  }

  function submitComentario(e) {
    e.preventDefault();

    async function adicionarComentario() {
      try {
        await postComentario(comentario);
        buscarPostagens();
      } catch (error) {}
    }
    adicionarComentario();
  }
  function apagarComentario() {
    async function apagarComentarioPostagem() {
      try {
        const resposta = await deletarComentarioPostagem(
          delComentario.postagemId,
          delComentario.comentarioId
        );
        buscarPostagens();
        setAbrir(!abrir);
      } catch (error) {}
    }
    apagarComentarioPostagem();
  }

  function abrirModal(idPostagem, idComentario) {
    setDelComentario({ postagemId: idPostagem, comentarioId: idComentario });
    setAbrir(!abrir);
  }

  return (
    <div className="div_postagem_post" key={postagem.id}>
      {!!abrir && (
        <ModalConfirmComponent
          text={modalApagarComentario}
          cancel={abrirModal}
          confirm={() => {
            apagarComentario();
          }}
        />
      )}
      <div className="status_data_div">
        <p className="postagem_p">Status: {postagem.status}</p>
        <p className="postagem_p">
          Data publicação: {DataFormatada(postagem.data)}
        </p>
      </div>
      <div className="estado_cidade_div">
        <p className="postagem_p">Estado: {postagem.estado}</p>
        <p className="postagem_p">
          Cidade: {PalavraFormatada(postagem.cidade)}
        </p>
      </div>
      <p className="postagem_p">Tipo: {PalavraFormatada(postagem.tipo)}</p>
      <p className="postagem_p ">Descrição: {postagem.descricao}</p>
      <img src={postagem.imagem} alt="" className="img_pet_post" />
      <div className="comentarios_postagens lista_scroll">
        {postagem.comentarios.map((comentario) => {
          return (
            <div className="comentario_div" key={comentario.id}>
              <span>
                <p className="comentario_p_margin">
                  Nome: {comentario.usuario.nome}
                </p>
              </span>
              <span>
                <p className="comentario_p_margin">
                  Data: {DataEHoraFormatada(comentario.horario)}
                </p>
              </span>
              <span>
                <p className="comentario_p_margin">
                  Comentario: {comentario.comentario}
                </p>
              </span>

              <button
                onClick={() => {
                  abrirModal(postagem.id, comentario.id);
                }}
                className="apagar_comentario_button"
              >
                <MdDeleteForever className="MdDeleteForever apagar_comentario_button_icon" />
              </button>
            </div>
          );
        })}
      </div>
      <div className="adicionar_comentario_div">
        <form
          action=""
          onSubmit={submitComentario}
          className="adicionar_comentario_form"
        >
          <label htmlFor="" className="adicionar_comentario_label">
            <input
              type="text"
              name="comentario"
              onChange={handleComentario}
              id=""
              className="adicionar_comentario_input"
            />
          </label>
          <button
            onClick={() => {
              postagemId(postagem.id);
            }}
          >
            Comentar
          </button>
        </form>
      </div>
      {children}
    </div>
  );
}
