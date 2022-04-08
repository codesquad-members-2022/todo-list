import { LowSync, LocalStorage } from 'lowdb';

const db = new LowSync(new LocalStorage('store'));

// 여기에 데이터 get, post, delete, patch, move 메서드 추가해서 export 하면 될 듯 합니다.

export default db;
