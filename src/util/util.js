export const pipe = (...fns) => {
  return (value) => fns.reduce((acc, fn) => fn(acc), value);
};
