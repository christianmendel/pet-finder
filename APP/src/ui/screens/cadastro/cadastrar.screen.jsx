import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useUserApi } from "../../../hooks/api";
import "./cadastrar.style.css";

export function CadastroScreen() {
  const [inputValores, setinputValores] = useState({
    nome: "",
    email: "",
    senha: "",
    apelido: "",
    data_de_nascimento: "",
    foto_perfil: "",
  });

  const { cadastro } = useUserApi();
  const navigate = useNavigate()
  function handleChange(e) {
    const { name, value } = e.target;
    setinputValores({ ...inputValores, [name]: value });
  }

  function hadleSubmit(e) {
    e.preventDefault();

    async function cadastrar() {
      try {
        await cadastro(inputValores);
        navigate("/")
      } catch (error) {}
    }
    cadastrar();
  }

  return (
    <section className="section_cadastro">
      <form action="" onSubmit={hadleSubmit} className="formulario_cadastro">
        <label htmlFor="">
          Name:
          <input type="text" name="nome" onChange={handleChange} value={inputValores.name} required placeholder="Digite seu nome"/>
        </label>
        <label htmlFor="">
          Email:
          <input type="email" name="email" onChange={handleChange} value={inputValores.email} required placeholder="Digite seu email" autocomplete="off"/>
        </label>
        <label htmlFor="">
          Senha:
          <input type="password" name="senha" onChange={handleChange} value={inputValores.senha} required placeholder="Digite sua senha"/>
        </label>
        <label htmlFor="">
          Apelido:
          <input type="text" name="apelido" onChange={handleChange} value={inputValores.apelido} placeholder="Digite seu apelido"/>
        </label>
        <label htmlFor="">
          Data de nascimento:
          <input
            type="date"
            name="data_de_nascimento"
            onChange={handleChange}
            value={inputValores.data_de_nascimento}
            required
          />
        </label>
        <label htmlFor="">
          Foto de perfil:
          <input type="text" name="foto_perfil" onChange={handleChange} value={inputValores.foto_perfil} placeholder="Digite sua imagem" />
        </label>
        <button>Criar Conta</button>
      </form>
    </section>
  );
}
