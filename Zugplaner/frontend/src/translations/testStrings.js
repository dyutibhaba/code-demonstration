import en from './en.json';

const testStrings = {};
const keys = Object.keys(en);
keys.forEach(key => testStrings[key] = key);

export default testStrings;
