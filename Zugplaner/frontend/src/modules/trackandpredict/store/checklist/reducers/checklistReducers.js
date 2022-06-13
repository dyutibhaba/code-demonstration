import {
  FETCHING_ERROR, FETCHING_NOT_FETCHED, FETCHING_ONGOING, FETCHING_SUCCESS,
} from '../../../../../store/common/helpers';
import {
  ASK_FOR_ACTIVITIES,
  ASK_FOR_ACTIVITIES_ERROR,
  ASK_FOR_ACTIVITIES_SUCCESS,
  ASK_FOR_CHECKLIST,
  ASK_FOR_CHECKLISTS,
  ASK_FOR_CHECKLISTS_ERROR,
  ASK_FOR_CHECKLISTS_SUCCESS,
  ASK_FOR_CHECKLIST_CREATION_SUCCESS,
  ASK_FOR_CHECKLIST_DELETION,
  ASK_FOR_CHECKLIST_DELETION_SUCCESS,
  ASK_FOR_CHECKLIST_ERROR,
  ASK_FOR_CHECKLIST_SUCCESS,
  ASK_FOR_SET_CURRENT_CHECKLIST,
  RESET_CHECKLISTS,
  ASK_FOR_MAINTENANCE_PLANS_SUCCESS,
  ASK_FOR_MAINTENANCE_PLAN_ERROR,
  ASK_FOR_MAINTENANCE_PLAN_SUCCESS,
  ASK_FOR_SET_CURRENT_MAINTENANCE_PLAN,
} from '../actions/checklistActions';

export const checklistsDictionary = (state = {}, action) => {
  switch (action.type) {
    case ASK_FOR_CHECKLISTS_SUCCESS:
      return action.payload.checklists.reduce((accumulator, checklist) => ({
        ...accumulator,
        [checklist.id]: checklist,
      }), state);
    case ASK_FOR_CHECKLIST_CREATION_SUCCESS:
    case ASK_FOR_CHECKLIST_SUCCESS:
      return {
        ...state,
        [action.payload.checklist.id]: action.payload.checklist,
      };
    case ASK_FOR_CHECKLIST_DELETION:
    case ASK_FOR_CHECKLIST_DELETION_SUCCESS:
    {
      const newState = { ...state };
      delete newState[action.payload.checklistId];
      return newState;
    }
    default:
      return state;
  }
};

export const checklists = (state = [], action) => { // NO SONAR
  switch (action.type) {
    case ASK_FOR_CHECKLISTS_SUCCESS:
      return action.payload.checklists.map((checklist) => checklist.id);
    case ASK_FOR_CHECKLIST_CREATION_SUCCESS:
      return [action.payload.checklist.id, ...state];
    case ASK_FOR_CHECKLIST_DELETION:
    case ASK_FOR_CHECKLIST_DELETION_SUCCESS:
      return state.filter((checklistId) => checklistId !== action.payload.checklistId);
    case RESET_CHECKLISTS:
      return [];
    default:
      return state;
  }
};

export const activities = (state = [], action) => { // NO SONAR
  switch (action.type) {
    case ASK_FOR_ACTIVITIES_SUCCESS:
      return action.payload.activities;
    case RESET_CHECKLISTS:
      return [];
    default:
      return state;
  }
};

export const currentMaintenancePlan = (state = null, action) => { // NO SONAR
  switch (action.type) {
    case ASK_FOR_SET_CURRENT_MAINTENANCE_PLAN:
      return action.payload.maintenancePlanId;
    default:
      return state;
  }
};

export const maintenancePlans = (state = [], action) => { // NO SONAR
  switch (action.type) {
    case ASK_FOR_MAINTENANCE_PLANS_SUCCESS:
      return action.payload.maintenancePlans;
    case RESET_CHECKLISTS:
      return [];
    default:
      return state;
  }
};

export const currentChecklist = (state = null, action) => { // NO SONAR
  switch (action.type) {
    case ASK_FOR_CHECKLIST_CREATION_SUCCESS:
    case ASK_FOR_CHECKLIST_SUCCESS:
      return action.payload.checklist.id;
    case ASK_FOR_MAINTENANCE_PLAN_SUCCESS:
      return action.payload.maintenancePlan.id;
    case ASK_FOR_SET_CURRENT_CHECKLIST:
      return action.payload.checklistId;
    case ASK_FOR_CHECKLISTS:
    case ASK_FOR_CHECKLIST_DELETION:
    case ASK_FOR_CHECKLIST:
    case ASK_FOR_CHECKLIST_ERROR:
    case ASK_FOR_MAINTENANCE_PLAN_ERROR:
      return null;
    default:
      return state;
  }
};

export const checklistsFetchingStatus = (state = FETCHING_NOT_FETCHED, action) => {
  switch (action.type) {
    case RESET_CHECKLISTS:
      return FETCHING_NOT_FETCHED;
    case ASK_FOR_CHECKLISTS:
      return FETCHING_ONGOING;
    case ASK_FOR_CHECKLISTS_SUCCESS:
      return FETCHING_SUCCESS;
    case ASK_FOR_CHECKLISTS_ERROR:
      return FETCHING_ERROR;
    default:
      return state;
  }
};

export const activitiesFetchingStatus = (state = FETCHING_NOT_FETCHED, action) => {
  switch (action.type) {
    case RESET_CHECKLISTS:
    case ASK_FOR_CHECKLIST_DELETION:
      return FETCHING_NOT_FETCHED;
    case ASK_FOR_ACTIVITIES:
      return FETCHING_ONGOING;
    case ASK_FOR_ACTIVITIES_SUCCESS:
      return FETCHING_SUCCESS;
    case ASK_FOR_ACTIVITIES_ERROR:
      return FETCHING_ERROR;
    default:
      return state;
  }
};
