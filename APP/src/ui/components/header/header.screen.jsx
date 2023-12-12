import { BiLogOut } from "react-icons/bi";
import { Link, useNavigate } from "react-router-dom";
import logo from "../../../assets/img/logo.png";
import "./header.style.css";
export function HeaderComponent() {
  const navigate = useNavigate();

  function deslogar() {
    localStorage.removeItem("user");
    navigate("/");
  }

  return (
    <header className="header">
      <div className="div_img_header">
        <img src={logo} alt="logo" className="img_header" />
      </div>
      <nav className="nav_header">
        <ul className="ul_header">
          <Link to={"/home"}>
            <li>Home</li>
          </Link>
          <Link to={"/postagem"}>
            <li>Criar Postagem</li>
          </Link>{" "}
          <Link to={"/perfil"}>
            <li>Perfil</li>
          </Link>
        </ul>
      </nav>
      <button className="border_none" onClick={deslogar}>
        <BiLogOut className="BiLogOut " />
      </button>
    </header>
  );
}
