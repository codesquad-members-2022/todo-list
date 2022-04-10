import Component from '../core/Component.js';
import ActionStore from '../store/ActionStore.js';
import { calculateTimeDiff } from '../utils/index.js';

class ActionLayer extends Component {
  setup() {
    this.$state = {
      actions: ActionStore.getActions(),
    };
    ActionStore.subscribe('actions', this);
  }

  template() {
    return `<div class='action-btn-wrapper'>
      <button class='close-btn'>
        <svg width='12' height='12' viewBox='0 0 12 12' fill='none' xmlns='http://www.w3.org/2000/svg'>
          <path
            d='M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z'
            fill='#BDBDBD' />
        </svg>
      </button>
    </div>
    <ol class='action-list'>
      ${
        this.$state.actions.length
          ? this.$state.actions
              .reverse()
              .reduce((prev, { id, contents, executedTime, user }) => {
                return (prev += `<li class='action-list-item' data-action-id='${id}'>
        <img class='user-profile' src='${user.profile}' alt='${user.name}'>
        <div class='action-info'>
          <span class='user-name'>${user.name}</span>
          ${contents}
          <span class='executed-time'>${calculateTimeDiff(executedTime)}</span>
        </div>
      </li>`);
              }, '')
          : `<li>활동 기록이 없습니다.</li>`
      }
    </ol>`;
  }

  setEvent() {
    this.addEvent('click', '.close-btn', () => {
      this.$target.classList.remove('active');
    });
  }
}

export default ActionLayer;
