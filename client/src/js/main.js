import { addCardEvent, renderColumns } from './todos';
import { cardAdder } from './todos';
export const init = () => {
  renderColumns();
  addCardEvent();
};
