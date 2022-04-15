import { URL } from "./variable.js";

export const pipe = (...fns) => {
  return (value) => fns.reduce((acc, fn) => fn(acc), value);
};

export const insertNodeBefore = (targetNode, referenceNode) => {
  referenceNode.parentNode.insertBefore(targetNode, referenceNode);
};

export const insertNodeAfter = (targetNode, referenceNode) => {
  referenceNode.parentNode.insertBefore(targetNode, referenceNode.nextSibling);
};

export const removeNodeself = (node) => {
  node.parentNode.removeChild(node);
};

export const hasClassName = (element, className) => {
  return element.classList.contains(className);
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
      await fetch(`${URL}/columns/${columnID}/${cardID}/update`, {
        method: "PATCH",
        body: JSON.stringify(requestBody),
        headers: { "Content-type": "application/json; charset=UTF-8" }
      })
    ).json();
    return changedColumnState;
  },

  async addCard(columnID, requestBody) {
    const updatedColumnState = await (
      await fetch(`${URL}/columns/${columnID}/add`, {
        method: "POST",
        body: JSON.stringify(requestBody),
        headers: { "Content-type": "application/json; charset=UTF-8" }
      })
    ).json();
    return updatedColumnState;
  }
};

