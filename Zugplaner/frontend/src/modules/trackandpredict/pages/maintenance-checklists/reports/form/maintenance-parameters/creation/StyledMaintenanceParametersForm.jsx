import styled from 'styled-components';

const StyledMaintenanceParametersForm = styled.form`
  margin: ${(props) => props.theme.spacings.large};
  display: grid;
  /* height: 100vh; */
  grid-template-columns: 1fr 2fr;
  /* grid-template-rows: repeat(9, 1fr); */
  grid-gap: 24px;
  /* grid-template-areas:
    "label1 input1"
    "label2 input2"
    "label3 input3"; */

  .ko {
    display: grid;
    height: 100%;
    grid-template-columns: 1fr 1fr;
    grid-gap: 16px;
  }

  .label {
    grid-column: 1 / 2;
    font-size: 16px;
    text-align: right;
    justify-content: flex-end;
  }

  .label-radio {
    margin-right: ${(props) => props.theme.spacings.medium};
    cursor: pointer;
  }

  .input-radio {
    margin-right: ${(props) => props.theme.spacings.xSmall};
    cursor: pointer;
  }

  .input,
  .button {
    grid-column: 2 / 3;
    font-size: 16px;
  }

  .number {
    width: 100px;
  }

  .textarea {
    height: 60px;
  }

  /* Fix calendar position for small device (mobile first) */
  .react-date-picker__calendar.react-date-picker__calendar--open {
    top: 50% !important;
    left: 50% !important;
    transform: translate(-50%, -50%) !important;
    position: fixed !important;
  }

  @media only screen and (min-width: 768px) {
    .react-date-picker__calendar.react-date-picker__calendar--open {
      top: 100% !important;
      left: 0 !important;
      transform: none !important;
      position: absolute !important;
    }
  }

  .dropdown-status {
    width: 300px;
  }

  .text-input-mp-width {
    max-width: 300px;
  }
`;

export default StyledMaintenanceParametersForm;
