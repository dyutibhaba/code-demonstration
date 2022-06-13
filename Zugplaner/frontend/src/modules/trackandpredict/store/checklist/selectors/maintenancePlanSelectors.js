import { FETCHING_NOT_FETCHED, FETCHING_ONGOING } from '../../../../../store/common/helpers';

export const getCurrentMaintenancePlan = (state) => state.maintenancePlans
  .filter((maintenancePlan) => maintenancePlan.id === state.currentMaintenancePlan).shift();

export const getMaintenancePlans = (state) => state.maintenancePlans;

export const areMaintenancePlanLoading = (state) => (
  state.checklistsFetchingStatus === FETCHING_ONGOING
);

export const areMaintenancePlanFetched = (state) => (
  state.checklistsFetchingStatus !== FETCHING_NOT_FETCHED
);

export const maintenancePlanNeedRefresh = (state) => (
  !areMaintenancePlanLoading(state) && !areMaintenancePlanFetched(state)
);
