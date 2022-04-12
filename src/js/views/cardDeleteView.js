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

export const onClickCardDeleteBtn = store => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('click', ({ target }) => {
    handleCardDeleteBtnClick(target, store);
  });
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
  const cardId = cardElement.dataset.id;
  const columnElement = cardElement.parentElement.parentElement.classList;
  const columnName = [...columnElement].filter(className => className !== 'column').join('');
  cardElement.remove();
  removeAlert();
  reflectStore(store, columnName, cardId);
  const mainView = document.querySelector('.main');
  mainView.innerHTML = '';
  store.notify(store.getStore('main'));
};

const reflectStore = (store, columnName, cardId) => {
  const targetColumn = getColumnData(store, columnName);
  const targetColumnIdx = getColumnIdx(store, columnName);
  const removeCardData = getDataById(targetColumn, cardId);
  targetColumn.forEach(column => {
    column.tasks = column.tasks.filter(task => !removeCardData.includes(task));
    column.total = column.tasks.length;
  });

  targetColumnIdx.forEach((columnIdx, idx) => {
    store.getStore('main')[columnIdx] = targetColumn[idx];
  });
};

const getColumnData = (store, columnName) => {
  store = store.getStore('main');
  return store.filter(({ className }) => className === columnName);
};

const getColumnIdx = (store, columnName) => {
  store = store.getStore('main');
  return store
    .map(({ className }, idx) => {
      if (className === columnName) {
        return idx;
      }
    })
    .filter(idx => idx >= 0);
};

const getDataById = (columns, targetId) => {
  return columns.map(column => column['tasks'].filter(task => String(task.id) === String(targetId))[0]);
};

const removeAlert = () => {
  const alertElement = document.querySelector('.delete-alert-wrapper');
  if (!alertElement) {
    return;
  }
  alertElement.remove();
};
