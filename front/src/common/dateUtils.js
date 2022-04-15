const { pipe } = require("./utils");

export const getISODateDiff = (aISODate, bISODate) => {
  return new Date(aISODate) - new Date(bISODate);
};

const getDateDiffInOrder = ({ prevDate, nextDate }) => {
  return new Date(nextDate) - new Date(prevDate);
};

export const second = (s) => {
  return s * 1000;
};

export const minute = (m) => {
  return m * 60 * 1000;
};

export const hour = (h) => {
  return h * 60 * 60 * 1000;
};

const diffToString = (date) => {
  if (date < 1000) return "방금 전";

  const dateSecond = Math.floor(date / 1000);
  const dateMinute = Math.floor(dateSecond / 60);
  const dateHour = Math.floor(dateMinute / 60);
  const dateDay = Math.floor(dateHour / 24);
  const dateMonth = Math.floor(dateDay / 30);
  const dateYear = Math.floor(dateMonth / 12);
  const dateValue = [
    dateYear,
    dateMonth,
    dateDay,
    dateHour,
    dateMinute,
    dateSecond,
  ];
  const dateUnit = ["년", "월", "일", "시간", "분", "초"];
  const biggestUnitIdx = dateValue.findIndex((value) => value !== 0);
  return `${dateValue[biggestUnitIdx]} ${dateUnit[biggestUnitIdx]}전`;
};

export const getDateDiffFormat = ({ prev, next }) => {
  return pipe(
    getDateDiffInOrder,
    diffToString
  )({ prevDate: prev, nextDate: next });
};

export const getMongoNow = () => {
  const now = new Date();
  const mongoDiff = hour(9);
  const mongoNow = new Date(now - mongoDiff);
  return mongoNow.toISOString();
};
