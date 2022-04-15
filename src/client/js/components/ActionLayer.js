import Component from '../core/Component.js';
import { ActionStore } from '../store/index.js';
import { renderActions } from '../render/index.js';
import { setCancelableInterval } from '../utils/index.js';
import { cloneDeep } from 'lodash';

class ActionLayer extends Component {
  setup() {
    this.$state = {
      actions: ActionStore.getAllActions(),
    };
    ActionStore.subscribe('actions', this);
    ActionStore.subscribe('isActionLayerActive', this);
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
    <ol class='action-list'></ol>`;
  }

  setEvent() {
    this.addEvent('click', '.close-btn', () => this.closeActionLayer());
  }

  mounted() {
    renderActions({
      container: this.$target.querySelector('.action-list'),
      actions: cloneDeep(this.$state.actions),
    });
  }

  notify(state) {
    if (state.isActionLayerActive) {
      state.isActionLayerActive ? this.clearInterval = this.changeActionTime() : this.clearInterval();
    } else {
      this.setState({
        actions: ActionStore.getAllActions(),
      });
    }
  }

  closeActionLayer() {
    this.$target.classList.remove('active');
    ActionStore.toggleIsActionLayerActive();
  }

  changeActionTime() {
    return setCancelableInterval(() => {
      renderActions({
        container: this.$target.querySelector('.action-list'),
        actions: cloneDeep(this.$state.actions),
      });
    }, 10000);
  }
}

export default ActionLayer;
