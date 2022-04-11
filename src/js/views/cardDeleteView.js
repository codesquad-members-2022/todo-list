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

export const onMouseOverCardDeleteBtn = () => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('mouseover', ({ target }) => {
    handleCardDeleteBtnMouseOver(target);
  });
};

const handleCardDeleteBtnMouseOver = target => {
  const targetBtn = target.closest(`.card__delete-btn`);
  if (!targetBtn) {
    return;
  }
  const targetCard = targetBtn.parentElement;
  const targetBtnImg = targetBtn.querySelector('path');
  targetBtnImg.style.fill = '#ff4343';
  targetCard.style.backgroundColor = '#FFEEEC';
  targetCard.style.boxShadow = 'inset 0 0 0 1px #FF4343';
  targetCard.style.boxSizing = 'border-box';
};

export const onMouseOutCardDeleteBtn = () => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('mouseout', ({ target }) => {
    handleCardDeleteBtnMouseOut(target);
  });
};

const handleCardDeleteBtnMouseOut = target => {
  const targetBtn = target.closest(`.card__delete-btn`);
  if (!targetBtn) {
    return;
  }
  const targetCard = targetBtn.parentElement;
  const targetBtnImg = targetBtn.querySelector('path');
  targetBtnImg.style.fill = '#bdbdbd';
  targetCard.style.backgroundColor = '#fff';
  targetCard.style.boxShadow = 'none';
};

export const onClickCardDeleteBtn = () => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('click', ({ target }) => {
    handleCardDeleteBtnClick(target);
  });
};

const handleCardDeleteBtnClick = target => {
  const targetBtn = target.closest(`.card__delete-btn`);
  if (!targetBtn) {
    return;
  }
  const deleteAlertElement = createDeleteAlertElement();
  document.body.appendChild(deleteAlertElement);
  onClickDeleteAlert(targetBtn, deleteAlertElement);
};

const onClickDeleteAlert = (cardElement, deleteAlertElement) => {
  deleteAlertElement.addEventListener('click', ({ target }) => {
    handleDeleteAlertClick(cardElement, target);
  });
};

const handleDeleteAlertClick = (cardElement, target) => {
  const targetBtn = target.closest(`.delete-alert__btn`);
  if (!targetBtn) {
    return;
  }
  const orders = { 취소: removeAlert, 삭제: removeCard };
  const order = target.innerHTML;

  orders[order](cardElement);
};

const removeCard = cardElement => {
  cardElement.parentElement.remove();
  removeAlert();
};

const removeAlert = () => {
  const alertElement = document.querySelector('.delete-alert-wrapper');
  if (!alertElement) {
    return;
  }
  alertElement.remove();
};
