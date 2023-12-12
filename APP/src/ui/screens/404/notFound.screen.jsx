import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { HeaderComponent } from "../../components";
import "./notFound.style.css";

export function NotFoundScreen() {
  const navigate = useNavigate(); 

  useEffect(()=>{
    timeOut()
  },[])

  function timeOut(){
    setTimeout(() => {
      return navigate("/home")
    }, 5000);
  }

  return (
    <section className="section_notFound">
      <HeaderComponent />
      <div className="text_align_center">
        <h1>Pagina 404</h1>
        <p>Você será redirecionado a Pagina Inicial!</p>
      </div>
    </section>
  );
}
