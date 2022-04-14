import { $, pipe } from '../util';

export class InitTodos {
  constructor(template) {
    this.target = $('main');
    this.template = template;
  }

  init = () => {
    pipe(this.getData, this.createColumns, this.renderColumns)('columns');
  };

  getData = async (keyword) => {
    const url = `http://localhost:3001/${keyword}`;
    const res = await fetch(url);
    const json = await res.json();
    return json;
  };

  createColumns = async (data) => {
    return data.map(this.template);
  };

  renderColumns = async (data) => {
    this.target.insertAdjacentHTML('beforeend', data.join(' '));
  };
}
