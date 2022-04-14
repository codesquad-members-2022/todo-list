export class CardModificationForm {
  constructor(target) {
    this.isOccupiedInput = false;
    this.target = target;
  }
  template() {
    const state = this.isOccupiedInput ? "active" : "deactivate";
    return `
      <li>
        <div class="task-${state}">
          <input type="text" class="task-${state}__title" placeholder="TITLE">
          <input type="text" class="task-${state}__desc" placeholder="BODY">
          <div class="task-${state}__btns">
            <button class="task-${state}__cancel">취소</button>
            <button class="task-${state}__confirm">등록</button>
          </div>
        </div>
      </li>
    `;
  }
}
