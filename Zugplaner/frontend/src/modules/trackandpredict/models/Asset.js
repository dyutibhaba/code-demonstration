import PropTypes from 'prop-types';

export default class Asset {
  static empty() {
    return new Asset('', '', 0, '', '', 0.0, 0.0, 0, '', '', null, 0);
  }

  static fromBlob({
    name,
    batteryPercentage,
    batteryVoltage,
    lastLocationDateTime,
    positionAddress,
    latitude,
    longitude,
    speedKph,
    id,
    eta,
    etaVariation,
    damageStatusFlag,
  }) {
    return new Asset(
      name,
      batteryPercentage,
      batteryVoltage,
      lastLocationDateTime,
      positionAddress,
      latitude,
      longitude,
      speedKph,
      id,
      eta,
      etaVariation,
      damageStatusFlag,
    );
  }

  static fromRowData({
    name,
    batteryPercentage,
    batteryVoltage,
    lastLocationDateTime,
    positionAddress,
    latitude,
    longitude,
    speedKph,
    id,
    eta,
    etaVariation,
    damageStatusFlag,
  }) {
    return new Asset(
      name,
      batteryPercentage,
      batteryVoltage,
      lastLocationDateTime,
      positionAddress,
      latitude,
      longitude,
      speedKph,
      id,
      eta,
      etaVariation,
      damageStatusFlag,
    );
  }

  static get shape() {
    return PropTypes.shape({
      name: PropTypes.string,
      batteryPercentage: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
      batteryVoltage: PropTypes.number,
      lastLocationDateTime: PropTypes.string,
      positionAddress: PropTypes.string,
      latitude: PropTypes.number,
      longitude: PropTypes.number,
      positionGPS: PropTypes.string,
      speedKph: PropTypes.number,
      id: PropTypes.string,
      eta: PropTypes.string,
      etaVariation: PropTypes.number,
      damageStatusFlag: PropTypes.number,
    });
  }

  constructor(
    name,
    batteryPercentage,
    batteryVoltage,
    lastLocationDateTime,
    positionAddress,
    latitude,
    longitude,
    speedKph,
    id,
    eta,
    etaVariation,
    damageStatusFlag,
  ) {
    this.name = name;
    this.batteryPercentage = batteryPercentage;
    this.batteryVoltage = batteryVoltage;
    this.lastLocationDateTime = lastLocationDateTime;
    this.positionAddress = positionAddress;
    this.latitude = latitude;
    this.longitude = longitude;
    this.positionGPS = `${latitude}, ${longitude}`;
    this.speedKph = speedKph;
    this.id = id;
    this.eta = eta;
    this.etaVariation = this.eta ? etaVariation : null;
    this.damageStatusFlag = damageStatusFlag;
  }
}
