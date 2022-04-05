export default function TodoHeader() {
  this.headerMenuElement = document.querySelector('.header__menu');
  this.alarmElement = document.querySelector('.alarm-wrapper');
  this.closeBtnElement = document.querySelector('.alarm__closeBtn');

  this.menuAnimation = () => {
    this.alarmElement.classList.toggle('no-display');
    this.headerMenuElement.classList.toggle('no-display');
  };

  this.headerMenuElement.addEventListener('click', () => {
    this.menuAnimation();
  });

  this.closeBtnElement.addEventListener('click', () => {
    this.menuAnimation();
  });
}
