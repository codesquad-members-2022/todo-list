import Component from '../core/Component.js';
import autosize from 'autosize';
import { UserStore, TaskStore } from '../store/index.js';

class InputCard extends Component {
  template() {
    return `<form class='card deactivate'>
              <textarea class='card-title' name='title' placeholder='제목을 입력하세요' maxlength='100' rows='1'></textarea>
              <textarea class='card-contents' name='contents' placeholder='내용을 입력하세요' maxlength='500' rows='1'></textarea>
              <div class='card-btn-group'>
                <button class='btn-normal card-btn-quit' type='button'>취소</button>
                <button class='btn-accent card-btn-enroll' type='button' disabled></button>
              </div>
            </form>`;
  }

  setEvent() {
    this.addEvent('input', 'textarea', this.handleTextareaInput.bind(this));
    this.addEvent('click', 'button', this.handleButtonClick.bind(this));
  }

  mounted() {
    const allTextarea = this.$target.querySelectorAll('textarea');
    allTextarea.forEach(textarea => autosize(textarea));
    this.fillEnrollBtnName();
    if (this.$props.mode === 'edit') this.fillTitleAndContents();
  }

  getTitleAndContents() {
    const title = this.$target.querySelector('.card-title');
    const contents = this.$target.querySelector('.card-contents');
    return { title, contents };
  }

  fillTitleAndContents() {
    const { title, contents } = this.getTitleAndContents();
    title.value = this.$props.title;
    contents.value = this.$props.contents;
  }

  fillEnrollBtnName() {
    const enrollBtnName = {
      new: '등록',
      edit: '수정',
    };
    const enrollBtn = this.$target.querySelector('.card-btn-enroll');
    enrollBtn.innerText = enrollBtnName[this.$props.mode];
  }

  handleTextareaInput() {
    const { title, contents } = this.getTitleAndContents();

    const isTextareaEmpty = title.value === '' || contents.value === '';
    const isSameTitleAndContents =
      title.value === this.$props.title &&
      contents.value === this.$props.contents;

    const enrollBtn = this.$target.querySelector('.card-btn-enroll');
    enrollBtn.disabled = isTextareaEmpty || isSameTitleAndContents;
  }

  async handleButtonClick(event) {
    event.preventDefault();
    const { target } = event;
    const isEnrolled = target.classList.contains('card-btn-enroll');
    isEnrolled ? this.createNewCard() : this.removeCard();
  }

  async createNewCard() {
    const cardInfo = this.getCardInfo();
    const { columnId, title, contents, userId } = cardInfo;

    switch (this.$props.mode) {
      case 'new': {
        await TaskStore.enrollTask({
          title,
          contents,
          columnId,
          userId,
        });
        break;
      }
      case 'edit': {
        await TaskStore.editTask({ title, contents }, this.$props.taskId);
        break;
      }
    }
  }

  getCardInfo() {
    const columnId = this.$props.id;
    const { title, contents } = this.getTitleAndContents();
    const user = UserStore.getUser();
    const userId = user.id;

    return {
      columnId,
      title: title.value,
      contents: contents.value,
      userId,
    };
  }

  removeCard() {
    if (this.$props.mode === 'edit') this.showHiddenList();
    this.$target.remove();
  }

  showHiddenList() {
    const hiddenList = this.$target.closest('.column').querySelector('.hidden');
    hiddenList.classList.remove('hidden');
  }
}

export default InputCard;
