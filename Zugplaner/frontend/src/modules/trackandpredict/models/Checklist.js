import PropTypes from 'prop-types';

import StartOfShiftBody from './StartOfShiftBody';
import RegularMaintenance from './RegularMaintenance';
import StartOfShiftChecklist from './StartOfShiftChecklist';
import EndOfShiftChecklist from './EndOfShiftChecklist';
import DamageReport from './DamageReport';
import MaintenanceParameters from './MaintenanceParameters';

export const CHECKLIST_NAMES = {
  1: 'maintenance.checklist.title.start.of.shift',
  2: 'maintenance.checklist.title.end.of.shift',
  3: 'maintenance.damages.title',
  4: 'maintenance.regular-maintenance.title',
  5: 'maintenance.maintenance-parameters.title',
};

export const CHECKLIST_TYPE_START_OF_SHIFT = 1;
export const CHECKLIST_TYPE_END_OF_SHIFT = 2;
export const CHECKLIST_TYPE_DAMAGE_REPORT = 3;
export const CHECKLIST_TYPE_REGULAR_MAINTENANCE = 4;
export const CHECKLIST_TYPE_MAINTENANCE_PLAN = 5;

export default class Checklist {
  static empty() {
    return new StartOfShiftChecklist('id', 'assetId', StartOfShiftBody.fromBlob({}), 1, 'userId', 0, '', null);
  }

  static fromBlob(blob) {
    switch (blob.type) {
      case CHECKLIST_TYPE_END_OF_SHIFT:
        return EndOfShiftChecklist.fromBlob(blob);
      case CHECKLIST_TYPE_START_OF_SHIFT:
        return StartOfShiftChecklist.fromBlob(blob);
      case CHECKLIST_TYPE_DAMAGE_REPORT:
        return DamageReport.fromBlob(blob);
      case CHECKLIST_TYPE_REGULAR_MAINTENANCE:
        return RegularMaintenance.fromBlob(blob);
      case CHECKLIST_TYPE_MAINTENANCE_PLAN:
        return MaintenanceParameters.fromBlob(blob);
      default:
        return {};
    }
  }

  static get shape() {
    return PropTypes.oneOfType([
      StartOfShiftChecklist.shape,
      EndOfShiftChecklist.shape,
      DamageReport.shape,
      RegularMaintenance.shape,
      MaintenanceParameters.shape,
    ]);
  }
}
