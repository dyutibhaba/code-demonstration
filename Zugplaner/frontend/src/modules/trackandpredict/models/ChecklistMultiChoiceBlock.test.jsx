import ChecklistMultiChoiceBlock from './ChecklistMultiChoiceBlock';

const multiBlob = {
  maintainerId: undefined,
  maintainerDisplayedName: undefined,
};

const multiChoiceBlock = new ChecklistMultiChoiceBlock(
  'maintainer',
  true,
  multiBlob.maintainerId,
  multiBlob.maintainerDisplayedName,
  [],
);

const multiChoiceBlockObject = {
  name: 'maintainer',
  required: true,
  selectedType: multiBlob.maintainerId,
  selectedLabel: multiBlob.maintainerDisplayedName,
  options: multiChoiceBlock.options,
};

describe('test ChecklistMultiChoiceBlock parsing from the backend', () => {
  it('parses the checklistMultiChoiceBlock with an object from the back end', () => {
    expect(multiChoiceBlock)
      .toEqual(multiChoiceBlockObject);
  });

  it('parses the checklistMultiChoiceBlock with an empty object from the back end', () => {
    const emptyMultiChoiceBlock = new ChecklistMultiChoiceBlock('', false, 1, '', []);
    expect(emptyMultiChoiceBlock)
      .toEqual(
        ChecklistMultiChoiceBlock.empty(),
      );
  });
});

describe('test ChecklistMultiChoiceBlock validation check', () => {
  it('returns false if the block is not valid', () => {
    expect(multiChoiceBlock.isBlockValid())
      .toEqual(false);
  });

  it('returns true if the block is valid', () => {
    const validMultiChoiceBlock = new ChecklistMultiChoiceBlock(
      'maintainer',
      true,
      'id',
      'User Name',
      [],
    );
    expect(validMultiChoiceBlock.isBlockValid())
      .toEqual(true);
  });
});
