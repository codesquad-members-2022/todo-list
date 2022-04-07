export const createCardTemplate = (task, icons) => {
  return `
    <li class="task__card" data-datetime=${task.datetime}>
    <div class="card__contents">
      <header class="card__contents__header">${task.header}</header>
      <main class="card__contents__main">${task.main}</main>
      <footer class="card__contents__footer">${task.footer}</footer>
    </div>
    <div class="card__delete-btn">
      ${icons.delete}
    </div>
  </li>
    `;
};
