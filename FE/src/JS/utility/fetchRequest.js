export async function fetchRequest(url, type = 'GET', request) {
  if(type === 'GET' || type === 'DELETE') {
    return await requestReadOrDelete(url, type);
  }
  return await requestCreateOrUpdate(url, type, request);
}

async function requestReadOrDelete(url, type) {
  const res = await fetch(url, { method: type });
  return res.json();
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
  return res.json();
}
