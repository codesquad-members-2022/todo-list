export async function fetchRequest(url, type = 'GET', request) {
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
  return await res.json();
}
