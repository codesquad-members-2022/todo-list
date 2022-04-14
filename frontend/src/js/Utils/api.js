const SERVER_END_POINT = 'http://13.124.223.73:8080';

const headers = {
  Accept: 'application/json',
  'Content-Type': 'application/json',
};

const request = async (url, options = {}) => {
  try {
    const response = await fetch(`${SERVER_END_POINT}${url}`, {
      headers,
      ...options,
    });

    if (!response.ok) {
      throw new Error('api failed');
    }

    const data = await response.json();

    return data;
  } catch (error) {
    throw new Error(error);
  }
};

export const postCard = async ({ card }) => {
  return await request('/cards', {
    method: 'POST',
    body: JSON.stringify(card),
  });
};

export const deleteCard = async ({ cardId }) => {
  return await fetch(`${SERVER_END_POINT}/cards/${cardId}`, {
    method: 'DELETE',
    headers,
  });
};

export const patchCard = async ({ card, cardId }) => {
  return await request(`/cards/${cardId}`, {
    method: 'PATCH',
    body: JSON.stringify(card),
  });
};
// if (targetCard.classList.contains('edit')) {
//   patchCard({
//     card: {
//       author: 'web',
//       content: contentValue,
//       title: titleValue,
//     },
//     cardId,
//   });
// }

// const { content, title } = this.actionCard.model;
// targetColumn.model.updateAddStstue();

// const author = 'web';
// const columnId = targetColumnID;

// const response = await postCard({
//   card: {
//     author,
//     columnId,
//     content,
//     title,
//   },
// });
