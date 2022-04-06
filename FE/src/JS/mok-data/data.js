export async function getData() {
  const successData = await fetch('/todo-list/FE/src/js/mok-data/mokData.json');
  const json = await successData.json();
  return json;
}
