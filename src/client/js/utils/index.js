export const calculateTimeDiff = (date) => {
  const now = new Date();
  const executed = new Date(date);
  const diff = parseInt((now.getTime() - executed.getTime()) / 1000 / 60);
  if (diff < 60) return `${diff}분 전`;
  else if (diff < 1440) return `${parseInt(diff / 60)}시간 전`;
  else return `${parseInt(diff / 1440)}일 전`;
}
