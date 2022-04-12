export const activationForm = (element, onDisable) => {
  if (onDisable) {
    element.disabled = true;
    element.classList.remove('bg-blue');
    element.classList.add('bg-sky-blue');
  } else {
    element.disabled = false;
    element.classList.remove('bg-sky-blue');
    element.classList.add('bg-blue');
  }
};
