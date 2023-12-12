import "./modalConfirm.style.css";
export function ModalConfirmComponent({ confirm, text, cancel }) {
  function deslogar() {}

  return (
    <div className="modal_confirm_div">
      <h4>{text}</h4>
      <div className="confirmar_cancelar_buttons">
        <button onClick={confirm}>Confirmar</button>
        <button onClick={cancel}>Cancelar</button>
      </div>
    </div>
  );
}
