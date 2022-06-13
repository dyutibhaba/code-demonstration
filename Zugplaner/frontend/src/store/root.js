import { combineEpics } from 'redux-observable';
import { combineReducers } from 'redux';
import {
  photosIds,
  photos,
  checkinForms,
  currentCheckinForm,
  currentTerminalId,
  licensePlate,
  checkinFormUpdateSubmission,
} from '../modules/digitalcheckin/store/reducers/digitalCheckInReducers';
import {
  trackers,
  trackersFetchingStatus,
  assignedTracker,
  assetsWithState,
  alertAssignTracker,
} from '../modules/admin/store/tracker/reducers/trackerReducers';
import {
  members,
  membersFetchingStatus,
} from '../modules/admin/store/member/reducers/memberReducers';
import { currentUser } from '../modules/common/store/reducers/userReducers';
import {
  getAllCheckinFormsEpic,
  getContainerRecognitionEpic,
  getLicensePlateRecognitionEpic,
  getTerminalsOfUser,
  registerNewCheckinform,
  deleteCheckinFormEpic,
  validateCheckinFormEpic,
} from '../modules/digitalcheckin/store/epics/digitalCheckinEpics';
import isLoading from '../modules/digitalcheckin/store/reducers/loaderReducers';
import { loginBackendEpic } from '../modules/common/store/epics/userEpics';
import {
  getCompanyAssetsEpic,
  assignAssetToUserGroupEpic,
  editAssetEpic,
  deleteAssetEpic,
  getUserAssetsEpic,
  getUserAssetsSilentlyEpic,
} from '../modules/admin/store/assets/epics/assetEpics';
import {
  getCompanyMembersEpic, memberRemoval, memberAddition, memberEdition, getCompanyMembersByAssetEpic,
} from '../modules/admin/store/member/epics/memberEpics';
import { getCompaniesEpic, deleteCompanyEpic } from '../modules/admin/store/company/epics/companyEpics';
import { companyIds, companies } from '../modules/admin/store/company/reducers/companyReducers';
import {
  checklistCreationEpic, checklistDeletionEpic, getChecklistsEpic,
  getChecklistEpic, getActivitiesEpic, maintenancePlanCreationEpic,
  getMaintenancePlansEpic, maintenancePlanDeletionEpic, getMaintenancePlanEpic,
  maintenancePlanUpdateEpic,
} from '../modules/trackandpredict/store/checklist/epics/checklistEpics';
import {
  currentChecklist,
  checklists, checklistsFetchingStatus, checklistsDictionary, activities, activitiesFetchingStatus,
  maintenancePlans, currentMaintenancePlan,
} from '../modules/trackandpredict/store/checklist/reducers/checklistReducers';
import {
  redirectToChecklistTableEpic,
  redirectToDamagesTableEpic,
  refreshChecklistTableAfterDeletionEpic,
  redirectToRegularMaintenanceTableEpic,
  redirectToChecklistsTableEpic,
  redirectToMaintenancePlanTableEpic,
  refreshMaintenancePlanTableAfterDeletionEpic,
} from '../modules/trackandpredict/store/checklist/epics/navigationChecklistEpic';
import { getDelayCausesEpic, recodeDelayCauseEpic, recodeDelayCauseSucceededEpic } from '../modules/delay-manager/store/epics/delayCausesEpics';
import { redirectToDelayCausesTableEpic } from '../modules/delay-manager/store/epics/navigationDelayManagerEpics';
import { delayCauses, delayCausesFetchingStatus } from '../modules/delay-manager/store/reducers/delayCausesReducers';
import { trainRuns, trainRunsFetchingStatus } from '../modules/delay-manager/store/reducers/trainRunsReducers';
import { getTrainRunsEpic } from '../modules/delay-manager/store/epics/trainRunsEpics';
import {
  recodedDelayCausesList,
  recodedDelayCausesFetchingStatus,
  isXlsFileDownloading,
} from '../modules/delay-manager/store/reducers/recodedDelayCausesReducers';
import {
  deleteRecodedDelayCauseEpic,
  getRecodedDelayCausesEpic,
  sendRecodedDelayCausesEpic,
  updateRecodedDelayCausesEpic,
} from '../modules/delay-manager/store/epics/recodedDelayCausesEpics';
import { delayDate } from '../modules/delay-manager/store/reducers/delayDateReducer';
import { orderDetails, updatedArticles } from '../modules/marketplace/store/reducers/shopReducers';
import { createOrderEpic, getAllArticlesEpic } from '../modules/marketplace/store/epics/shopEpics';
import { forwardToOrderDetailsPageEpic } from '../modules/marketplace/store/epics/navigationShopEpics';
import { getAssetDistanceEpic, getAssetFlexibleDistanceEpic } from '../modules/trackandpredict/store/checklist/epics/assetDistanceEpics';
import { assetDistance, assetFlexibleDistance } from '../modules/trackandpredict/store/checklist/reducers/assetDistanceReducers';
import {
  createUserToModuleRolesEpic,
  getUserToModuleRolesEpic, refreshUserModuleRoleTableAfterDeletionEpic,
  updateUserToModuleRolesEpic, userModuleRoleDeletionEpic,
} from '../modules/admin/store/usertomodulerole/epics/userToModuleRoleEpics';
import {
  currentUserModuleRoleRelation,
  usersAndModuleRoles,
} from '../modules/admin/store/usertomodulerole/reducers/userToModuleRoleReducers';
import {
  addressesFetchingStatus,
  addresses,
} from '../modules/transportorder/store/reducers/addressesReducers';
import { getAddressesEpic } from '../modules/transportorder/store/epics/addressesEpics';
import { addressGroupFetchingStatus, addressGroups } from '../modules/transportorder/store/reducers/addressGroupReducers';
import {
  addressGroupCreateEpic,
  addressGroupDeletionEpic, addressGroupMultiUpdateEpic,
  addressGroupUpdateEpic,
  getAddressGroupsEpic,
} from '../modules/transportorder/store/epics/addressGroupEpics';

export const rootEpic = combineEpics(
  deleteCheckinFormEpic,
  deleteCompanyEpic,
  validateCheckinFormEpic,
  getAllCheckinFormsEpic,
  getContainerRecognitionEpic,
  getLicensePlateRecognitionEpic,
  registerNewCheckinform,
  getTerminalsOfUser,
  loginBackendEpic,
  getCompaniesEpic,
  getCompanyAssetsEpic,
  assignAssetToUserGroupEpic,
  getCompanyMembersEpic,
  getCompanyMembersByAssetEpic,
  memberRemoval,
  memberAddition,
  memberEdition,
  editAssetEpic,
  deleteAssetEpic,
  getUserAssetsEpic,
  getUserAssetsSilentlyEpic,
  checklistCreationEpic,
  checklistDeletionEpic,
  getChecklistsEpic,
  getChecklistEpic,
  redirectToChecklistTableEpic,
  redirectToDamagesTableEpic,
  refreshChecklistTableAfterDeletionEpic,
  redirectToRegularMaintenanceTableEpic,
  redirectToMaintenancePlanTableEpic,
  redirectToChecklistsTableEpic,
  redirectToDelayCausesTableEpic,
  getActivitiesEpic,
  getDelayCausesEpic,
  getRecodedDelayCausesEpic,
  sendRecodedDelayCausesEpic,
  updateRecodedDelayCausesEpic,
  deleteRecodedDelayCauseEpic,
  getTrainRunsEpic,
  recodeDelayCauseEpic,
  recodeDelayCauseSucceededEpic,
  maintenancePlanCreationEpic,
  maintenancePlanUpdateEpic,
  getMaintenancePlansEpic,
  maintenancePlanDeletionEpic,
  getMaintenancePlanEpic,
  refreshMaintenancePlanTableAfterDeletionEpic,
  getAllArticlesEpic,
  createOrderEpic,
  forwardToOrderDetailsPageEpic,
  getAssetDistanceEpic,
  getAssetFlexibleDistanceEpic,
  getUserToModuleRolesEpic,
  updateUserToModuleRolesEpic,
  userModuleRoleDeletionEpic,
  refreshUserModuleRoleTableAfterDeletionEpic,
  createUserToModuleRolesEpic,
  addressGroupUpdateEpic,
  getAddressesEpic,
  getAddressGroupsEpic,
  addressGroupDeletionEpic,
  addressGroupMultiUpdateEpic,
  addressGroupCreateEpic,
);

export const rootReducer = combineReducers({
  checkinForms,
  photosIds,
  photos,
  isLoading,
  currentCheckinForm,
  currentTerminalId,
  licensePlate,
  checkinFormUpdateSubmission,
  currentUser,
  companyIds,
  companies,
  assetsWithState,
  trackers,
  trackersFetchingStatus,
  assignedTracker,
  alertAssignTracker,
  members,
  membersFetchingStatus,
  currentChecklist,
  checklists,
  checklistsFetchingStatus,
  checklistsDictionary,
  activities,
  maintenancePlans,
  currentMaintenancePlan,
  activitiesFetchingStatus,
  delayCauses,
  delayCausesFetchingStatus,
  trainRuns,
  trainRunsFetchingStatus,
  recodedDelayCausesList,
  recodedDelayCausesFetchingStatus,
  isXlsFileDownloading,
  delayDate,
  updatedArticles,
  orderDetails,
  assetDistance,
  assetFlexibleDistance,
  usersAndModuleRoles,
  currentUserModuleRoleRelation,
  addresses,
  addressesFetchingStatus,
  addressGroupFetchingStatus,
  addressGroups,
});
