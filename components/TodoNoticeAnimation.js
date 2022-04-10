export default function TodoNoticeAnimation() {
  this.headerMenuElement = document.querySelector('.header__menu');
  this.alarmElement = document.querySelector('.alarm-wrapper');
  this.closeBtnElement = document.querySelector('.alarm__closeBtn');

  this.menuAnimation = () => {
    this.headerMenuElement.classList.toggle('no-display');
  };

  this.headerMenuElement.addEventListener('click', () => {
    this.alarmElement.style.animation = 'fadeInLeft 1s forwards';
    this.menuAnimation();
  });

  this.closeBtnElement.addEventListener('click', () => {
    this.alarmElement.style.animation = 'fadeInRight 1s';
    this.menuAnimation();
  });
}
