function column(title) {
  return `<div class="todo-wrapper">
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
  </div>`;
}

// function init() {
//   const main = document.querySelector('main');
//   main.innerHTML += column('해야할 일');
//   main.innerHTML += column('완료한 일');

//   document.querySelector('.todo-wrapper').innerHTML += cards('공부하기');
// }

init();
