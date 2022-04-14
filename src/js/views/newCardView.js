export const createNewCardTemplate = todoData => {
  return `
  <li class="task__card ${todoData ? 'edit-card' : 'new-card'}">
  <div class="card__contents">
    <header class="card__contents__header">
      <input class="card__contents__input--header" type="text" placeholder="제목을 입력하세요" value="${
        todoData ? todoData.header : ''
      }"/>
    </header>
    <main class="card__contents__main">
      <textarea class="card__contents__input--main" maxlength="500" placeholder="내용을 입력하세요">${
        todoData ? todoData.main : ''
      }</textarea>
    </main>
    <footer class="card__contents__footer footer__buttons">
      <input type=button class="footer__buttons__cancel" value="취소">
      ${
        todoData
          ? '<input type=button class="footer__buttons__edit" value="수정"/>'
          : '<input type=button class="footer__buttons__save" value="등록" disabled/>'
      }
    </footer>
  </div>
</li>
  `;
};

export const insertEditCard = (targetCard, todoData) => {
  const editCardTemplate = createNewCardTemplate(todoData);
  targetCard.insertAdjacentHTML('afterend', editCardTemplate);
  const editCardElement = document.querySelector('.edit-card');
  const newCardContents = editCardElement.querySelector('.card__contents');
  const textArea = editCardElement.querySelector('.card__contents__input--main');
  onNewCardInput(newCardContents);
  onResizeTextArea(textArea);
};

const onNewCardClick = (newCard, store, columnClassName) => {
  const saveBtn = newCard.querySelector('.footer__buttons__save');
  saveBtn.addEventListener('click', () => {
    const titleInputValue = newCard.querySelector('.card__contents__input--header').value;
    const textAreaValue = newCard.querySelector('.card__contents__input--main').value;
    saveNewCard(newCard, store, columnClassName, titleInputValue, textAreaValue);
  });
};

const saveNewCard = (newCard, store, columnClassName, ...userInput) => {
  const [header, main] = userInput;
  const taskData = {
    header,
    main,
    footer: 'author',
    datetime: new Date().toISOString(),
    status: columnClassName,
    cardId: Date.now(),
  };
  const columnData = store.getStore('main').find(column => column.className === columnClassName);
  columnData.tasks.unshift(taskData);
  columnData.total++;
  const columnElement = document.querySelector(`.${columnClassName}`);
  store.notify('newTodo', columnElement, taskData, columnData.total);
  newCard.parentElement.remove();
};

const onNewCardInput = newCard => {
  newCard.addEventListener('input', () => {
    changeSaveBtnStatus(newCard);
  });
};

const changeSaveBtnStatus = newCard => {
  const titleInput = newCard.querySelector('.card__contents__input--header');
  const textArea = newCard.querySelector('.card__contents__input--main');
  const saveBtn = newCard.querySelector('.footer__buttons__save')
    ? newCard.querySelector('.footer__buttons__save')
    : newCard.querySelector('.footer__buttons__edit');
  textArea.value && titleInput.value ? enableBtn(saveBtn) : disableBtn(saveBtn);
};

const disableBtn = btn => {
  btn.disabled = true;
  btn.style.backgroundColor = '#86c5ff';
  btn.style.cursor = '';
};

const enableBtn = btn => {
  btn.disabled = false;
  btn.style.backgroundColor = '#0175de';
  btn.style.cursor = 'pointer';
};

export const onAddBtnClick = store => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('click', ({ target }) => {
    handleAddBtnClick(mainElement, target, store);
  });
};

const handleAddBtnClick = (mainElement, target, store) => {
  if (!target.closest('.title-column__btn__add')) {
    return;
  }
  const columnClassName = target.closest(`.title-column__btn__add`).dataset.classname;
  const column = mainElement.querySelector(`.${columnClassName}`);
  const cardList = column.querySelector('.task__cards');
  toggleNewCard(cardList, store, columnClassName);
};

const resizeTextArea = textArea => {
  textArea.style.height = 'auto';
  textArea.style.height = textArea.scrollHeight + 'px';
};

const onResizeTextArea = textArea => {
  textArea.addEventListener('input', () => {
    resizeTextArea(textArea);
  });
};

const onCancelBtnClick = newCard => {
  const cancelBtn = newCard.querySelector('.footer__buttons__cancel');
  cancelBtn.addEventListener('click', () => {
    newCard.parentElement.remove();
  });
};

const toggleNewCard = (cardList, store, columnClassName) => {
  const newCard = cardList.querySelector('.new-card');
  if (newCard) {
    newCard.remove();
    return;
  }

  const newCardTemplate = createNewCardTemplate();
  cardList.insertAdjacentHTML('afterbegin', newCardTemplate);
  const newCardContents = cardList.querySelector('.card__contents');
  const textArea = cardList.querySelector('.card__contents__input--main');

  onNewCardInput(newCardContents);
  onResizeTextArea(textArea);
  onNewCardClick(newCardContents, store, columnClassName);
  onCancelBtnClick(newCardContents);
};
