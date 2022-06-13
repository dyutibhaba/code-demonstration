import React, { useCallback } from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import { FormattedMessage } from 'react-intl';
import { useDispatch, useSelector } from 'react-redux';
import { askForMaintenancePlanDeletion } from '../../store/checklist/actions/checklistActions';
import StyledDeleteCompanyModal from '../../../admin/pages/companies/modals/StyledDeleteCompanyModal';
import Modal from '../../../../components/miscellaneous/ModalTpz';
import Button from '../../../common/components/miscellaneous/Button';
import {
  CHECKLIST_NAMES,
  CHECKLIST_TYPE_MAINTENANCE_PLAN,
} from '../../models/Checklist';
import { askForAssetsByUserSilently } from '../../../admin/store/assets/actions/assetActions';
import MaintenanceParameters from '../../models/MaintenanceParameters';
import { getRegularMaintenance } from '../../store/checklist/selectors/regularMaintenanceSelectors';

export const getReportTypeNameSwitch = (reportType) => {
  switch (reportType) {
    case CHECKLIST_TYPE_MAINTENANCE_PLAN:
      return 'maintenance plan';
    default:
      return 'checklist';
  }
};

const DeleteMaintenancePlanModal = ({ report, closeModal, open }) => {
  const maintenanceLogs = useSelector(getRegularMaintenance);
  const dispatch = useDispatch();
  const handleDelete = useCallback(() => {
    dispatch(askForMaintenancePlanDeletion(report.id, report.assetId, report.type));
    dispatch(askForAssetsByUserSilently());
    closeModal();
  }, [report]);

  const plan = maintenanceLogs.find((item) => item.maintenancePlanId === report.id);
  if (plan !== undefined) {
    return (
      <StyledDeleteCompanyModal isOpened={open} onAfterClose={closeModal}>
        <Modal.Header><FormattedMessage id="modal.checklist.delete.title" /></Modal.Header>
        <Modal.Body>
          <p className={clsx('tpz-body')}>
            <FormattedMessage
              id="modal.maintenance-plan.delete.negation"
              values={{
                // eslint-disable-next-line react/display-name
                bold: (str) => <b>{str}</b>,
              }}
            />
          </p>
        </Modal.Body>
        <Modal.Footer>
          <Button
            type="button"
            level="secondary"
            onClick={closeModal}
            className="tpz-btn tpz-btn-secondary"
          >
            <FormattedMessage id="modal.company.delete.cancel" />
          </Button>
        </Modal.Footer>
      </StyledDeleteCompanyModal>
    );
  }
  return (
    <StyledDeleteCompanyModal isOpened={open} onAfterClose={closeModal}>
      <Modal.Header><FormattedMessage id="modal.checklist.delete.title" /></Modal.Header>
      <Modal.Body>
        <p className={clsx('tpz-body')}>
          <FormattedMessage
            id="modal.checklist.delete.body1"
            values={{
              reportType: <FormattedMessage id={CHECKLIST_NAMES[report.type]} />,
              creatorName: (report.type === 4 ? report.body.maintainer.selectedLabel
                : report.creator),
              by: (report.type === 4 ? 'reported by' : 'created by'),
              // eslint-disable-next-line react/display-name
              bold: (str) => <b>{str}</b>,
            }}
          />
        </p>
        <p className={clsx('tpz-body')}>
          <b><FormattedMessage
            id="modal.checklist.delete.body2"
            values={{ reportName: getReportTypeNameSwitch(report.type) }}
          />
          </b>
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button
          type="button"
          level="secondary"
          onClick={closeModal}
          className="tpz-btn tpz-btn-secondary"
        >
          <FormattedMessage id="modal.company.delete.cancel" />
        </Button>
        <Button
          id="delete-modal"
          type="submit"
          variant="danger"
          onClick={handleDelete}
          className="confirm-deletion-button"
        >
          <FormattedMessage id="modal.company.delete.confirm" />
        </Button>
      </Modal.Footer>
    </StyledDeleteCompanyModal>
  );
};
DeleteMaintenancePlanModal.propTypes = {
  report: MaintenanceParameters.shape,
  open: PropTypes.bool,
  closeModal: PropTypes.func,
};

DeleteMaintenancePlanModal.defaultProps = {
  report: MaintenanceParameters.empty(),
  open: false,
  closeModal: () => { },
};

export default DeleteMaintenancePlanModal;
