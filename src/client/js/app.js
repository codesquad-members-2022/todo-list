import '../style/style.scss';
import ActionLayer from './components/ActionLayer.js';
import ActionStore from './store/ActionStore.js'
import Header from './components/Header.js';

const init = async () => {
  await ActionStore.init();
  new Header(document.querySelector('header'));
  new ActionLayer(document.querySelector('.action-layer'));
}

init();
