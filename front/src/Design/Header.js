import React from 'react';

import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Home, About, User, MoonChartView } from '../Yang';
import { MainPageSimple, TrendMainPage, TrendResultPage} from './';

const App = () => {
  return (
    <Router>
      <div>
        <Route exact path='/home' component={Home} />
        <Route path='/about' component={About} />
        <Route path='/user' component={User} />
        <Route path='/moon1' component={MoonChartView} />
        <Route exact path='/' component={MainPageSimple} />

        <Route
          exact path='/trend'
          render={({location}) => 
            location.state !== undefined
            ? (
              <TrendMainPage searchType="트렌드" searchVal = {location.state.searchVal}></TrendMainPage>
            )
            : (
              <TrendMainPage searchType="트렌드" searchVal = "비디오 게임"></TrendMainPage>
            )
          }>
        </Route>

        <Route
          exact path='/trend/:keyword'
          component = {TrendResultPage}>
        </Route>
      </div>
    </Router>
  );
}
export default App;