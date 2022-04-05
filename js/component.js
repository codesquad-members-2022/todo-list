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

function add(addbtn) {
  addbtn.forEach((e) => {
    e.onclick = function () {
      this.parentNode.append(`<div>new</div>`);
    };
  });
}

function remove(removebtn) {
  removebtn.forEach((e) => {
    e.onclick = function () {
      const parent = this.parentNode;
      parent.parentNode.remove();
    };
  });
}

export { remove, add, cards, column };
