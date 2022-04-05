function cards(string) {
  return `<div class="todo-content">
  <p class="card-text">${string}</p>
  <div class="card-btn-wrapper">
    <button class="card-remove-btn">R</button>
    <button class="card-edit-btn btn">E</button>
  </div>
</div>`;
}

function column(className, title) {
  return `<div class="todo-wrapper ${className}">
          <div class="todo-header">
          <h2>${title}</h2>
          <button class="add-btn btn">add</button>
          <button class="remove-btn btn">remove</button>
          </div>
      </div>`;
}

function add(callback) {
  return callback;
}

function addTodo() {
  const parent = document.querySelector(".column-add-btn").parentNode;
  const grandParent = parent.parentNode;
  grandParent.parentNode.innerHTML += cards("hi");
}

function remove(removebtn) {
  removebtn.forEach((e) => {
    e.onclick = function () {
      const parent = this.parentNode;
      parent.parentNode.remove();
    };
  });
}

function removeColumn(removebtn) {
  removebtn.forEach((e) => {
    e.onclick = function () {
      const parent = this.parentNode;
      const grand = parent.parentNode;
      grand.parentNode.remove();
    };
  });
}

function modalReveal() {
  document.querySelector(".modal-wrapper").classList.toggle("active");
}

function toDoInputReveal() {
  document.querySelector(".todo-input").classList.toggle("active");
}

export {
  remove,
  add,
  addTodo,
  cards,
  column,
  removeColumn,
  modalReveal,
  toDoInputReveal,
};
