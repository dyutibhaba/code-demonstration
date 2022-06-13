import React from 'react';
import PropTypes from 'prop-types';
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import { FormattedMessage } from 'react-intl';

import Label from '../../../../../../../common/components/miscellaneous/Label';
import FreeCommentField from '../../common/blocks/free-comment/FreeCommentField';
import ChecklistFreeBlock from '../../../../../../models/ChecklistFreeBlock';
import TextInput from '../../../../../../../../components/miscellaneous/TextInputTpz/TextInputTpz';
import MaintenanceParameters from '../../../../../../models/MaintenanceParameters';
import Dropdown from '../../../../../../../common/components/miscellaneous/Dropdown';

const MaintenancePlanDetailsForm = ({
  maintenancePlanReport, setMaintenancePlanReport, readOnly,
}) => {
  const onChange = (block) => {
    setMaintenancePlanReport(maintenancePlanReport.withBlock(block));
  };
  const onChangeMaintenanceType = (e) => onChange(new ChecklistFreeBlock('maintenanceType', true, e.target.value));
  const onChangeFrequencyValue = (e) => onChange(new ChecklistFreeBlock('frequencyValue', true, e.target.value));
  const onChangeAlarmSet = (e) => onChange(new ChecklistFreeBlock('alarmIsSet', true, e.target.checked));

  return (
    <>
      <Label className="label" required>
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.maintenance-type" />
      </Label>
      <TextInput
        className="text-input-mp-width"
        value={maintenancePlanReport.body.maintenanceType.comment}
        block={maintenancePlanReport.body.maintenanceType.comment}
        onChange={onChangeMaintenanceType}
      />
      <Label className="label" required>
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.frequency-value" />
      </Label>
      <TextInput
        className="input number"
        value={maintenancePlanReport.body.frequencyValue.comment}
        block={maintenancePlanReport.body.frequencyValue.comment}
        onChange={onChangeFrequencyValue}
        type="number"
        min="0"
      />
      <Label className="label" required>
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.frequency-unit" />
      </Label>
      <div className="dropdown-status">
        <Dropdown
          fullWidth
          block={maintenancePlanReport.body.frequencyType}
          disabled={readOnly}
          onChange={onChange}
        />
      </div>
      <Label className="label">
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.set-alarm" />
      </Label>
      <FormControlLabel
        value="start"
        control={<Checkbox color="primary" checked={maintenancePlanReport.body.alarmIsSet.comment} />}
        labelPlacement="end"
        onChange={onChangeAlarmSet}
      />
      <FreeCommentField
        label="maintenance.maintenance-parameters.form.title.comment"
        block={maintenancePlanReport.body.comment}
        onChange={onChange}
        readOnly={false}
      />
    </>
  );
};

MaintenancePlanDetailsForm.propTypes = {
  maintenancePlanReport: MaintenanceParameters.shape.isRequired,
  setMaintenancePlanReport: PropTypes.func.isRequired,
  readOnly: PropTypes.bool.isRequired,
};

export default MaintenancePlanDetailsForm;
