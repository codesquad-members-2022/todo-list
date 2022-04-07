import { axiosRequest, postData } from "./util/util.js";
const data = {
  column: "have-to-do-column",
  title: "post연습",
  detail: "GitHub 공부 내용<br>모던 자바스크립트 1장 공부내용",
  time: "~~~",
};
const changedData = {
  column: "have-to-do-column",
  title: "put연습",
  detail: "GitHub 공부 내용<br>모던 자바스크립트 1장 공부내용",
  time: "~~~",
  id: "7",
};
const getData = await axiosRequest("get", "todos");
console.log(getData);
const $hamburger = document.querySelector(".hamburger-button");
$hamburger.addEventListener("click", async function () {
  const post = await axiosRequest("post", "todos", data);
  console.log(post);
});
$hamburger.addEventListener("mouseleave", async function () {
  const put = await axiosRequest("put", "todos", changedData);
  console.log(put);
});
