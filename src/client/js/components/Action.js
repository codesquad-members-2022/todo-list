import Component from '../core/Component.js';
import { calculateTimeDiff } from '../utils/index.js';

class Action extends Component {
  template() {
    const { contents, executedTime, user } = this.$props.action;
    return `<img class='user-profile' src='${user.profile}' alt='${user.name}'>
        <div class='action-info'>
          <span class='user-name'>${user.name}</span>
          ${contents}
          <span class='executed-time'>${calculateTimeDiff(executedTime)}</span>
        </div>`;
  }
}

export default Action;
