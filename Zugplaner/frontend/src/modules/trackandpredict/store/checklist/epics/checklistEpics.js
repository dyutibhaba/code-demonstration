import { ofType } from 'redux-observable';
import { mergeMap } from 'rxjs/operators';
import { ChecklistRepository } from '../../../repositories/ChecklistRepository';
import { isChecklistLoaded } from '../selectors/checklistSelectors';
import {
  askForChecklistCreationError,
  askForChecklistCreationSuccess,
  askForChecklistDeletionError,
  askForChecklistDeletionSuccess,
  askForChecklistsError,
  askForChecklistsSuccess,
  askForChecklistError,
  askForChecklistSuccess,
  askForMaintenancePlansSuccess,
  askForMaintenancePlansError,
  ASK_FOR_CHECKLISTS,
  ASK_FOR_CHECKLIST_CREATION,
  ASK_FOR_CHECKLIST_DELETION,
  ASK_FOR_CHECKLIST,
  askForSetCurrentChecklist,
  ASK_FOR_ACTIVITIES,
  ASK_FOR_MAINTENANCE_PLANS,
  askForActivitiesSuccess,
  askForActivitiesError,
  ASK_FOR_MAINTENANCE_PLAN_CREATION,
  ASK_FOR_MAINTENANCE_PLAN_UPDATION,
  ASK_FOR_MAINTENANCE_PLAN_DELETION,
  askForMaintenancePlanDeletionSuccess,
  askForMaintenancePlanDeletionError,
  ASK_FOR_MAINTENANCE_PLAN,
  askForMaintenancePlanSuccess,
  askForMaintenancePlanError,
} from '../actions/checklistActions';

export const getChecklistsEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLISTS),
  mergeMap(async (action) => {
    const { assetId } = action.payload;
    const checklists = await ChecklistRepository.getChecklistsFor(assetId);
    if (typeof checklists !== 'string') {
      return askForChecklistsSuccess(checklists);
    }
    return askForChecklistsError(assetId);
  }),
);

export const getActivitiesEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_ACTIVITIES),
  mergeMap(async (action) => {
    const { assetId } = action.payload;
    const activities = await ChecklistRepository.getActivitiesFor(assetId);
    if (typeof activities !== 'string') {
      return askForActivitiesSuccess(activities);
    }
    return askForActivitiesError(assetId);
  }),
);

export const getChecklistEpic = (action$, state$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST),
  mergeMap(async (action) => {
    const { assetId, checklistId } = action.payload;
    const checklistLoaded = isChecklistLoaded(checklistId)(state$.value);
    if (checklistLoaded) {
      return askForSetCurrentChecklist(checklistId);
    }
    const checklist = await ChecklistRepository.getChecklistFor(assetId, checklistId);
    if (typeof checklists !== 'string') {
      return askForChecklistSuccess(checklist);
    }
    return askForChecklistError(assetId);
  }),
);

export const checklistCreationEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_CREATION),
  mergeMap(async (action) => {
    const {
      assetId, type, body, checklistId, maintenancePlanId, history,
    } = action.payload;
    const checklist = await ChecklistRepository
      .createChecklist(assetId, type, body, checklistId, maintenancePlanId);
    if (typeof checklist !== 'string') {
      return askForChecklistCreationSuccess(checklist, history);
    }
    return askForChecklistCreationError(assetId);
  }),
);

export const checklistDeletionEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_CHECKLIST_DELETION),
  mergeMap(async (action) => {
    const {
      checklistId, assetId, type, history,
    } = action.payload;
    const response = await ChecklistRepository.deleteChecklist(checklistId);
    if (response.status === 204) {
      return askForChecklistDeletionSuccess(checklistId, assetId, type, history);
    }
    return askForChecklistDeletionError(checklistId);
  }),
);

export const maintenancePlanCreationEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_MAINTENANCE_PLAN_CREATION),
  mergeMap(async (action) => {
    const {
      assetId, type, body, history,
    } = action.payload;
    const maintenancePlan = await ChecklistRepository.createMaintenancePlan(assetId, type, body);
    if (typeof maintenancePlan !== 'string') {
      return askForChecklistCreationSuccess(maintenancePlan, history);
    }
    return askForChecklistCreationError(assetId);
  }),
);

export const maintenancePlanUpdateEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_MAINTENANCE_PLAN_UPDATION),
  mergeMap(async (action) => {
    const {
      assetId, maintenancePlanId, body, history,
    } = action.payload;
    const maintenancePlan = await ChecklistRepository.updateMaintenancePlan(
      maintenancePlanId, body,
    );
    if (typeof maintenancePlan !== 'string') {
      return askForChecklistCreationSuccess(maintenancePlan, history);
    }
    return askForChecklistCreationError(assetId);
  }),
);

export const getMaintenancePlansEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_MAINTENANCE_PLANS),
  mergeMap(async (action) => {
    const { assetId } = action.payload;
    const maintenancePlans = await ChecklistRepository.getMaintenancePlansFor(assetId);
    if (typeof maintenancePlans !== 'string') {
      return askForMaintenancePlansSuccess(maintenancePlans);
    }
    return askForMaintenancePlansError(assetId);
  }),
);

export const maintenancePlanDeletionEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_MAINTENANCE_PLAN_DELETION),
  mergeMap(async (action) => {
    const {
      maintenancePlanId, assetId, type, history,
    } = action.payload;
    const response = await ChecklistRepository.deleteMaintenancePlan(maintenancePlanId);
    if (response.status === 204) {
      return askForMaintenancePlanDeletionSuccess(maintenancePlanId, assetId, type, history);
    }
    return askForMaintenancePlanDeletionError(maintenancePlanId);
  }),
);

export const getMaintenancePlanEpic = (action$) => action$.pipe(
  ofType(ASK_FOR_MAINTENANCE_PLAN),
  mergeMap(async (action) => {
    const { assetId, maintenancePlanId } = action.payload;
    const maintenancePlan = await ChecklistRepository
      .getMaintenanacePlanFor(assetId, maintenancePlanId);
    if (typeof maintenancePlan !== 'string') {
      return askForMaintenancePlanSuccess(maintenancePlan);
    }
    return askForMaintenancePlanError(assetId);
  }),
);

export default checklistCreationEpic;
