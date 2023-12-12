import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom'
import { UserGlobalProvider } from './context'

ReactDOM.render(
  <React.StrictMode>
    <UserGlobalProvider>
      <BrowserRouter>
        <App /> 
      </BrowserRouter>
    </UserGlobalProvider>
  </React.StrictMode>,
  document.getElementById('root')
);