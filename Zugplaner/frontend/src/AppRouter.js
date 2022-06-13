import React, { useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

import { Redirect } from 'react-router';
import TrackAndPredictHome from './modules/trackandpredict/pages/home/Home';
import DigitalCheckInHome from './modules/digitalcheckin/pages/Home';
import NotFound from './modules/common/pages/not-found/NotFound';
import Portal from './modules/portal/pages/Portal';
import NewContainer from './modules/digitalcheckin/pages/NewContainer';
import NanonetTest from './modules/digitalcheckin/pages/NanonetTest';
import PhotoCapture from './modules/digitalcheckin/pages/PhotoCapture';
import { askForLoginBackend } from './modules/common/store/actions/userActions';
import {
  DELAY_MANAGER_FEATURE, hasPremiumAccessFor, isAdmin, MAINTENANCE_FEATURE, MARKETPLACE_FEATURE,
} from './modules/common/store/selector/userSelector';
import Companies from './modules/admin/pages/companies/Companies';
import Admin from './modules/admin/pages/old/Admin';
import TrackerTabContainer from './modules/admin/pages/trackers/TrackerTabContainer';
import MemberTabContainer from './modules/admin/pages/members/MemberTabContainer';
import EndOfShiftPage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/end-of-shift/creation/EndOfShiftPage';
import ShiftDetailsPage from './modules/trackandpredict/pages/maintenance-checklists/reports/ShiftDetailsPage';
import DamageReportPage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/damage/creation/DamageReportPage';
import DamageReportDetailsPage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/damage/update/DamageReportDetailsPage';
import MaintenanceParametersPage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/maintenance-parameters/creation/MaintenanceParametersPage';
import RegularMaintenancePage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/regular-maintenance/creation/RegularMaintenancePage';
import MaintenanceTabs from './modules/trackandpredict/pages/maintenance-checklists/layout/MaintenanceTabs';
import StartOfShiftPage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/start-of-shift/creation/StartOfShiftPage';
import DelayManagerTabs from './modules/delay-manager/pages/layout/DelayManagerTabs';
import DelayCauseTrainRunView from './modules/delay-manager/pages/train-run/DelayCauseTrainRunView';
import MaintenancePlanDetailsPage from './modules/trackandpredict/pages/maintenance-checklists/reports/form/maintenance-parameters/update/MaintenancePlanDetailsPage';
import MarketplaceTabs from './modules/marketplace/pages/layout/MarketplaceTabs';
import OrderDetails from './modules/marketplace/pages/order/OrderDetails';
import OrderSummaryPage from './modules/marketplace/pages/layout/OrderSummaryPage';
import UserToModuleRoleTabContainer from './modules/admin/pages/UserToModuleRoles/UserToModuleRoleTabContainer';
import Users from './modules/admin/pages/user/Users';
import UserToModuleRoleEditPage from './modules/admin/pages/UserToModuleRoles/UserToModuleRoleEditPage';
import UserToModuleRoleNewPage from './modules/admin/pages/UserToModuleRoles/UserToModuleRoleNewPage';
import TransportOrderTabs from './modules/transportorder/pages/layout/TransportOrderTabs';

const AdminRouter = () => (
  <Switch>
    <Route path="/admin/user-groups/:id/members" component={MemberTabContainer} />
    <Route path="/admin/user-groups/:id/trackers" component={TrackerTabContainer} />
    <Route path="/admin/user-groups" component={Companies} />
    <Route path="/admin/old" component={Admin} />
    <Route path="/admin/user-to-module-roles/:userId/:moduleId/:roleId" component={UserToModuleRoleEditPage} />
    <Route path="/admin/user-to-module-roles/new" component={UserToModuleRoleNewPage} />
    <Route path="/admin/user-to-module-roles" component={UserToModuleRoleTabContainer} />
    <Route path="/admin/users" component={Users} />
    <Route path="/admin" component={Companies} />
  </Switch>
);

const MaintenanceRouter = () => (
  <Switch>
    <Route path="/track-and-predict/maintenance-asset/:assetId/checklists/startofshift" component={StartOfShiftPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/checklists/endofshift" component={EndOfShiftPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/checklists/:checklistId" component={ShiftDetailsPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/checklists" render={() => (<MaintenanceTabs index={0} />)} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/damages/new" component={DamageReportPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/damages/:damageReportId" component={DamageReportDetailsPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/damages" render={() => (<MaintenanceTabs index={1} />)} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/maintenance-parameters/new" component={MaintenanceParametersPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/maintenance-parameters/:maintenancePlanId" component={MaintenancePlanDetailsPage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/maintenance-parameters" render={() => (<MaintenanceTabs index={2} />)} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/maintenance-log/new" component={RegularMaintenancePage} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/maintenance-log" render={() => (<MaintenanceTabs index={3} />)} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/activity" render={() => (<MaintenanceTabs index={4} />)} />
    <Route path="/track-and-predict/maintenance-asset/:assetId/mileage" render={() => (<MaintenanceTabs index={5} />)} />
  </Switch>
);

const DigitalCheckinRouter = () => (
  <Switch>
    <Route path="/digital-check-in/new/:index" component={PhotoCapture} />
    <Route path="/digital-check-in/new" component={NewContainer} />
    <Route path="/digital-check-in/test" component={NanonetTest} />
    <Route path="/digital-check-in" component={DigitalCheckInHome} />
  </Switch>
);

const DelayManagerRouter = () => (
  <Switch>
    <Route path="/delay-manager/train-run/:id" component={DelayCauseTrainRunView} />
    <Route path="/delay-manager/delay-cause" render={() => (<DelayManagerTabs index={0} />)} />
    <Route path="/delay-manager/recoding" render={() => (<DelayManagerTabs index={1} />)} />
    <Route path="/delay-manager" render={() => (<DelayManagerTabs index={0} />)} />
  </Switch>
);

const TransportOrderRouter = () => (
  <Switch>
    <Route path="/transport-order/address-groups" render={() => (<TransportOrderTabs index={1} />)} />
    <Route path="/transport-order" render={() => (<TransportOrderTabs index={0} />)} />
  </Switch>
);

const MarketplaceRouter = () => (
  <Switch>
    <Route path="/marketplace/products/:productId/articles" render={() => (<MarketplaceTabs index={0} />)} />
    <Route exact path="/marketplace"><Redirect to="/marketplace/products/l700h/articles" /></Route>
    <Route path="/marketplace/order/summary" render={() => (<OrderSummaryPage />)} />
    <Route path="/marketplace/order-details/:id" component={OrderDetails} />
    <Route path="/marketplace/order" render={() => (<text>Order Summary</text>)} />
    <Route path="/marketplace/product/:id" render={() => (<text>Order Table</text>)} />
  </Switch>
);

const AppRouter = () => {
  const userIsAdmin = useSelector(isAdmin);
  const hasPayingAccessFor = (feature) => useSelector(hasPremiumAccessFor(feature));
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(askForLoginBackend());
  });

  return (
    <Router>
      <Switch>
        {userIsAdmin && <Route path="/admin" component={AdminRouter} />}
        <Route path="/transport-order" component={TransportOrderRouter} />
        {hasPayingAccessFor(MAINTENANCE_FEATURE)
          && (
            <Route path="/track-and-predict/maintenance-asset" component={MaintenanceRouter} />
          )}
        <Route path="/track-and-predict" component={TrackAndPredictHome} />
        <Route path="/portal" component={Portal} />
        <Route path="/digital-check-in" component={DigitalCheckinRouter} />
        {hasPayingAccessFor(DELAY_MANAGER_FEATURE) && <Route path="/delay-manager" component={DelayManagerRouter} />}
        {hasPayingAccessFor(MARKETPLACE_FEATURE) && <Route path="/marketplace" component={MarketplaceRouter} />}
        <Route path="/home" component={Portal} />
        <Route path="/" exact component={Portal} />
        <Route component={NotFound} />
      </Switch>
    </Router>
  );
};

export default AppRouter;
