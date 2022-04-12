import { URL } from "./variable.js";

export const pipe = (...fns) => {
  return (value) => fns.reduce((acc, fn) => fn(acc), value);
};

export const request = {
  async allState() {
    const rawState = await (await fetch(`${URL}/columns`)).json();
    return rawState;
  },

  async deleteCard(columnID, cardID) {
    await fetch(`${URL}/columns/${columnID}/${cardID}/delete`, { method: "DELETE" });
  },

  async changeCard(columnID, cardID, requestBody) {
    const changedColumnState = await (
      await fetch(`http://localhost:3000/columns/${columnID}/${cardID}/update`, {
        method: "PATCH",
        body: JSON.stringify(requestBody),
        headers: { "Content-type": "application/json; charset=UTF-8" }
      })
    ).json();
    return changedColumnState;
  },

  async addCard(columnID, requestBody) {
    const updatedColumnState = await (
      await fetch(`http://localhost:3000/columns/${columnID}/add`, {
        method: "POST",
        body: JSON.stringify(requestBody),
        headers: { "Content-type": "application/json; charset=UTF-8" }
      })
    ).json();
    return updatedColumnState;
  }
};
