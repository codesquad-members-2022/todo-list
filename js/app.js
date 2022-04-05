import {
  remove,
  add,
  addTodo,
  cards,
  column,
  removeColumn,
  modalReveal,
  toDoInputReveal,
} from "./component.js";

const plan = document.querySelector(".todo-plan");
const done = document.querySelector(".todo-done");
const columnRemoveBtn = document.querySelectorAll(".column-remove-btn");
let cardAddBtn = document.querySelector(".column-add-btn");
let cardRemoveBtn = document.querySelectorAll(".card-remove-btn");
const modalCancelBtn = document.querySelector(".modal-cancel-btn");

console.log("hi!");
remove(cardRemoveBtn);
removeColumn(columnRemoveBtn);
add(toDoInputReveal());

cardAddBtn.addEventListener("click", add);
modalCancelBtn.addEventListener("click", modalReveal);
