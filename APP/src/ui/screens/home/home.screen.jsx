import { useCallback, useEffect, useState } from "react";
import { useFuncionalidadesApi } from "../../../hooks/api/funcionalidades/use-funcionalidades-api.hook";
import { HeaderComponent, PostComponent } from "../../components";
import estadosCidades from "../../utils/jsons/estados-cidades.json";
import "./home.style.css";

export function HomeScreen() {
  const [postagens, setPostagens] = useState([]);
  const [cidades, setCidades] = useState([]);

  const [inputValores, setInputValores] = useState({
    cidade: "",
    estado: "",
  });

  const { getListarPostagens, getListarPostagemPorCidadeEstado } =
    useFuncionalidadesApi();

  const buscarPostagens = useCallback(async () => {
    const response = await getListarPostagens();

    setPostagens(response);
  }, [getListarPostagens]);

  useEffect(() => {
    buscarPostagens();
  }, [buscarPostagens]);

  function handlePostagem(e) {
    const { name, value } = e.target;

    setInputValores({ ...inputValores, [name]: value });

    if (name === "estado") {
      const estadoFiltrado = estadosCidades.estados.find((estado) => {
        return estado.sigla === value;
      });

      setCidades(estadoFiltrado.cidades);
    }
  }

  function submitPostagem(e) {
    e.preventDefault();

    async function buscarPostagens() {
      try {
        const resposta = await getListarPostagemPorCidadeEstado(
          inputValores.cidade,
          inputValores.estado
        );
        if (resposta.length !== 0 && resposta.length !== null) {
          setPostagens(resposta);
        }
      } catch (error) {}
    }
    buscarPostagens();
  }

  function limparFiltro() {
    setInputValores({ cidade: "", estado: "" });
    buscarPostagens();
  }

  return (
    <section className="section_home">
      <HeaderComponent />

      <div className="filtro_home">
        <form action="" onSubmit={submitPostagem} className="form_filtro_home">
          <label htmlFor="" className="label_filtro_home">
            Estado:
            <select
              name="estado"
              onChange={handlePostagem}
              id=""
              value={inputValores.estado}
              className="select_filtro_home margin_left_10"
              required
            >
              <option value="" disabled selected>
                Selecione
              </option>
              {estadosCidades.estados.map((cidade) => {
                return <option value={cidade.sigla}>{cidade.sigla}</option>;
              })}
            </select>
          </label>
          <label htmlFor="" className="label_filtro_home">
            Cidade:
            <select
              name="cidade"
              onChange={handlePostagem}
              id=""
              value={inputValores.cidade}
              className="select_filtro_home margin_left_10"
              required
              disabled={!inputValores.estado}
            >
              <option value="" disabled selected>
                Selecione
              </option>
              {cidades.map((cidade) => {
                return <option value={cidade}>{cidade}</option>;
              })}
            </select>
          </label>
          <button>Buscar</button>
        </form>
        <button onClick={limparFiltro}>Limpar filtro</button>
      </div>
      <div className="div_ordenar_home">
        <div className="map">
          <iframe
            className="iframe_map"
            referrerpolicy="no-referrer-when-downgrade"
            src={`https://www.google.com/maps/embed/v1/place?key=AIzaSyBbUQABEyf-eRwR9ThGM7ramBouie1a9d0&q=${
              !!inputValores.cidade ? inputValores.cidade : "PareciNovo"
            },${!!inputValores.estado ? inputValores.estado : "RS"} `}
          />
        </div>
        {!!postagens && postagens.length !== 0 && (
          <div className="postagens_home lista_scroll">
            {postagens.map((postagem) => {
              return (
                <PostComponent
                  postagem={postagem}
                  buscarPostagens={buscarPostagens}
                />
              );
            })}
          </div>
        )}
      </div>
    </section>
  );
}
