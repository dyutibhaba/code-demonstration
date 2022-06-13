import DateBlock from "./DateBlock";
import StartOfShiftBody from "./StartOfShiftBody";

describe('test StartOfShiftBody parsing from the backend', () => {
  it('parses the start of shift body with an empty body from the back end', () => {
    
    const expected = StartOfShiftBody.empty();
    const checklistBody = StartOfShiftBody.fromBlob({});
    expect(expected)
      .toEqual(StartOfShiftBody.empty().withBlock(new DateBlock('operatingDay', true, expected.blocks[4].date)));
  });
});
