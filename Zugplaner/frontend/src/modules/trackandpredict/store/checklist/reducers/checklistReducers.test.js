import { FETCHING_NOT_FETCHED } from '../../../../../store/common/helpers';
import { deserialize } from '../../../../common/utils';
import Checklist from '../../../models/Checklist';
import DamageReport from '../../../models/DamageReport';
import DateBlock from '../../../models/DateBlock';
import {
  askForChecklistCreationSuccess,
  askForChecklistsSuccess,
  askForChecklistDeletion,
  askForChecklists,
  askForResetChecklists,
  askForChecklistsError,
  askForSetCurrentChecklist,
} from '../actions/checklistActions';
import {
  getChecklists,
  getCurrentChecklist,
  areChecklistsFetched,
  areChecklistsLoading,
  isChecklistLoaded,
} from '../selectors/checklistSelectors';
import { getDamageReports } from '../selectors/damageReportSelectors';
import {
  checklists,
  currentChecklist,
  checklistsFetchingStatus,
  checklistsDictionary,
} from './checklistReducers';

describe('checklists reducer', () => {
  const callChecklistsReducerWithAction = (arrayOfChecklists, action) => ({
    checklists: checklists(arrayOfChecklists.map((checklist) => checklist.id), action),
    checklistsDictionary: checklistsDictionary(arrayOfChecklists.reduce((accumulator, checklist) => ({
      ...accumulator,
      [checklist.id]: checklist,
    }), {}), action)
  });

  it('is an empty array at initialization', () => {
    const initialState = [];
    const newState = { checklists: checklists(initialState, {action: 'RANDOM_ACTION'})};
    expect(getChecklists(newState))
      .toEqual([]);
  });

  it('holds the list of checklists when received', () => {
    const initialState = [];
    const receivedChecklists = [Checklist.empty().withId(1), Checklist.empty().withId(2)];
    const newState = callChecklistsReducerWithAction(initialState, askForChecklistsSuccess(receivedChecklists));
    expect(getChecklists(newState))
      .toEqual(receivedChecklists);
  });

  it('holds the first created checklist', () => {
    const initialState = [];
    const createdChecklist = Checklist.empty().withId(1);
    const newState = callChecklistsReducerWithAction(initialState, askForChecklistCreationSuccess(createdChecklist))
    expect(getChecklists(newState))
      .toEqual([createdChecklist]);
  });

  it('adds the last created checklist at the beginning of the checklists when received', () => {
    const receivedChecklists = [Checklist.empty().withId(1), Checklist.empty().withId(2)];
    const initialState = receivedChecklists;
    const createdChecklist = Checklist.empty().withId(3);
    const newState = callChecklistsReducerWithAction(initialState, askForChecklistCreationSuccess(createdChecklist))
    expect(getChecklists(newState))
      .toEqual([createdChecklist, ...receivedChecklists]);
  });

  it('removes the deleted checklist from the list', () => {
    const currentChecklists = [Checklist.empty().withId('1'), Checklist.empty().withId(2)];
    const initialState = currentChecklists;
    const newState = callChecklistsReducerWithAction(initialState, askForChecklistDeletion('1'));
    expect(getChecklists(newState))
      .toEqual([currentChecklists[1]]);
  });

  it('flushes the checklists when exiting the checklist tab', () => {
    const currentChecklists = [Checklist.empty().withId('1'), Checklist.empty()];
    const initialState = currentChecklists;
    const newState = callChecklistsReducerWithAction(initialState, askForResetChecklists());
    expect(getChecklists(newState))
      .toEqual([]);
  });

  it('selector selects checklists and filters out damage reports', () => {
    const initialState = [];
    const receivedReports = [Checklist.empty().withId('1'), Checklist.empty().withId(2), DamageReport.empty().withId(3)];
    const newState = callChecklistsReducerWithAction(initialState, askForChecklistsSuccess(receivedReports));
    expect(deserialize(getChecklists(newState)))
      .toEqual(deserialize([Checklist.empty().withId('1'), Checklist.empty().withId(2)]));
  });

  it('selector selects damage reports and filters out checklists', () => {
    const damageReport = DamageReport.empty().withId(3).withBlock(new DateBlock('damageDate', true, new Date('2021-03-08')));
    const initialState = [];
    const receivedReports = [Checklist.empty().withId('1'), Checklist.empty().withId(2), damageReport];
    const newState = callChecklistsReducerWithAction(initialState, askForChecklistsSuccess(receivedReports));
    expect(deserialize(getDamageReports(newState)))
      .toEqual(deserialize([damageReport]));
  });
});

describe('currentChecklist reducer', () => {
  const callCurrentChecklistReducerWithAction = (checklist, action) => ({
    currentChecklist: currentChecklist(checklist ? checklist.id : undefined, action),
    checklistsDictionary: checklistsDictionary(checklist ? { [checklist.id]: checklist } : {}, action)
  });

  it('is null at initialization', () => {
    const initialState = undefined;
    const newState = callCurrentChecklistReducerWithAction(initialState, { action: 'RANDOM_ACTION' });
    expect(getCurrentChecklist(newState))
      .toEqual(null);
  });

  it('returns the newly created checklist when received', () => {
    const checklist = Checklist.empty().withId(1);
    const initialState = null;
    const newState = callCurrentChecklistReducerWithAction(initialState, askForChecklistCreationSuccess(checklist));
    expect(getCurrentChecklist(newState))
      .toEqual(checklist);
  });

  it('returns null when the current checklist has been deleted', () => {
    const checklist = Checklist.empty().withId('1');
    const initialState = checklist;
    const newState = callCurrentChecklistReducerWithAction(initialState, askForChecklistDeletion(checklist.id));
    expect(getCurrentChecklist(newState))
      .toEqual(null);
  });

  it('returns null when the list of checklists are fetched', () => {
    const checklist = Checklist.empty().withId('1');
    const initialState = checklist;
    const newState = callCurrentChecklistReducerWithAction(initialState, askForChecklists(checklist.id));
    expect(getCurrentChecklist(newState))
      .toEqual(null);
  });

  it('set selected current checklist', () => {
    const currentChecklist = Checklist.empty().withId('1');
    const initialState = currentChecklist;
    const newState = callCurrentChecklistReducerWithAction(initialState, askForSetCurrentChecklist(1));
    expect(isChecklistLoaded(1)(newState))
      .toBe(true);
  });
});

describe('checklistsFetchingStatus reducer', () => {
  const callChecklistFetchingReducerWithAction = (previousState, action) => ({
    checklistsFetchingStatus: checklistsFetchingStatus(previousState, action),
  });

  it('is not fetched and not loading at initialization', () => {
    const initialState = FETCHING_NOT_FETCHED;
    const newState = { checklistsFetchingStatus: checklistsFetchingStatus(initialState, {action: 'RANDOM_ACTION'})};
    expect(areChecklistsFetched(newState)).toEqual(false);
    expect(areChecklistsLoading(newState)).toEqual(false);
  });

  it('is not fetched and not loading after a reset', () => {
    const initialState = FETCHING_NOT_FETCHED;
    const newState = callChecklistFetchingReducerWithAction(initialState, askForResetChecklists());
    expect(areChecklistsFetched(newState)).toEqual(false);
    expect(areChecklistsLoading(newState)).toEqual(false);
  });

  it('is fetched and loading when we fetch the checklists', () => {
    const initialState = FETCHING_NOT_FETCHED;
    const newState = callChecklistFetchingReducerWithAction(initialState, askForChecklists('1'));
    expect(areChecklistsFetched(newState)).toEqual(true);
    expect(areChecklistsLoading(newState)).toEqual(true);
  });

  it('is fetched and not loading when we received the checklists', () => {
    const initialState = FETCHING_NOT_FETCHED;
    const newState = callChecklistFetchingReducerWithAction(initialState, askForChecklistsSuccess([]));
    expect(areChecklistsFetched(newState)).toEqual(true);
    expect(areChecklistsLoading(newState)).toEqual(false);
  });

  it('is fetched and not loading when there was an error during the fetch of checklists', () => {
    const initialState = FETCHING_NOT_FETCHED;
    const newState = callChecklistFetchingReducerWithAction(initialState, askForChecklistsError('1'));
    expect(areChecklistsFetched(newState)).toEqual(true);
    expect(areChecklistsLoading(newState)).toEqual(false);
  });
});