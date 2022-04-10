import { icons } from '../constants/constant.js';

export const createHeaderElement = () => {
  const headerTemplate = `
    <div class="header__title">TO-DO LIST</div>
    <div class="header__layer-button">${icons.menu}</div>
    `;
  const headerElement = document.createElement('header');
  headerElement.classList.add('header');
  headerElement.innerHTML = headerTemplate;
  return headerElement;
};
