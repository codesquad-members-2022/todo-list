import { TODO_LIST_URL } from '../constants/constant';
import { $, $$, reflectStore, rerender, saveToDB } from '../utils/util';

const createDeleteAlertElement = () => {
  const deleteAlert = document.createElement('div');
  deleteAlert.classList.add('delete-alert-wrapper');
  deleteAlert.innerHTML = createDeleteAlertTemplate();
  return deleteAlert;
};
const createDeleteAlertTemplate = () => {
  return `
  <div class="delete-alert">
    <div class="delete-alert__text">선택한 카드를 삭제할까요?</div>
    <div class="delete-alert__btn">
      <div class="delete-alert__btn__cancel alert-btn">취소</div>
      <div class="delete-alert__btn__delete alert-btn">삭제</div>
    </div>
  </div>
  `;
};

export const attatchDeleteEventsToView = (parentElement, store) => {
  parentElement.addEventListener('mouseover', ({ target }) => {
    handleCardDeleteBtnMouseOver(target);
  });
  parentElement.addEventListener('mouseout', ({ target }) => {
    handleCardDeleteBtnMouseOut(target);
  });
  parentElement.addEventListener('click', ({ target }) => {
    handleCardDeleteBtnClick(target, store);
  });
};

const handleCardDeleteBtnMouseOver = target => {
  const targetBtn = target.closest(`.card__delete-btn`);
  if (!targetBtn) {
    return;
  }
  const targetCard = targetBtn.parentElement;
  const targetBtnImg = $('path', targetBtn);
  targetBtnImg.style.fill = '#ff4343';
  targetCard.classList.add('delete-candidate-card');
};

const handleCardDeleteBtnMouseOut = target => {
  const targetBtn = target.closest(`.card__delete-btn`);
  if (!targetBtn) {
    return;
  }
  const targetCard = targetBtn.parentElement;
  const targetBtnImg = $('path', targetBtn);
  targetBtnImg.style.fill = '#bdbdbd';
  targetCard.classList.remove('delete-candidate-card');
};

const handleCardDeleteBtnClick = (target, store) => {
  const targetBtn = target.closest(`.card__delete-btn`);
  if (!targetBtn) {
    return;
  }
  const deleteAlertElement = createDeleteAlertElement();
  document.body.appendChild(deleteAlertElement);
  onClickDeleteAlert(targetBtn, deleteAlertElement, store);
};

const onClickDeleteAlert = (cardDeleteBtn, deleteAlertElement, store) => {
  deleteAlertElement.addEventListener('click', ({ target }) => {
    handleDeleteAlertClick(cardDeleteBtn, target, store);
  });
};

const handleDeleteAlertClick = (cardDeleteBtn, target, store) => {
  const targetBtn = target.closest(`.delete-alert__btn`);
  if (!targetBtn) {
    return;
  }
  const orders = { 취소: removeAlert, 삭제: removeCard };
  const order = target.innerHTML;

  orders[order](cardDeleteBtn, store);
};

const removeCard = (cardDeleteBtn, store) => {
  const cardElement = cardDeleteBtn.parentElement;
  removeAlert();
  cardElement.remove();
  reflectStore(store);
  rerender(store, 'main');

  const columns = store.getStore('main');
  columns.forEach(column => {
    const url = TODO_LIST_URL + '/' + column.id;
    saveToDB(url, column);
  });
};

const removeAlert = () => {
  const alertElement = $('.delete-alert-wrapper');
  if (!alertElement) {
    return;
  }
  alertElement.remove();
};
