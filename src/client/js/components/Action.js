import Component from '../core/Component.js';
import { calculateTimeDiff } from '../utils/index.js';
import { ActionContents } from '../constants/index.js';

class Action extends Component {
  template() {
    const { executedTime, user } = this.$props.action;
    return `<img class='user-profile' src='${user.profile}' alt='${user.name}'>
        <div class='action-info'>
          <span class='user-name'>${user.name}</span>
          ${this.createContents(this.$props.action)}
          <span class='executed-time'>${calculateTimeDiff(executedTime)}</span>
        </div>`;
  }

  createContents({ type, action, contents }) {
    return ActionContents[type][action](contents);
  }
}

export default Action;
