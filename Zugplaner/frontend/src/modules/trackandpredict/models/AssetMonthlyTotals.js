import PropTypes from 'prop-types';

export default class AssetMonthlyTotals {
  static empty() {
    return new AssetMonthlyTotals('', 0.00);
  }

  static fromBlob({
    month,
    distance,
  }) {
    return new AssetMonthlyTotals(
      month,
      distance,
    );
  }

  static get shape() {
    return PropTypes.shape({
      month: PropTypes.string,
      distance: PropTypes.number,
    });
  }

  constructor(
    month,
    distance,
  ) {
    this.month = month;
    this.distance = distance;
  }
}
