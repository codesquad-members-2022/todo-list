import Component from '../core/Component.js';
import { ActionStore } from '../store/index.js';

class Header extends Component {

  template() {
    return `<h1 class='title'>TO-DO LIST</h1>
    <button class='menu-btn'>
      <svg width='17' height='11' viewBox='0 0 17 11' fill='none' xmlns='http://www.w3.org/2000/svg'>
        <path d='M0 1V0H17V1H0ZM17 5V6H0V5H17ZM0 10H17V11H0V10Z' fill='black'/>
      </svg>        
    </button>`;
  }

  setEvent() {
    this.addEvent('click', '.menu-btn', () => this.openActionLayer())
  }

  openActionLayer() {
    ActionStore.toggleIsActionLayerActive();
    document.querySelector('.action-layer').classList.add('active');
  }
}

export default Header;
