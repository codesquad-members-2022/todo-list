import "./header.scss";

export default class Header {
  render() {
    const template = this.#getTemplate();
    document.body.insertAdjacentHTML("afterbegin", template);
  }

  #getTemplate() {
    return `
      <header>
        <h1 class="header__title">TO-DO LIST</h1>
        <div class="header__menu-btn"></div>
      </header>
    `;
  }
}