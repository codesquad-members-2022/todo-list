export const createHeaderElement = icon => {
  const headerTemplate = `
    <div class="header__title">TO-DO LIST</div>
    <div class="header__layer-button">${icon}</div>
    `;
  const headerElement = document.createElement('header');
  headerElement.classList.add('header');
  headerElement.innerHTML = headerTemplate;
  return headerElement;
};
