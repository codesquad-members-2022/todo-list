import { $, $$, pipe } from '../util';

export class InitTodos {
  constructor(columnTemplate, cardTemplate) {
    this.target = $('main');
    this.columnTemplate = columnTemplate;
    this.cardTemplate = cardTemplate;
  }

  init = async () => {
    await pipe(this.getData, this.createColumns, this.renderColumns)('columns');
    pipe(this.getData, this.createCards)('cards');
  };

  getData = async (keyword) => {
    const url = `http://localhost:3001/${keyword}`;
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

  createCards = async (data) => {
    $$('.column').forEach((column) => {
      const columnName = $('.column-title', column).textContent;
      const columnCards = data.filter((e) => e.states === columnName);
      const cardTemplates = columnCards.reduce((acc, cur) => {
        return (
          acc +
          `
            <li class="list_item default" data-id="${cur.id}">
              ${this.cardTemplate(cur.title, cur.content)}
            </li>
            `
        );
      }, '');
      column.lastElementChild.innerHTML = cardTemplates;
    });
  };

  renderCards = (data) => {};

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
