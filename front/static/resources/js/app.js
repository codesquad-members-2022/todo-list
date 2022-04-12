import css from '../scss/main.scss';
import { BoardController } from './controllers/boardController';

(() => {
  const boardController = new BoardController();
  boardController.init();
})();
