fetch('http://localhost:3001/company', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    id: 4,
    name: 'Apple',
    country: 'USA',
  }),
})
  .then((res) => res.json())
  .then((data) => console.log(data));
