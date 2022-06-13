import { ofType } from 'redux-observable';
import { EMPTY, of } from 'rxjs';
import { ignoreElements, map, mergeMap } from 'rxjs/operators';
import {
  CHECKLIST_TYPE_DAMAGE_REPORT,
  CHECKLIST_TYPE_END_OF_SHIFT,
  CHECKLIST_TYPE_REGULAR_MAINTENANCE,
  CHECKLIST_TYPE_MAINTENANCE_PLAN,
  CHECKLIST_TYPE_START_OF_SHIFT,

} from '../../../models/Checklist';
import {
  askForChecklists,
  askForMaintenancePlans,
  ASK_FOR_CHECKLIST_CREATION_SUCCESS,
  ASK_FOR_CHECKLIST_DELETION_SUCCESS,
  ASK_FOR_MAINTENANCE_PLAN_DELETION_SUCCESS,
} from '../actions/checklistActions';

export const redirectToChecklistTableEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_DELETION_SUCCESS),
  map((action) => {
    const { history, assetId } = action.payload;
    if (history) {
      history.push(`/track-and-predict/maintenance-asset/${assetId}/checklists`);
    }
  }),
  ignoreElements(),
);

export const redirectToChecklistsTableEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_CREATION_SUCCESS),
  map((action) => {
    const { history, checklist } = action.payload;
    if (history && (
      checklist.type === CHECKLIST_TYPE_START_OF_SHIFT
      || checklist.type === CHECKLIST_TYPE_END_OF_SHIFT)) {
      history.push(`/track-and-predict/maintenance-asset/${checklist.assetId}/checklists`);
    }
  }),
  ignoreElements(),
);

export const refreshChecklistTableAfterDeletionEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_DELETION_SUCCESS),
  mergeMap((action) => {
    const { history, assetId, type } = action.payload;
    return (!history && type !== CHECKLIST_TYPE_DAMAGE_REPORT)
      ? of(askForChecklists(assetId)) : EMPTY;
  }),
);

export const redirectToDamagesTableEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_CREATION_SUCCESS),
  map((action) => {
    const { history, checklist } = action.payload;
    if (history && checklist.type === CHECKLIST_TYPE_DAMAGE_REPORT) {
      history.push(`/track-and-predict/maintenance-asset/${checklist.assetId}/damages`);
    }
  }),
  ignoreElements(),
);

export const redirectToRegularMaintenanceTableEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_CREATION_SUCCESS),
  map((action) => {
    const { history, checklist } = action.payload;
    if (history && checklist.type === CHECKLIST_TYPE_REGULAR_MAINTENANCE) {
      history.push(`/track-and-predict/maintenance-asset/${checklist.assetId}/maintenance-log`);
    }
  }),
  ignoreElements(),
);

export const redirectToMaintenancePlanTableEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_CREATION_SUCCESS),
  map((action) => {
    const { history, checklist } = action.payload;
    if (history && checklist.type === CHECKLIST_TYPE_MAINTENANCE_PLAN) {
      history.push(`/track-and-predict/maintenance-asset/${checklist.assetId}/maintenance-parameters`);
    }
  }),
  ignoreElements(),
);

export const refreshMaintenancePlanTableAfterDeletionEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_MAINTENANCE_PLAN_DELETION_SUCCESS),
  mergeMap((action) => {
    const { history, assetId, type } = action.payload;
    return (!history && type !== CHECKLIST_TYPE_DAMAGE_REPORT)
      ? of(askForMaintenancePlans(assetId)) : EMPTY;
  }),
);

export default redirectToChecklistTableEpic;
