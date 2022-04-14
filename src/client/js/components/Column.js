import Component from '../core/Component.js';
import { TaskStore } from '../store/index.js';
import { renderCards, renderNewInputCard } from '../render/index.js';

class Column extends Component {
  setup() {
    this.$state = {
      column: this.$props.column,
      tasks: TaskStore.getTasksFilteredWithColumn(this.$props.column.id),
    };
    TaskStore.subscribe('tasks', this);
  }

  template() {
    return `<div class='column-header'>
          <div class='column-header-left'>
            <h2 class='column-header-title'>${this.$state.column.name}</h2>
            <span class='badge'>${this.$state.tasks.length}</span>
          </div>
          <div class='column-header-right'>
            <button class='add-btn'>
              <svg width='14' height='14' viewBox='0 0 14 14' fill='none' xmlns='http://www.w3.org/2000/svg'>
                <path
                  d='M0.105713 7.53033L0.105713 6.46967H6.46967V0.105713H7.53033V6.46967H13.8943V7.53033H7.53033V13.8943H6.46967V7.53033H0.105713Z'
                  fill='#BDBDBD' />
              </svg>
            </button>
            <button class='delete-btn'>
              <svg width='12' height='12' viewBox='0 0 12 12' fill='none' xmlns='http://www.w3.org/2000/svg'>
                <path
                  d='M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z'
                  fill='#BDBDBD' />
              </svg>
            </button>
          </div>
        </div>
        <ol class='column-card-list'></ol>`;
  }

  mounted() {
    renderCards({
      container: this.$target.querySelector('.column-card-list'),
      tasks: this.$state.tasks,
    });
  }

  notify({ tasks }) {
    const filteredTasks = tasks.filter(
      task => task.columnId === this.$state.column.id,
    );
    this.setState({ tasks: filteredTasks });
  }

  setEvent() {
    this.addEvent('click', '.add-btn', () => this.handleAddBtnClick());
  }

  handleAddBtnClick() {
    const inputCard = this.$target.querySelector('.deactivate');
    const hiddenList = this.$target.querySelector('.hidden');

    if (inputCard) inputCard.closest('li').remove();
    if (hiddenList) hiddenList.classList.remove('hidden');
    if (inputCard && !hiddenList) return;

    renderNewInputCard({
      container: this.$target.querySelector('.column-card-list'),
      id: this.$props.column.id,
    });
  }
}

export default Column;
