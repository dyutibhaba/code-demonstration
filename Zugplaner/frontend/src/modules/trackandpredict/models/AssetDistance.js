import PropTypes from 'prop-types';

export default class AssetDistance {
  // eslint-disable-next-line max-len
  constructor(id, assetId, date, distanceKm, dailyDistance,
    yesterdayDistance, thisMonthDistance, lastMonthDistance, monthlyTotals) {
    // eslint-disable-next-line prefer-rest-params
    this.id = id;
    this.assetId = assetId;
    this.date = date;
    this.distanceKm = distanceKm;
    this.dailyDistance = dailyDistance;
    this.yesterdayDistance = yesterdayDistance;
    this.thisMonthDistance = thisMonthDistance;
    this.lastMonthDistance = lastMonthDistance;
    this.monthlyTotals = monthlyTotals;
  }

  static fromBlob({
    id,
    assetId,
    date,
    distanceKm,
    dailyDistance,
    yesterdayDistance,
    thisMonthDistance,
    lastMonthDistance,
    monthlyTotals,
  }) {
    return {
      id,
      assetId,
      date,
      distanceKm,
      dailyDistance,
      yesterdayDistance,
      thisMonthDistance,
      lastMonthDistance,
      monthlyTotals,
    };
  }

  static get shape() {
    return PropTypes.shape({
      id: PropTypes.string,
      assetId: PropTypes.string,
      // eslint-disable-next-line react/forbid-prop-types
      date: PropTypes.any,
      distanceKm: PropTypes.number,
      dailyDistance: PropTypes.number,
      yesterdayDistance: PropTypes.number,
      thisMonthDistance: PropTypes.number,
      lastMonthDistance: PropTypes.number,
      monthlyTotals: PropTypes.arrayOf(PropTypes.shape({
        month: PropTypes.string,
        distance: PropTypes.number,
      })),
    });
  }
}
