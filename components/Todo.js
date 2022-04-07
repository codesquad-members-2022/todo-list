export default class Todo {
  constructor(todoData) {
    this.todoData = todoData;
  }
  render = () => {
    return `<div class="card">
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
}
