const myAlert = (() => {
  let callback = () => {};
  const $alert = document.createElement("div");
  $alert.className = "alert-background";
  $alert.addEventListener("click", alertButtonClickHandler);

  function render(message) {
    $alert.innerHTML = template(message);
    document.body.appendChild($alert);
  }
  function destroy() {
    document.body.removeChild($alert);
  }
  function template(state) {
    return `
      <div class="alert">
        <div class="alert-message">${state.message ? state.message : ""}</div>
        <div class="alert-button-wrapper">
          <button class="alert-button-normal">${state.cancleText ? state.cancleText : "취소"}</button>
          <button class="alert-button-accent">${state.confirmText ? state.confirmText : "확인"}</button>
        </div>
      </div>
    `;
  }
  function alertButtonClickHandler({ target }) {
    if (target.closest(".alert-button-normal")) {
      destroy();
    } else if (target.closest(".alert-button-accent")) {
      destroy();
      callback();
    }
  }

  return (state = {}, _callback = () => {}) => {
    callback = _callback;
    render(state);
  };
})();

export default myAlert;
