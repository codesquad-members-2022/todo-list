import '../src/styles/main.scss';
import Columns from './components/Columns.js';
import Histories from './components/Histories.js';

window.addEventListener('DOMContentLoaded', () => {
  const columns = new Columns();
  const histories = new Histories();

  columns.init();
  histories.init();
});
