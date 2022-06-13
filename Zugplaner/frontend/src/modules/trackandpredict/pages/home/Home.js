import React from 'react';

import AssetSplitPane from '../assets-table/AssetSplitPaneWithLoader';
import MainLayout from '../../../common/components/layout/main-layout/MainLayout';

const Home = () => (
  <MainLayout moduleName="Track&Predict" moduleHome="/track-and-predict">
    <div>
      <AssetSplitPane />
    </div>
  </MainLayout>

);

export default Home;
