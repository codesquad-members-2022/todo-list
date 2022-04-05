function column(className, title) {
  return `<div class="todo-wrapper ${className}">
          <div class="todo-header">
          <h2>${title}</h2>
          <button class="add-btn btn">add</button>
          <button class="remove-btn btn">remove</button>
          </div>
      </div>`;
}
// flex
// sass
// button

function cards(string) {
  return `<div class="todo-content">
  <p class="cards str">${string}</p>
  <button class="remove-btn btn">remove</button>
  <button class="edit-btn btn>edit</button>
  </div>`;
}

function init() {
  const main = document.querySelector("main");
  main.innerHTML += column("todo-plan", "해야할 일");
  main.innerHTML += column("todo-done", "완료한 일");

  document.querySelector(".todo-plan").innerHTML += cards("공부하기");
  document.querySelector(".todo-done").innerHTML += cards("운동하기");
}

init();
