import Component from '../core/Component.js';
import { TaskStore } from '../store/index.js';

class Alert extends Component {
  template() {
    return `<div class='alert-container'>
              <p class='alert-text'>선택한 카드를 삭제할까요?</p>
              <div class='alert-btn-group'>
                <button class='btn-normal'>취소</button>
                <button class='btn-accent'>삭제</button>
              </div>
            </div>`;
  }

  mounted() {
    this.$target.classList.add('active');
  }

  setEvent() {
    this.addEvent('click', '.btn-normal', () => this.undeleteCard());

    this.addEvent('click', '.btn-accent', () => this.deleteCard());
  }

  undeleteCard() {
    document.querySelector('.delete').classList.remove('delete');
    this.closeAlert();
  }

  async deleteCard() {
    await TaskStore.deleteTask(this.$props.cardId);
    this.closeAlert();
  }

  closeAlert() {
    this.$target.classList.remove('active');
    this.destroy();
  }
}

export default Alert;
