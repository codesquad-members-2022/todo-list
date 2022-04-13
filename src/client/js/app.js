import '../style/style.scss';
import { initStore } from './store/index.js';
import { render } from './render/index.js';

const init = async () => {
  await initStore();
  render();
};

init();
