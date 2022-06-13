import React from 'react';
import PropTypes from 'prop-types';
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import { FormattedMessage } from 'react-intl';

import StyledMaintenanceParametersForm from './StyledMaintenanceParametersForm';
import 'react-datepicker/dist/react-datepicker.css';
import Label from '../../../../../../../common/components/miscellaneous/Label';
import FreeCommentField from '../../common/blocks/free-comment/FreeCommentField';
import ChecklistFreeBlock from '../../../../../../models/ChecklistFreeBlock';
import TextInput from '../../../../../../../../components/miscellaneous/TextInputTpz/TextInputTpz';
import MaintenanceParameters from '../../../../../../models/MaintenanceParameters';
import Dropdown from '../../../../../../../common/components/miscellaneous/Dropdown';

const MaintenanceParametersForm = ({ report, setReport }) => {
  const onChange = (block) => {
    setReport(report.withBlock(block));
  };
  const onChangeMaintenanceType = (e) => onChange(new ChecklistFreeBlock('maintenanceType', true, e.target.value));
  const onChangeFrequencyValue = (e) => onChange(new ChecklistFreeBlock('frequencyValue', true, e.target.value));
  const onChangeAlarmSet = (e) => onChange(new ChecklistFreeBlock('alarmIsSet', true, e.target.checked));

  return (
    <StyledMaintenanceParametersForm>
      <Label className="label" required>
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.maintenance-type" />
      </Label>
      <TextInput
        className="text-input-mp-width"
        onChange={onChangeMaintenanceType}
      />
      <Label className="label" required>
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.frequency-value" />
      </Label>
      <TextInput
        className="input number"
        block={report.body.frequencyValue}
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
          block={report.body.frequencyType}
          onChange={onChange}
        />
      </div>
      <Label className="label">
        <FormattedMessage id="maintenance.maintenance-parameters.form.header.set-alarm" />
      </Label>
      <FormControlLabel
        value="start"
        control={<Checkbox color="primary" />}
        labelPlacement="end"
        onChange={onChangeAlarmSet}
      />
      <FreeCommentField
        label="maintenance.maintenance-parameters.form.title.comment"
        block={report.body.comment}
        onChange={onChange}
        readOnly={false}
      />
    </StyledMaintenanceParametersForm>
  );
};

MaintenanceParametersForm.propTypes = {
  report: MaintenanceParameters.shape.isRequired,
  setReport: PropTypes.func.isRequired,
};

export default MaintenanceParametersForm;
