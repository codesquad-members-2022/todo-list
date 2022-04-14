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

export async function getTodos() {
  await fetch('http://localhost:5000/todos', {
    method: 'GET',
  })
    .then(json => {
      console.log(json);
      return json;
    })
    .catch(error => {
      console.log(error);
    });
}

export async function postTodo(todo) {
  await fetch('http://localhost:5000/todos', {
    method: 'POST',
    body: todo,
  })
    .then(json => {
      console.log(json);
      return json;
    })
    .catch(error => {
      console.log(error);
    });
}
