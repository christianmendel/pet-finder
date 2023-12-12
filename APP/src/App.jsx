import { Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import { useGlobalUser } from "./context";
import { HomeScreen, LoginScreen, NotFoundScreen, PerfilScreen } from "./ui/screens";
import { CadastroScreen } from "./ui/screens/cadastro/cadastrar.screen";
import { PostagemScreen } from "./ui/screens/postagem/postagem.screen";

function PrivateRoute({ children }) {
  const [user] = useGlobalUser();
  
  if (user.token) {
    return <>{children}</>;
  } else {
    return <Navigate to="/" />;
  }
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<LoginScreen />} />
      <Route path="/cadastro" element={<CadastroScreen />} />
      <Route path="*" element={<NotFoundScreen />} />
      <Route
        path="/home"
        element={
          <PrivateRoute>
            <HomeScreen />
          </PrivateRoute>
        }
      />{" "}
      <Route
        path="/perfil"
        element={
          <PrivateRoute>
            <PerfilScreen />
          </PrivateRoute>
        }
      />
      <Route
        path="/postagem"
        element={
          <PrivateRoute>
            <PostagemScreen />
          </PrivateRoute>
        }
      />
    </Routes>
  );
}

export default App;
