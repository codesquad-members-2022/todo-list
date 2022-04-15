export const pipe =
  (...fns) =>
  (value) =>
    fns.reduce((result, fn) => fn(result), value);
