export async function deleteTodo(id) {
  await fetch('http://localhost:5000/todos' + id, {
    method: 'DELETE',
  })
    .then(json => {
      console.log(json);
    })
    .catch(error => {
      console.log(error);
    });
}
