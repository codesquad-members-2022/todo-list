import { $, $$, pipe, url } from '../util';

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
    const keywordUrl = url(keyword);
    const res = await fetch(keywordUrl);
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
    $$('.column').forEach((column) => {
      const columnName = $('.column-title', column).textContent;
      const columnCards = data.filter((e) => e.states === columnName);

      column.lastElementChild.innerHTML = cardSequence[columnName].reduce(
        (acc, cur) => {
          return (acc += this.createCards(cur, columnCards));
        },
        ''
      );
    });
  };

  createCards = (sequence, columnCards) => {
    let cardsTemplate = '';
    columnCards.forEach((cardInfo) => {
      if (sequence === Number(cardInfo.id)) {
        cardsTemplate += `
        <li class="list_item default" data-id="${cardInfo.id}">
          ${this.cardTemplate(cardInfo.title, cardInfo.content)}
        </li>
      `;
      }
    });
    return cardsTemplate;
  };

  getMaxId = async () => {
    const cardData = await this.getData('cards');
    const maxId = cardData.reduce((prev, current) => {
      return Number(prev.id) > Number(current.id) ? prev : current;
    });

    return cardData.length ? Number(maxId.id) : 0;
  };
}
