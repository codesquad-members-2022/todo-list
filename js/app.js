import { remove, add, cards, column } from "./component.js";

const plan = document.querySelector(".todo-plan");
const done = document.querySelector(".todo-done");
let cardAddBtn = document.querySelectorAll(".column-add-btn");
let cardRemoveBtn = document.querySelectorAll(".card-remove-btn");

console.log("hi!");
add(cardAddBtn);
remove(cardRemoveBtn);
