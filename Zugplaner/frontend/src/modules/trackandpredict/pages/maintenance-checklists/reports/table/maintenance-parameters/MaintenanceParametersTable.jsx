import React, { useMemo, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useIntl } from 'react-intl';
import { useHistory, useParams } from 'react-router';
import { format, isAfter } from 'date-fns';
import Checklist from '../../../../../models/Checklist';
import { isAdmin } from '../../../../../../common/store/selector/userSelector';
import Button from '../../../../../../common/components/miscellaneous/Button';
import StyledChecklistTable from '../checklists/StyledChecklistTable';
import Table from '../../../../../../common/components/table/Table';
import Icon from '../../../../../../common/components/miscellaneous/Icon';
import AddMaintenanceParametersButton from './AddMaintenanceParametersButton';
import { getMaintenancePlans } from '../../../../../store/checklist/selectors/maintenancePlanSelectors';
import DeleteMaintenancePlanModal from '../../../../modal/DeleteMaintenancePlanModal';
import { askForSetCurrentMaintenancePlan as selectMaintenancePlan } from '../../../../../store/checklist/actions/checklistActions';

const MaintenanceParametersTable = () => {
  const maintenancePlans = useSelector(getMaintenancePlans);
  const [openDeleteModal, setOpenDeleteModal] = useState(false);
  const [maintenancePlanToDelete, setMaintenancePlanToDelete] = useState(Checklist.empty());
  const intl = useIntl();
  const ref = React.createRef();

  const displayFrequencyUnit = ({ row: { values } }) => {
    const frequencyUnit = intl.formatMessage({ id: values.frequencyUnit });
    return `${values.frequencyValue} ${frequencyUnit}`;
  };

  const formatLastUpdate = ({ row: { values } }) => (
    <span>{format(new Date(values.lastUpdateDateTime), 'Pp O')} </span>
  );

  const sortingByCreationDate = (itemA, itemB) => (isAfter(
    new Date(itemA.creationDate), new Date(itemB.creationDate),
  ) ? -1 : 1);

  const data = maintenancePlans
    .sort(sortingByCreationDate)
    .map((report) => report.toMaintenancePlanFormat());

  const displayIfAlarmSet = ({ row: { values } }) => (
    values.alarmIsSet ? <span className="material-icons">alarm_on</span>
      : <span className="material-icons">alarm_off</span>
  );

  const displayIfAlarmIsDue = ({ row: { values } }) => (
    values.alarmIsDue ? <span className="material-icons" style={{ color: 'hsl(220deg 78% 34%)' }}>notifications_active</span>
      : <span className="material-icons" />
  );

  const displayActions = ({ row: { values } }) => {
    const userIsAdmin = useSelector(isAdmin);
    const history = useHistory();
    const dispatch = useDispatch();
    const { assetId } = useParams();
    return (
      <div className="actions-buttons">
        <Button
          level="ghost"
          className="tpz-btn-small"
          onClick={() => {
            dispatch(selectMaintenancePlan(values.id));
            history.push(`/track-and-predict/maintenance-asset/${assetId}/maintenance-parameters/${values.id}`);
          }}
        >
          <span className="material-icons">edit</span>
        </Button>
        {userIsAdmin
          && (
            <Button
              id="delete"
              level="ghost"
              className="tpz-btn-small"
              iconButton
              onClick={() => {
                setMaintenancePlanToDelete(maintenancePlans.find((item) => item.id === values.id));
                setOpenDeleteModal(true);
              }}
            >
              <Icon name="delete" />
            </Button>
          )}
      </div>
    );
  };

  const headers = useMemo(
    () => [
      {
        accessor: 'id',
        Header: intl.formatMessage({ id: 'maintenance.regular-maintenance.table.header.actions' }),
        disableFilters: true,
        Cell: displayActions,
      }, {
        accessor: 'maintenanceType',
        Header: intl.formatMessage({ id: 'maintenance.maintenance-parameters.table.header.maintenance-type' }),
        disableFilters: true,
        className: 'checklistModel-cell',
      }, {
        accessor: 'frequencyValue',
        Header: intl.formatMessage({ id: 'maintenance.maintenance-parameters.table.header.frequency-value' }),
        disableFilters: true,
        className: 'checklistModel-cell',
        show: false,
      }, {
        accessor: 'frequencyUnit',
        Header: intl.formatMessage({ id: 'maintenance.maintenance-parameters.table.header.frequency' }),
        disableFilters: true,
        Cell: displayFrequencyUnit,
        className: 'checklistModel-cell',
      }, {
        accessor: 'comment',
        Header: intl.formatMessage({ id: 'maintenance.maintenance-parameters.form.title.comment' }),
        disableFilters: true,
        className: 'checklistModel-cell',
      }, {
        accessor: 'alarmIsSet',
        Header: intl.formatMessage({ id: 'maintenance.maintenance-parameters.table.header.alarm-set' }),
        disableFilters: true,
        className: 'checklistModel-cell',
        Cell: displayIfAlarmSet,
      }, {
        accessor: 'alarmIsDue',
        Header: intl.formatMessage({ id: 'maintenance.maintenance-parameters.table.header.due' }),
        disableFilters: true,
        className: 'checklistModel-cell',
        Cell: displayIfAlarmIsDue,
      }, {
        accessor: 'lastUpdateDateTime',
        Header: intl.formatMessage({ id: 'maintenance.regular-maintenance.table.header.lastUpdate' }),
        disableFilters: true,
        Cell: formatLastUpdate,
        className: 'lastupdate-cell',
      },
    ],
    [],
  );

  return (
    <>
      <DeleteMaintenancePlanModal
        report={maintenancePlanToDelete}
        open={openDeleteModal}
        closeModal={() => setOpenDeleteModal(false)}
      />
      <StyledChecklistTable>
        <Table
          ref={ref}
          tableDataSearchLabel="trackandpredict.header.filter.maintenance-parameters"
          columns={headers}
          data={data}
          sort
          paginationEnabled
          addButton={<AddMaintenanceParametersButton />}
        />
      </StyledChecklistTable>
    </>
  );
};

export default MaintenanceParametersTable;
