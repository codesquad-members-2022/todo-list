export const getLocalStorageByKey = key => {
  return JSON.parse(localStorage.getItem(key));
};
