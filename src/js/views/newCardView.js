export const createNewCardTemplate = () => {
  return `
  <li class="task__card new-card">
  <div class="card__contents">
    <header class="card__contents__header">
      <input class="card__contents__input--header" type="text" placeholder="제목을 입력하세요" />
    </header>
    <main class="card__contents__main">
      <textarea class="card__contents__input--main" maxlength="500" placeholder="내용을 입력하세요"></textarea>
    </main>
    <footer class="card__contents__footer footer__buttons">
      <input type=button class="footer__buttons__cancel" value="취소">
      <input type=button class="footer__buttons__save" value="등록" disabled/>
    </footer>
  </div>
</li>
  `;
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
  columnData.tasks.push(taskData);
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
  const saveBtn = newCard.querySelector('.footer__buttons__save');
  if (textArea.value && titleInput.value) {
    saveBtn.disabled = false;
    saveBtn.style.backgroundColor = '#0175de';
    saveBtn.style.cursor = 'pointer';
  } else {
    saveBtn.disabled = true;
    saveBtn.style.backgroundColor = '#86c5ff';
    saveBtn.style.cursor = '';
  }
};

export const onAddBtnClick = store => {
  const mainElement = document.querySelector('.main');
  mainElement.addEventListener('click', ({ target }) => {
    handleAddBtnClick(mainElement, target, store);
  });
};

const handleAddBtnClick = (mainElement, target, store) => {
  if (!target.closest(`.title-column__btn__add`)) {
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
  } else {
    const newCardTemplate = createNewCardTemplate();
    cardList.insertAdjacentHTML('afterbegin', newCardTemplate);
    const newCard = cardList.querySelector('.card__contents');
    const textArea = cardList.querySelector('.card__contents__input--main');
    onNewCardInput(newCard);
    onResizeTextArea(textArea);
    onNewCardClick(newCard, store, columnClassName);
    onCancelBtnClick(newCard);
  }
};
