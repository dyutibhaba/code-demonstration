import React, { useEffect } from 'react';
import { FormattedMessage } from 'react-intl';
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router';
import PropTypes from 'prop-types';
import _ from 'lodash';

import MaintenanceTabContainer from './MaintenanceTabContainer';
import DamageReportsTable from '../reports/table/damage/DamageReportsTable';
import RegularMaintenanceTable from '../reports/table/regular-maintenance/RegularMaintenanceTable';
import ChecklistTable from '../reports/table/checklists/ChecklistTable';
import { getAsset, isUserAssetsFetched, isUserAssetsLoading } from '../../../../admin/store/tracker/selectors/trackerSelectors';
import { areChecklistsFetched, areChecklistsLoading } from '../../../store/checklist/selectors/checklistSelectors';
import { askForAssetsByUser } from '../../../../admin/store/assets/actions/assetActions';
import { askForChecklists, askForResetChecklists, askForMaintenancePlans } from '../../../store/checklist/actions/checklistActions';
import { Loader } from '../../../../common/components/miscellaneous/Loader';
import ActivitiesTable from '../reports/table/activity/ActivitiesTable';
import MaintenanceParametersTable from '../reports/table/maintenance-parameters/MaintenanceParametersTable';
import MileagePage from '../reports/table/mileage/MileagePage';
import { askForAssetDistance } from '../../../store/checklist/actions/assetDistanceActions';

const switchRender = (tabIndex) => {
  switch (tabIndex) {
    case 0:
      return <ChecklistTable />;
    case 1:
      return <DamageReportsTable />;
    case 2:
      return <MaintenanceParametersTable />;
    case 3:
      return <RegularMaintenanceTable />;
    case 4:
      return <ActivitiesTable />;
    case 5:
      return <MileagePage />;
    default:
      return <></>;
  }
};

const MaintenanceTabs = ({ index }) => {
  const { assetId } = useParams();
  const dispatch = useDispatch();
  const asset = useSelector(getAsset(assetId));
  const isFetched = useSelector(isUserAssetsFetched);
  const isLoading = useSelector(isUserAssetsLoading);
  const checklistsFetched = useSelector(areChecklistsFetched);
  const checklistsLoading = useSelector(areChecklistsLoading);

  useEffect(() => {
    if (asset === undefined && !isFetched && !isLoading) {
      dispatch(askForAssetsByUser());
    }
    if (!checklistsFetched) {
      dispatch(askForChecklists(assetId));
      dispatch(askForMaintenancePlans(assetId));
      dispatch(askForAssetDistance(assetId));
    }
    return () => { dispatch(askForResetChecklists()); };
  }, []);

  if (_.isNil(asset) && isFetched) {
    return (<p><FormattedMessage id="maintenance.tabs.error" /></p>);
  }

  if (isLoading || _.isNil(asset)) {
    return (<Loader />);
  }

  return (
    <MaintenanceTabContainer index={index} asset={asset}>
      {checklistsLoading ? <Loader /> : switchRender(index)}
    </MaintenanceTabContainer>
  );
};

MaintenanceTabs.propTypes = {
  index: PropTypes.number,
};

MaintenanceTabs.defaultProps = {
  index: 0,
};

export default MaintenanceTabs;
