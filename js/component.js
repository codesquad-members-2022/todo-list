function cards(string) {
  return `<div class="todo-content">
  <p class="cards str">${string}</p>
  <button class="remove-btn btn">remove</button>
  <button class="edit-btn btn>edit</button>
  </div>`;
}

function add(addbtn) {
  addbtn.forEach((e) => {
    e.onclick = function () {
      e.parentNode;
    };
  });
}

function remove(removebtn) {
  removebtn.forEach((e) => {
    e.onclick = function () {
      this.parentNode.remove();
    };
  });
}

export { remove };
