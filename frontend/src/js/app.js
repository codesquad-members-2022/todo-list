import resetCss from '../scss/reset.scss';
import css from '../scss/style.scss';
import Controller from './Controller/Controller.js';

const controller = new Controller();
controller.initAlert({
  target: controller.deleteAlertView,
  content: {
    title: '선택한 카드를 삭제할까요?',
    cancel: '취소',
    accept: '삭제',
  },
});
