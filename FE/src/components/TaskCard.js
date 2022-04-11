export default class TaskCard {
  init() {

  }

  template(state) {
    return `
    <ul class="work__list">
    <li class="work__item">
      <div class="task-idle">
        <div class="task-idle__contents">
          <h3 class="task-idle__title">GitHub 공부하기</h3>
          <p class="task-idle__desc">add, commit, push</p>
          <strong class="task-idle__author">author by web</strong>
        </div>
        <div class="task-idle__btn">
          <div class="task-idle__btn--remove"></div>
        </div>
      </div>
    </li>
    <li>
      <div class="task-active">
          <input type="text" class="task-active__title" placeholder="TITLE">
          <input type="text" class="task-active__desc" placeholder="BODY">
          <div class="task-active__btns">
            <button class="task-active__cancel">취소</button>
            <button class="task-active__confirm">등록</button>
          </div>
        </div>
      </li>
      <li>
        <div class="task-deactivate">
          <input type="text" class="task-deactivate__title" placeholder="TITLE">
          <input type="text" class="task-deactivate__desc" placeholder="BODY">
          <div class="task-deactivate__btns">
            <button class="task-deactivate__cancel">취소</button>
            <button class="task-deactivate__confirm">등록</button>
          </div>
        </div>
      </li>
      <li>
        <div class="task-delete">
          <div class="task-delete__contents">
            <h3 class="task-delete__title">GitHub 공부하기</h3>
            <p class="task-delete__desc">add, commit, push</p>
            <strong class="task-delete__author">author by web</strong>
          </div>
          <div class="task-delete__btn">
            <div class="task-delete__btn--remove"></div>
          </div>
        </div>
      </li>
      <li>
        <div class="task-drag">
          <div class="task-drag__contents">
            <h3 class="task-drag__title">GitHub 공부하기</h3>
            <p class="task-drag__desc">add, commit, push</p>
            <strong class="task-drag__author">author by web</strong>
          </div>
          <div class="task-drag__btn">
            <div class="task-drag__btn--remove"></div>
          </div>
        </div>
      </li>
      <li>
        <div class="task-place">
          <div class="task-place__contents">
            <h3 class="task-place__title">GitHub 공부하기</h3>
            <p class="task-place__desc">add, commit, push</p>
            <strong class="task-place__author">author by web</strong>
          </div>
          <div class="task-place__btn">
            <div class="task-place__btn--remove"></div>
          </div>
        </div>
      </li>
    </ul>
    `
  }
}
