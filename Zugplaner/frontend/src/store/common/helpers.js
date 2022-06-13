import moment from 'moment';

export const FETCHING_ONGOING = 'FETCHING_ONGOING';
export const FETCHING_ERROR = 'FETCHING_ERROR';
export const FETCHING_SUCCESS = 'FETCHING_SUCCESS';
export const FETCHING_NOT_FETCHED = 'FETCHING_NOT_FETCHED';
export const DEFAULT_SHOP_CURRENCY = '€';
export const DEFAULT_DISTANCE_UNIT = ' km';
export const DATE_FORMAT = ' YYYY-MM-DD';

export const toGermanNumberFormat = (price) => Number(price).toLocaleString(
  'de-DE', { minimumFractionDigits: 2 },
) + DEFAULT_SHOP_CURRENCY;

export const toEuropeanNumberFormatDistance = (price) => Number(price).toLocaleString(
  'de-DE', { minimumFractionDigits: 2, maximumFractionDigits: 2 },
) + DEFAULT_DISTANCE_UNIT;

export const formatDate = (date) => moment(date, DATE_FORMAT).format().slice(0, 10);

export const toFirstName = (str) => ((!str) ? 'user' : str.split(' ')[0]);
