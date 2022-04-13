const isEmptyInput = input => {
  if (!input) return true;
};

const checkBtnType = (btn) => {
  return btn.classList.contains('button--add') ? 'add' : 'cancle';
};

export { isEmptyInput, checkBtnType };
