import "./postagem.style.css";

import { useJsApiLoader } from "@react-google-maps/api";
import { useState } from "react";
import { useFuncionalidadesApi } from "../../../hooks/api/funcionalidades/use-funcionalidades-api.hook";
import { HeaderComponent } from "../../components";
import estadosCidades from "../../utils/jsons/estados-cidades.json";

export function PostagemScreen() {
  const { isLoaded } = useJsApiLoader({
    googleMapsApiKey: "AIzaSyCMiNNsQPW39WPUE9XYMSBJwiUWJYTl9CM",
  });

  if (!isLoaded) return <div>Loading...</div>;
  return <Map />;
}

function Map() {
  const [inputValores, setInputValores] = useState({
    tipo: "",
    descricao: "",
    imagem: "",
    cidade: "",
    estado: "",
  });

  const [cidades, setCidades] = useState([]);

  const { postPostagem } = useFuncionalidadesApi();

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

    async function postarPublicacao() {
      try {
        await postPostagem(inputValores);
        document.location.reload(true);
      } catch (error) {}
    }

    postarPublicacao();
  }

  return (
    <section className="section_postagem">
      <HeaderComponent />
      <div className="div_ordenar_postagem">
        <form action="" onSubmit={submitPostagem} className="form_postagem">
          <h2>Criar Postagem</h2>
          <label htmlFor="">
            <select name="tipo" id="" onChange={handlePostagem}>
              <option value="" selected disabled>
                Selecione
              </option>
              <option value="CACHORRO">Cachorro</option>
              <option value="GATO">Gato</option>
              <option value="COELHO">Coelho</option>
            </select>
          </label>

          <label htmlFor="">
            Descricao:
            <input
              type="text"
              name="descricao"
              onChange={handlePostagem}
              className="input_postagem"
            />
          </label>

          <label htmlFor="">
            Imagem:
            <input
              type="text"
              name="imagem"
              onChange={handlePostagem}
              className="input_postagem"
            />
          </label>
          <label htmlFor="">
            Estado:
            <select
              name="estado"
              onChange={handlePostagem}
              id=""
              value={inputValores.estado}
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
          <label htmlFor="">
            Cidade:
            <select
              name="cidade"
              onChange={handlePostagem}
              id=""
              value={inputValores.cidade}
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
          <button>Publicar</button>
        </form>
        <div className="map">
          <iframe
            className="iframe_map"
            referrerpolicy="no-referrer-when-downgrade"
            src={`https://www.google.com/maps/embed/v1/place?key=AIzaSyBbUQABEyf-eRwR9ThGM7ramBouie1a9d0&q=${
              !!inputValores.cidade ? inputValores.cidade : "PareciNovo"
            },${!!inputValores.estado ? inputValores.estado : "RS"} `}
          />
        </div>
      </div>
    </section>
  );
}
