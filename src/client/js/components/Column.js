import Component from '../core/Component.js';

class Column extends Component {
  setup() {
    this.$state = {
      tasks: [
        {
          title: '블로그에 포스팅',
          contents: 'Github 공부내용\n모던 자바스크립트 공부내용',
          columnId: 2,
          userId: 1,
          id: 1,
          user: {
            id: 1,
            name: 'web',
            password: '1234',
            profile: 'https://avatars.githubusercontent.com/u/67730358?v=4',
          },
          column: {
            name: 'Done',
            id: 2,
          },
        },
      ],
    };
  }

  template() {
    return `<div class='column-header'>
          <div class='column-header-left'>
            <h2 class='column-header-title'>${this.$state.tasks.column.name}</h2>
            <span class='badge'>${this.$state.tasks.length}</span>
          </div>
          <div class='column-header-right'>
            <button class='add-btn'>
              <svg width='14' height='14' viewBox='0 0 14 14' fill='none' xmlns='http://www.w3.org/2000/svg'>
                <path
                  d='M0.105713 7.53033L0.105713 6.46967H6.46967V0.105713H7.53033V6.46967H13.8943V7.53033H7.53033V13.8943H6.46967V7.53033H0.105713Z'
                  fill='#BDBDBD' />
              </svg>
            </button>
            <button class='delete-btn'>
              <svg width='12' height='12' viewBox='0 0 12 12' fill='none' xmlns='http://www.w3.org/2000/svg'>
                <path
                  d='M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z'
                  fill='#BDBDBD' />
              </svg>
            </button>
          </div>
        </div>
        <ol class='column-card-list'>
          <li>
            <div class='card'>
              <div class='card-header'>
                <h3 class='card-title'>${this.$state.tasks.title}</h3>
                <button class='close-btn'>
                  <svg width='12' height='12' viewBox='0 0 12 12' fill='none' xmlns='http://www.w3.org/2000/svg'>
                    <path
                      d='M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z'
                      fill='#828282' />
                  </svg>
                </button>
              </div>
              <p class='card-contents'>${this.$state.tasks.contents}</p>
              <span class='card-author'>author by ${this.$state.tasks.user.name}</span>
            </div>
          </li>
        </ol>`;
  }
}

export default Column;
