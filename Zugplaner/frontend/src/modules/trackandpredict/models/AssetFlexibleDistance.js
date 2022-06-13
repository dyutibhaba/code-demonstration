import PropTypes from 'prop-types';

export default class AssetFlexibleDistance {
  constructor(totalDistance) {
    this.totalDistance = totalDistance;
  }

  static empty() {
    return new AssetFlexibleDistance(0.00);
  }

  static fromBlob({
    totalDistance,
  }) {
    return new AssetFlexibleDistance(
      totalDistance,
    );
  }

  static get shape() {
    return PropTypes.shape({
      totalDistance: PropTypes.number,
    });
  }
}
