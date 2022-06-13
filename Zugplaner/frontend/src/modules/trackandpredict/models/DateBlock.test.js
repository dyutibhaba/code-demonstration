import DateBlock from "./DateBlock";

const dateBlockBack = {
  name: 'operatingDay',
  required: true,
  date: new Date('2021-01-25'),
};

const updatedDateBlock = {
  name: 'operatingDay',
  required: true,
  date: new Date('2020-12-30'),
};

const dateBlock = new DateBlock('operatingDay', true, new Date('2021-01-25'));

describe('test DateBlock parsing from the backend', () => {
  it('parses the DateBlock with an object from the back end', () => {
    expect(dateBlockBack)
    .toEqual(dateBlock);
  });

  it('parses the DateBlock with an empty object', () => {
    expect(new DateBlock())
    .toEqual(new DateBlock(undefined, undefined, undefined));
  });
});

describe('test DateBlock', () => {
  it('updates its date', () => {
    expect(dateBlock.withDate(new Date('2020-12-30')))
    .toEqual(updatedDateBlock);
  });
});

describe('test DateBlock', () => {
  it('serializes its body', () => {
    expect(dateBlock.toBody())
    .toEqual({'operatingDay': '2021-01-25'});
  });

  it('returns an empty object when the date is null', () => {
    expect(new DateBlock('date', true, null).toBody())
    .toEqual({});
  });
});
