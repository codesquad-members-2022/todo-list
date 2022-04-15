import { $, $$, pipe } from '../util';

export class InitTodos {
  constructor(columnTemplate, cardTemplate) {
    this.target = $('main');
    this.columnTemplate = columnTemplate;
    this.cardTemplate = cardTemplate;
  }

  init = async () => {
    await pipe(this.getData, this.createColumns, this.renderColumns)('columns');
    pipe(this.getData, this.renderCards)('cards');
  };

  getData = async (keyword) => {
    const url = `http://localhost:3002/${keyword}`;
    const res = await fetch(url);
    const json = await res.json();
    return json;
  };

  createColumns = async (data) => {
    return data.map(this.columnTemplate);
  };

  renderColumns = async (data) => {
    this.target.insertAdjacentHTML('beforeend', data.join(' '));
  };

  renderCards = async (data) => {
    const cardSequence = await this.getData('cardSequence');
    console.log(cardSequence);
    $$('.column').forEach((column) => {
      let cardsTemplate = '';
      const columnName = $('.column-title', column).textContent;
      const columnCards = data.filter((e) => e.states === columnName);
      cardSequence[columnName].forEach((sequence) => {
        columnCards.forEach((cardInfo) => {
          if (sequence === Number(cardInfo.id)) {
            cardsTemplate += `
            <li class="list_item default" data-id="${cardInfo.id}">
              ${this.cardTemplate(cardInfo.title, cardInfo.content)}
            </li>
          `;
          }
        });
      });
      column.lastElementChild.innerHTML = cardsTemplate;
    });
  };

  getMaxId = async () => {
    const cardData = await this.getData('cards');

    if (cardData.length) {
      const maxId = cardData.reduce((prev, current) => {
        return Number(prev.id) > Number(current.id) ? prev : current;
      });

      return Number(maxId.id);
    } else {
      return 0;
    }
  };
}
