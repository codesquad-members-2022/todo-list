function column(className, title) {
  return `<div class="todo-wrapper ${className}">
          <div class="todo-header">
          <h2>${title}</h2>
          <button class="add-btn btn">add</button>
          <button class="remove-btn btn">remove</button>
          </div>
      </div>`;
}

const plan = document.querySelector('.todo-plan');
const done = document.querySelector('.todo-done');
let removebtn = document.querySelectorAll('.remove-btn');
