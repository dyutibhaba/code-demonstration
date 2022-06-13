import { FETCHING_NOT_FETCHED, FETCHING_ONGOING } from '../../../../../store/common/helpers';
import { CHECKLIST_TYPE_REGULAR_MAINTENANCE } from '../../../models/Checklist';

export const getCurrentRegularMaintenance = (state) => state
  .checklistsDictionary[state.currentChecklist];

export const getRegularMaintenance = (state) => state.checklists.map(
  (id) => state.checklistsDictionary[id],
).filter((item) => item.type === CHECKLIST_TYPE_REGULAR_MAINTENANCE);

export const areRegularMaintenanceLoading = (state) => (
  state.checklistsFetchingStatus === FETCHING_ONGOING
);

export const areRegularMaintenanceFetched = (state) => (
  state.checklistsFetchingStatus !== FETCHING_NOT_FETCHED
);
