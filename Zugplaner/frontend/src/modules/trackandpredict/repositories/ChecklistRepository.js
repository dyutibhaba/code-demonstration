import { extractData, mapFunction } from '../../common/repositories/utils';
import Http from '../../common/services/Http';
import Checklist from '../models/Checklist';

export class ChecklistRepository {
  static getChecklistsFor(assetId) {
    return Http
      .get(`/checklists?assetId=${assetId}`)
      .then(extractData)
      .then(mapFunction(Checklist.fromBlob))
      .catch(() => 'fetching data error');
  }

  static getActivitiesFor(assetId) {
    return Http
      .get(`/checklists/activities?assetId=${assetId}`)
      .then(extractData)
      .then(mapFunction(Checklist.fromBlob))
      .catch(() => 'fetching data error');
  }

  static getMaintenancePlansFor(assetId) {
    return Http
      .get(`/maintenance-plan?assetId=${assetId}`)
      .then(extractData)
      .then(mapFunction(Checklist.fromBlob))
      .catch(() => 'fetching data error');
  }

  static getChecklistFor(assetId, checklistId) {
    return Http
      .get(`/checklists/${checklistId}?assetId=${assetId}`)
      .then(extractData)
      .then(Checklist.fromBlob)
      .catch(() => 'fetching data error');
  }

  static createChecklist(assetId, type, body, checklistId, maintenancePlanId) {
    const checklistParam = checklistId ? `&checklistId=${checklistId}` : '';
    const maintenancePlanIdParam = maintenancePlanId ? `&maintenancePlanId=${maintenancePlanId}` : '';
    return Http
      .post(`/checklists?assetId=${assetId}&type=${type}${checklistParam}${maintenancePlanIdParam}`, body)
      .then(extractData)
      .then(Checklist.fromBlob)
      .catch(() => 'fetching data error');
  }

  static createMaintenancePlan(assetId, type, body) {
    const mType = body.maintenanceType;
    const value = body.frequencyValue;
    const unit = body.frequencyType;
    const commentVal = body.comment === undefined ? '' : body.comment;
    const alarm = body.alarmIsSet ? body.alarmIsSet : false;
    return Http
      .post(`/maintenance-plan?assetId=${assetId}&maintenanceType=${mType}&alarmSet=${alarm}&frequencyType=${unit}&frequencyValue=${value}&comment=${commentVal}`)
      .then(extractData)
      .then(Checklist.fromBlob)
      .catch(() => 'fetching data error');
  }

  static updateMaintenancePlan(maintenancePlanId, body) {
    return Http
      .patch(`/maintenance-plan/${maintenancePlanId}`, body)
      .then(extractData)
      .then(Checklist.fromBlob)
      .catch(() => 'fetching data error');
  }

  static deleteMaintenancePlan(maintenancePlanId) {
    return Http
      .delete(`/maintenance-plan/${maintenancePlanId}`)
      .catch(() => 'fetching data error');
  }

  static deleteChecklist(checklistId) {
    return Http
      .delete(`/checklists/${checklistId}`)
      .catch(() => 'fetching data error');
  }

  static getMaintenanacePlanFor(assetId, maintenancePlanId) {
    return Http
      .get(`/maintenance-plan/${maintenancePlanId}?assetId=${assetId}`)
      .then(extractData)
      .then(Checklist.fromBlob)
      .catch(() => 'fetching data error');
  }
}

export default ChecklistRepository;
