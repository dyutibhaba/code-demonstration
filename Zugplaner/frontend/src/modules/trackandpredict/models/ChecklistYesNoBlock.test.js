import ChecklistYesNoBlock from "./ChecklistYesNoBlock";

const motorolstandBlob = {
  motorolstand:'yes',
  comment_motorolstand: undefined,
  liter_motorolstand: undefined,
}

const kuhlwasserstandBlob = {
  kuhlwasserstand:'no',
  comment_kuhlwasserstand: undefined,
  liter_kuhlwasserstand: undefined,
}
const fahrwerkBlob = {
  fahrwerk:'yes',
  comment_fahrwerk: undefined,
  liter_fahrwerk: undefined,
}
const undichtigkeitenBlob = {
  undichtigkeiten:'yes',
  comment_undichtigkeiten: undefined,
  liter_undichtigkeiten: undefined,
}

const motorolstandYesNoBlock = {
  name: 'motorolstand',
  required: true,
  status: 'yes',
  hasComment: true,
  comment: undefined,
  hasLiter: true,
  liter: undefined,
}

const motorolChecklistYesNoBlock = new ChecklistYesNoBlock(
  'motorolstand',
  true,
  motorolstandBlob.motorolstand,
  true,
  motorolstandBlob.comment_motorolstand,
  true,
  motorolstandBlob.liter_motorolstand,
);

const kuhlwasserChecklistYesNoBlock = new ChecklistYesNoBlock(
  'kuhlwasserstand',
  true,
  kuhlwasserstandBlob.kuhlwasserstand,
  true,
  kuhlwasserstandBlob.comment_kuhlwasserstand,
  true,
  kuhlwasserstandBlob.liter_kuhlwasserstand,
);

describe('test ChecklistYesNoBlock parsing from the backend', () => {
  it('parses the checklistYesNoBlock with an object from the back end', () => {
    expect(motorolChecklistYesNoBlock)
    .toEqual(motorolstandYesNoBlock);
  });

  it('parses the checklistYesNoBlock with an empty object from the back end', () => {
    const emptyYesNoBlock = new ChecklistYesNoBlock();
    expect(emptyYesNoBlock)
    .toEqual(new ChecklistYesNoBlock(undefined, undefined, undefined, undefined, undefined, undefined, undefined));
  });
});

describe('test ChecklistYesNoBlock validation check', () => {
  it('return false if the block is not valid', () => {
    expect(kuhlwasserChecklistYesNoBlock.isBlockValid())
    .toEqual(false);
  });
  it('return true if the block is valid', () => {
    expect(motorolChecklistYesNoBlock.isBlockValid())
    .toEqual(true);
  });
});

describe('test ChecklistYesNoBlock parsing from the backend', () => {
  it('parses the checklistYesNoBlock with an object from the back end', () => {
    expect(motorolChecklistYesNoBlock)
    .toEqual(motorolstandYesNoBlock);
  });

  it('parses the checklistYesNoBlock with an empty object from the back end', () => {
    const emptyYesNoBlock = new ChecklistYesNoBlock();
    expect(emptyYesNoBlock)
    .toEqual(new ChecklistYesNoBlock(undefined, undefined, undefined, undefined, undefined, undefined, undefined));
  });
});

describe('test ChecklistYesNoBlock validation check', () => {
  it('return false if the block is not valid', () => {
    expect(kuhlwasserChecklistYesNoBlock.isBlockValid())
    .toEqual(false);
  });
  it('return true if the block is valid', () => {
    expect(motorolChecklistYesNoBlock.isBlockValid())
    .toEqual(true);
  });
});
