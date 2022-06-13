import { deserialize } from '../../common/utils';
import AssetDistance from "./AssetDistance";

describe('test asset distance model parsing from back end', () => {
  it('parses the asset distance from the back end', () => {
    const assetDistanceBlob = {
      dailyDistance: 1,
      yesterday: 2,
      actualMonth: 2,
      lastMonth: 2,
      monthlyTotals: [],
    };
    const assetDistance = AssetDistance.fromBlob(assetDistanceBlob);

    expect(deserialize(assetDistance)).toEqual(
      deserialize(AssetDistance.fromBlob(assetDistanceBlob)),
    );
  });
});
