export async function fetchRequest(url, type = 'GET', request) {
<<<<<<< HEAD
  switch (type) {
    case 'GET':
    case 'DELETE':
      return await requestReadOrDelete(url, type);
    case 'POST':
    case 'PUT':
    case 'PATCH':
      return await requestCreateOrUpdate(url, type, request);
  }
}

async function requestReadOrDelete(url, type) {
  return fetch(url, { method: type }).then((res) => res.json());
=======
  if(type === 'GET' || type === 'DELETE') {
    return await requestReadOrDelete(url, type);
  }
  return await requestCreateOrUpdate(url, type, request);
}

async function requestReadOrDelete(url, type) {
  const res = await fetch(url, { method: type });
  return res.json();
>>>>>>> 4ff23090e90e6f46854e1cc08278107db1840297
}

// POST는 create 시에만, 전체 데이터 수정은 PUT, 일부 데이터 수정은 PATCH
async function requestCreateOrUpdate(url, type, request) {
  const res = await fetch(url, {
    method: type,
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(request),
  });
<<<<<<< HEAD
  return await res.json();
=======
  return res.json();
>>>>>>> 4ff23090e90e6f46854e1cc08278107db1840297
}
