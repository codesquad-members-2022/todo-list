import Component from "./Component.js";

export class TodoCard extends Component {

  template() {
    const { todo, idx, selected } = this.$props;
    return `${selected === true ? `<div class="wrapper">
<span class="title">${todo.title}</span><span class="content">${todo.content}</span>
</div>
<div class="frame">
  <div class="button-left"><span>취소</span></div>
  <div class="button-right"><span>등록</span></div>
</div>` :
      `<div class="wrapper">
        <h2 class="card-title">${todo.title}</h2><span class="card-content">${todo.content}</span><span
        class="card-caption">${todo.caption}</span>
      </div>
    <span class="delete-card">
    <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path
    d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z"
    fill="#828282" />
    </svg></span>`
    }`;
  }

  setEvent() {
    this.addEvent("doubleclick", "wrapper", e => {

    });
  }
}
