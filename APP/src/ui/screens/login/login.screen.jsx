import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useGlobalUser } from "../../../context";
import { useUserApi } from "../../../hooks/api";
import "./login.style.css";

export function LoginScreen() {
  const [email, setEmail] = useState(null);
  const [password, setPassword] = useState(null);
  const [, setGlobalUser] = useGlobalUser();
  const [error, setError] = useState(null);

  const { login } = useUserApi();

  const navigate = useNavigate();

  function handleEmailChange(event) {
    const username = event.target.value;

    setEmail(username);
  }

  function handlePasswordChange(event) {
    const password = event.target.value;

    setPassword(password);
  }

  async function onLoginSubmit(event) {
    event.preventDefault();

    try {
      const user = await login(email, password);
      setGlobalUser(user);
      localStorage.setItem("user", JSON.stringify(user));
      navigate("/home");
    } catch (error) {
      setError(error.response?.data?.message);
    }
  }

  return (
    <div className="login">
      <h1 className="h1_petFinder">PetFinder</h1>
      <h1>Login</h1>
      <form onSubmit={onLoginSubmit} className="login_form">
        <input
          name="email"
          type="text"
          onChange={handleEmailChange}
          placeholder="Digite seu email"
        />
        <input
          name="password"
          type="password"
          onChange={handlePasswordChange}
          placeholder="Digite sua senha"
        />
        <button>Enviar</button>
      </form>
      <div className="login_cadastro">
        <NavLink to={"/cadastro"}>
          <button>cadastrar</button>
        </NavLink>
      </div>
      {error}
    </div>
  );
}
