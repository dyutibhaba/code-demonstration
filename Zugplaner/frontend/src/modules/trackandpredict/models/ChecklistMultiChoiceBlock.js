import PropTypes from 'prop-types';
import Block from './Block';

export default class ChecklistMultiChoiceBlock extends Block {
  static get shape() {
    return PropTypes.shape({
      name: PropTypes.string,
      required: PropTypes.bool,
      selectedType: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
      selectedLabel: PropTypes.string,
      options: PropTypes.arrayOf(PropTypes.any),
    });
  }

  static empty() {
    return new ChecklistMultiChoiceBlock('', false, 1, '', []);
  }

  constructor(name, required, selectedType, selectedLabel, options) {
    super(name);
    this.required = required;
    this.selectedType = selectedType;
    this.selectedLabel = selectedLabel;
    this.options = options;
  }

  isBlockValid() {
    return !this.required || (this.selectedType !== '' && this.selectedType !== undefined);
  }

  toBody() {
    switch (this.name) { // NO SONAR
      case 'maintainer':
        return {
          [this.name]: {
            maintainerId: this.selectedType,
            maintainerDisplayedName: this.selectedLabel,
          },
        };
      default:
        return {
          [this.name]: this.selectedType,
        };
    }
  }

  withSelection({ type, label }) {
    return new ChecklistMultiChoiceBlock(
      this.name,
      this.required,
      type,
      label,
      this.options,
    );
  }

  isTypeNumber() {
    return typeof this.selectedType === 'number';
  }
}
