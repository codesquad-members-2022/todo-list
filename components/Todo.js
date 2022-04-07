export default class Todo {
  constructor(todoData) {
    this.todoData = todoData;
  }
  render = () => {
    return /*html*/ `<div class="card" id =${this.todoData.id}>
      <header>
        <h3>${this.todoData.title}</h3>
        <div class="column__delete">x</div>
      </header>
      <div class="card__content">
        <p class="card__content-text">${this.todoData.content}</p>
      </div>
      <div class="card__author">
        <p class="card__author-text">author by ${this.todoData.userId}</p>
      </div>
    </div>`;
  };

  run = () => {
    document.getElementById().addEventListener('click', this.makeEdit);
  };
  makeEdit = () => {
    console.log('hi');
  };
}
