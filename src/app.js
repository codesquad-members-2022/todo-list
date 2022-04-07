fetch('http://localhost:3000/mock')
  .then(response => response.json())
  .then(data => console.log(data));
