import TodoNotice from '../components/TodoNotice.js';
import { $ } from '../utils/dom.js';
import { getLastIdByKey, getLocalStorageByKey } from './localStorage.js';

export const handleNotice = notice => {
  const todoNotice = new TodoNotice(notice);
  $('.alarm').insertAdjacentHTML('afterbegin', todoNotice.render());
};

export const createNotice = (data, action) => {
  const notice = {};
  notice.id = getLastIdByKey('notices') + 1;
  notice.title = data.title;
  notice.firstStatus = data.firstStatus;
  notice.status = data.status;
  notice.action = action;
  notice.userId = 1;

  const localNotices = getLocalStorageByKey('notices');
  localNotices.push(notice);
  localStorage.setItem('notices', JSON.stringify(localNotices));

  return notice;
};
