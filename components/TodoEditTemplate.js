export default class TodoEditTemplate {
  constructor(editTitle, editContent, editUserId) {
    this.ediTitle = editTitle;
    this.editContent = editContent;
    this.editUserId = editUserId;
  }
  render = () => {
    return /*html*/ `
    <header>
    <h3>${this.ediTitle}</h3>
    <div class="column__delete">x</div>
  </header>
  <div class="card__content">
    <p class="card__content-text">${this.editContent}</p>
  </div>
  <div class="card__author">
    <p class="card__author-text">author by ${this.editUserId}</p>
  </div>`;
  };
}
