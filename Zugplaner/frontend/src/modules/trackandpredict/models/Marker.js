import { marker as MarkerLeaflet, divIcon } from 'leaflet';

export default class Marker {
  static fromTracker(
    {
      latitude, longitude, description, etaVariation, name, id,
    },
    selectedLatitude,
    selectedLongitude,
  ) {
    return new MarkerLeaflet([latitude, longitude], {
      icon: this.defineIcon(
        latitude,
        longitude,
        selectedLatitude,
        selectedLongitude,
        name || description,
        etaVariation,
      ),
      trackerId: id,
    });
  }

  static defineIcon(trackerLatitude,
    trackerLongitude, selectedLatitude, selectedLongitude, description, etaVariation) {
    const isActive = trackerLatitude === selectedLatitude
      && trackerLongitude === selectedLongitude ? 'active train-active' : 'custom-div-icon';
    const isDelayed = etaVariation > 0 ? 'delayed' : null;

    return divIcon({
      className: `${isActive}`,
      html: `<div style='background-color:#fff;' class='marker-pin ${isDelayed}'></div><span class='asset-name'>${description}</span>`,
      iconSize: [60, 60],
      iconAnchor: [30, 60],
      popupAnchor: [0, -35],
    });
  }
}
