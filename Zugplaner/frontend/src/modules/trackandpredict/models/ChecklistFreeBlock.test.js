import ChecklistFreeBlock from "./ChecklistFreeBlock";

const freeBlob = {
  stangenlagerPrufen: undefined,
}

const freeChecklistBlock = new ChecklistFreeBlock(
  'stangenlagerPrufen',
  false,
  freeBlob.stangenlagerPrufen,
);

const freeBlockObject = {
  name: 'stangenlagerPrufen',
  required: false,
  comment: undefined,
}

describe('test ChecklistFreeBlock parsing from the backend', () => {
  it('parses the checklistFreeBlock with an object from the back end', () => {
    expect(freeChecklistBlock)
    .toEqual(freeBlockObject);
  });

  it('parses the checklistFreeBlock with an empty object from the back end', () => {
    const emptyFreeBlock = new ChecklistFreeBlock();
    expect(emptyFreeBlock)
    .toEqual(new ChecklistFreeBlock(undefined, undefined, undefined));
  });
});

describe('test ChecklistFreeBlock validation check', () => {
  it('returns true if the block is valid', () => {
    expect(freeChecklistBlock.isBlockValid())
    .toEqual(true);
  });
});
