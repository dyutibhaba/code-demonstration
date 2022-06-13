import {
  CHECKLIST_TYPE_DAMAGE_REPORT,
  CHECKLIST_TYPE_END_OF_SHIFT, CHECKLIST_TYPE_MAINTENANCE_PLAN,
  CHECKLIST_TYPE_REGULAR_MAINTENANCE,
  CHECKLIST_TYPE_START_OF_SHIFT,
} from '../../../models/Checklist';

export const ASK_FOR_CHECKLIST_CREATION = 'ASK_FOR_CHECKLIST_CREATION';
export const ASK_FOR_CHECKLIST_CREATION_SUCCESS = 'ASK_FOR_CHECKLIST_CREATION_SUCCESS';
export const ASK_FOR_CHECKLIST_CREATION_ERROR = 'ASK_FOR_CHECKLIST_CREATION_ERROR';
export const ASK_FOR_CHECKLIST_DELETION = 'ASK_FOR_CHECKLIST_DELETION';
export const ASK_FOR_CHECKLIST_DELETION_SUCCESS = 'ASK_FOR_CHECKLIST_DELETION_SUCCESS';
export const ASK_FOR_CHECKLIST_DELETION_ERROR = 'ASK_FOR_CHECKLIST_DELETION_ERROR';
export const ASK_FOR_CHECKLISTS = 'ASK_FOR_CHECKLISTS';
export const ASK_FOR_CHECKLISTS_SUCCESS = 'ASK_FOR_CHECKLISTS_SUCCESS';
export const ASK_FOR_CHECKLISTS_ERROR = 'ASK_FOR_CHECKLISTS_ERROR';
export const ASK_FOR_CHECKLIST = 'ASK_FOR_CHECKLIST';
export const ASK_FOR_CHECKLIST_SUCCESS = 'ASK_FOR_CHECKLIST_SUCCESS';
export const ASK_FOR_CHECKLIST_ERROR = 'ASK_FOR_CHECKLIST_ERROR';
export const ASK_FOR_SET_CURRENT_CHECKLIST = 'ASK_FOR_SET_CURRENT_CHECKLIST';
export const ASK_FOR_ACTIVITIES = 'ASK_FOR_ACTIVITIES';
export const ASK_FOR_ACTIVITIES_SUCCESS = 'ASK_FOR_ACTIVITIES_SUCCESS';
export const ASK_FOR_ACTIVITIES_ERROR = 'ASK_FOR_ACTIVITIES_ERROR';
export const RESET_CHECKLISTS = 'RESET_CHECKLISTS';
export const ASK_FOR_MAINTENANCE_PLAN_CREATION = 'ASK_FOR_MAINTENANCE_PLAN_CREATION';
export const ASK_FOR_MAINTENANCE_PLAN_CREATION_SUCCESS = 'ASK_FOR_MAINTENANCE_PLAN_CREATION_SUCCESS';
export const ASK_FOR_MAINTENANCE_PLAN_CREATION_ERROR = 'ASK_FOR_MAINTENANCE_PLAN_CREATION_ERROR';
export const ASK_FOR_MAINTENANCE_PLANS = 'ASK_FOR_MAINTENANCE_PLANS';
export const ASK_FOR_MAINTENANCE_PLANS_SUCCESS = 'ASK_FOR_MAINTENANCE_PLANS_SUCCESS';
export const ASK_FOR_MAINTENANCE_PLANS_ERROR = 'ASK_FOR_MAINTENANCE_PLANS_ERROR';
export const ASK_FOR_MAINTENANCE_PLAN_DELETION = 'ASK_FOR_MAINTENANCE_PLAN_DELETION';
export const ASK_FOR_MAINTENANCE_PLAN_DELETION_SUCCESS = 'ASK_FOR_MAINTENANCE_PLAN_DELETION_SUCCESS';
export const ASK_FOR_MAINTENANCE_PLAN_DELETION_ERROR = 'ASK_FOR_MAINTENANCE_PLAN_DELETION_ERROR';
export const ASK_FOR_MAINTENANCE_PLAN = 'ASK_FOR_MAINTENANCE_PLAN';
export const ASK_FOR_MAINTENANCE_PLAN_SUCCESS = 'ASK_FOR_MAINTENANCE_PLAN_SUCCESS';
export const ASK_FOR_MAINTENANCE_PLAN_ERROR = 'ASK_FOR_MAINTENANCE_PLAN_ERROR';
export const ASK_FOR_SET_CURRENT_MAINTENANCE_PLAN = 'ASK_FOR_SET_CURRENT_MAINTENANCE_PLAN';
export const ASK_FOR_MAINTENANCE_PLAN_UPDATION = 'ASK_FOR_MAINTENANCE_PLAN_UPDATION';

export const askForChecklistCreation = (
  assetId, type, body, checklistId, maintenancePlanId, history,
) => ({
  type: ASK_FOR_CHECKLIST_CREATION,
  payload: {
    assetId,
    type,
    body,
    checklistId,
    maintenancePlanId,
    history,
  },
});

export const askForMaintenancePlanChecklistCreation = (assetId, type, body, checklistId,
  maintenancePlanId, history) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_CREATION,
  payload: {
    assetId,
    type,
    body,
    history,
  },
});

export const askForMaintenancePlanUpdate = (assetId, maintenancePlanId, type, body, history) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_UPDATION,
  payload: {
    assetId,
    maintenancePlanId,
    type,
    body,
    history,
  },
});

export const askForStartOfShiftChecklistCreation = (
  assetId, body, checklistId, maintenancePlanId, history,
) => askForChecklistCreation(assetId, CHECKLIST_TYPE_START_OF_SHIFT, body, checklistId,
  maintenancePlanId, history);

export const askForEndOfShiftChecklistCreation = (
  assetId, body, checklistId, maintenancePlanId, history,
) => askForChecklistCreation(assetId, CHECKLIST_TYPE_END_OF_SHIFT, body, checklistId,
  maintenancePlanId, history);

export const askForDamageReportCreation = (
  assetId, body, checklistId, maintenancePlanId, history,
) => askForChecklistCreation(assetId, CHECKLIST_TYPE_DAMAGE_REPORT, body, checklistId,
  maintenancePlanId, history);

export const askForRegularMaintenanceCreation = (
  assetId, body, checklistId, maintenancePlanId, history,
) => askForChecklistCreation(assetId, CHECKLIST_TYPE_REGULAR_MAINTENANCE, body, checklistId,
  maintenancePlanId, history);

export const askForMaintenancePlanCreation = (
  assetId, body, checklistId, maintenancePlanId, history,
) => askForMaintenancePlanChecklistCreation(assetId, CHECKLIST_TYPE_MAINTENANCE_PLAN, body,
  checklistId, maintenancePlanId, history);

export const askForMaintenancePlanUpdation = (
  assetId, maintenancePlanId, body, history,
) => askForMaintenancePlanUpdate(assetId, maintenancePlanId, CHECKLIST_TYPE_MAINTENANCE_PLAN,
  body, history);

export const askForChecklistCreationSuccess = (checklist, history) => ({
  type: ASK_FOR_CHECKLIST_CREATION_SUCCESS,
  payload: {
    checklist,
    history,
  },
});

export const askForChecklistCreationError = (assetId) => ({
  type: ASK_FOR_CHECKLIST_CREATION_ERROR,
  payload: {
    assetId,
  },
});

export const askForChecklistDeletion = (checklistId, assetId, type, history) => ({
  type: ASK_FOR_CHECKLIST_DELETION,
  payload: {
    checklistId,
    assetId,
    type,
    history,
  },
});

export const askForChecklistDeletionSuccess = (checklistId, assetId, type, history) => ({
  type: ASK_FOR_CHECKLIST_DELETION_SUCCESS,
  payload: {
    checklistId,
    assetId,
    type,
    history,
  },
});

export const askForChecklistDeletionError = (checklistId) => ({
  type: ASK_FOR_CHECKLIST_DELETION_ERROR,
  payload: {
    checklistId,
  },
});

export const askForChecklists = (assetId) => ({
  type: ASK_FOR_CHECKLISTS,
  payload: {
    assetId,
  },
});

export const askForActivities = (assetId) => ({
  type: ASK_FOR_ACTIVITIES,
  payload: {
    assetId,
  },
});

export const askForActivitiesSuccess = (activities) => ({
  type: ASK_FOR_ACTIVITIES_SUCCESS,
  payload: {
    activities,
  },
});

export const askForActivitiesError = (assetId) => ({
  type: ASK_FOR_ACTIVITIES_ERROR,
  payload: {
    assetId,
  },
});

export const askForChecklistsSuccess = (checklists) => ({
  type: ASK_FOR_CHECKLISTS_SUCCESS,
  payload: {
    checklists,
  },
});

export const askForChecklistsError = (assetId) => ({
  type: ASK_FOR_CHECKLISTS_ERROR,
  payload: {
    assetId,
  },
});

export const askForResetChecklists = () => ({
  type: RESET_CHECKLISTS,
  payload: {},
});

export const askForChecklist = (assetId, checklistId) => ({
  type: ASK_FOR_CHECKLIST,
  payload: {
    assetId,
    checklistId,
  },
});

export const askForChecklistSuccess = (checklist) => ({
  type: ASK_FOR_CHECKLIST_SUCCESS,
  payload: {
    checklist,
  },
});

export const askForChecklistError = (assetId, checklistId) => ({
  type: ASK_FOR_CHECKLIST_ERROR,
  payload: {
    assetId,
    checklistId,
  },
});

export const askForSetCurrentChecklist = (checklistId) => ({
  type: ASK_FOR_SET_CURRENT_CHECKLIST,
  payload: {
    checklistId,
  },
});

export const askForSetCurrentMaintenancePlan = (maintenancePlanId) => ({
  type: ASK_FOR_SET_CURRENT_MAINTENANCE_PLAN,
  payload: {
    maintenancePlanId,
  },
});

export const askForMaintenancePlans = (assetId) => ({
  type: ASK_FOR_MAINTENANCE_PLANS,
  payload: {
    assetId,
  },
});

export const askForMaintenancePlansSuccess = (maintenancePlans) => ({
  type: ASK_FOR_MAINTENANCE_PLANS_SUCCESS,
  payload: {
    maintenancePlans,
  },
});

export const askForMaintenancePlansError = (assetId) => ({
  type: ASK_FOR_MAINTENANCE_PLANS_ERROR,
  payload: {
    assetId,
  },
});

export const askForMaintenancePlanCreationSuccess = (maintenancePlan, history) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_CREATION_SUCCESS,
  payload: {
    maintenancePlan,
    history,
  },
});

export const askForMaintenancePlanCreationError = (assetId) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_CREATION_ERROR,
  payload: {
    assetId,
  },
});

export const askForMaintenancePlanDeletion = (maintenancePlanId, assetId, type, history) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_DELETION,
  payload: {
    maintenancePlanId,
    assetId,
    type,
    history,
  },
});

export const askForMaintenancePlanDeletionSuccess = (
  maintenancePlanId, assetId, type, history,
) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_DELETION_SUCCESS,
  payload: {
    maintenancePlanId,
    assetId,
    type,
    history,
  },
});

export const askForMaintenancePlanDeletionError = (maintenancePlanId) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_DELETION_ERROR,
  payload: {
    maintenancePlanId,
  },
});

export const askForMaintenancePlan = (assetId, maintenancePlanId) => ({
  type: ASK_FOR_MAINTENANCE_PLAN,
  payload: {
    assetId,
    maintenancePlanId,
  },
});

export const askForMaintenancePlanSuccess = (maintenancePlan) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_SUCCESS,
  payload: {
    maintenancePlan,
  },
});

export const askForMaintenancePlanError = (assetId, maintenancePlanId) => ({
  type: ASK_FOR_MAINTENANCE_PLAN_ERROR,
  payload: {
    assetId,
    maintenancePlanId,
  },
});
