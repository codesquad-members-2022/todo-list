class Board {
  render(columns) {
    document.querySelector('main').insertAdjacentHTML('afterbegin', this.template(columns));
  }

  template(columns) {
    return `<div class="column-wrap">
              ${columns}
            </div>`;
  }

  isfindedTarget(target, targetName) {
    return target.classList.contains(targetName) || target.closest('.' + targetName) ? true : false;
  }

  setTarget(target, targetName) {
    const $target = target.classList.contains(targetName) ? target : target.closest('.' + targetName);
    return $target;
  }

  reRenderSelectedCard(eventTarget, findedTarget, reRenderSelectedCard) {
    if (!this.isfindedTarget(eventTarget, findedTarget)) return;

    const $target = this.setTarget(eventTarget, 'card');
    reRenderSelectedCard($target);
  }

  reRenderSelectedColumn(eventTarget, findedTarget, observeViewModel) {
    if (!this.isfindedTarget(eventTarget, findedTarget)) return;

    const $target = this.setTarget(eventTarget, 'card');
    observeViewModel($target, 'PUT');
  }

  addClickEventListener(reRenderSelectedCard, observeViewModel) {
    document.querySelector('main').addEventListener('click', ({ target }) => {
      this.reRenderSelectedCard(target, 'card__button--cancle', reRenderSelectedCard);
      this.reRenderSelectedColumn(target, 'card__button--submit', observeViewModel);
    });
  }

  addDblClickEventListener(reRenderSelectedCard) {
    document
      .querySelector('main')
      .addEventListener('dblclick', ({ target }) => this.reRenderSelectedCard(target, 'card', reRenderSelectedCard));
  }

  addEvent(reRenderSelectedCard, reRenderSelectedColumn) {
    this.addClickEventListener(reRenderSelectedCard, reRenderSelectedColumn);
    this.addDblClickEventListener(reRenderSelectedCard);
  }
}

export { Board };
