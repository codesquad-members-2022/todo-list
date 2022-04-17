import Component from '../core/Component.js';
import columnStore from '../store/columnStore.js';
import { renderEditedInputCard, renderAlert } from '../render/index.js';

class Card extends Component {
  template() {
    return `<div class='card'>
              <div class='card-header'>
                <h3 class='card-title'>${this.$props.title}</h3>
                <button class='delete-btn'>
                  <svg width='12' height='12' viewBox='0 0 12 12' fill='none' xmlns='http://www.w3.org/2000/svg'>
                    <path
                      d='M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z'
                      fill='#828282' />
                  </svg>
                </button>
              </div>
              <p class='card-contents'>${this.$props.contents}</p>
              <span class='card-author'>author by ${this.$props.user.name}</span>
            </div>`;
  }

  setEvent() {
    this.addEvent('mouseover', '.delete-btn', () => this.addCardStyle());

    this.addEvent('mouseout', '.delete-btn', () => this.removeCardStyle());

    this.addEvent('click', '.delete-btn', () => this.openAlert());

    this.addEvent('dblclick', '.card', () => this.showEditedInputCard());
  }

  addCardStyle() {
    this.$target.querySelector('.card').classList.add('delete');
  }

  removeCardStyle() {
    this.$target.querySelector('.card').classList.remove('delete');
  }

  openAlert() {
    this.$target.querySelector('.card').classList.add('delete');
    renderAlert({
      cardId: this.$props.id,
    });
  }

  showEditedInputCard() {
    this.removeInputCard();

    const columnById = columnStore.getColumnWithId(this.$props.columnId);
    const taskId = this.$target.dataset.id;
    const { title, contents } = this.$props;

    renderEditedInputCard({
      target: this.$target,
      column: columnById,
      title,
      contents,
      taskId,
    });
    this.$target.classList.add('hidden');
  }

  removeInputCard() {
    const column = this.$target.closest('.column');
    const inputCard = column.querySelector('.deactivate');
    if (!inputCard) return;

    inputCard.closest('li').remove();
    const hiddenList = column.querySelector('.hidden');
    if (!hiddenList) return;

    hiddenList.classList.remove('hidden');
  }
}

export default Card;
