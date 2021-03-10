import React, { Component } from 'react';

import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Home, About, User, MoonChartView } from '../Yang';
import { MainPageSimple, TrendMainPage, AlltimeMainPage, TrendResultPage, AlltimeResultPage } from './';

class App extends Component {

  render() {
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
            render={({location}) => <TrendMainPage searchType="트렌드" searchVal = {location.state.searchVal}></TrendMainPage>}>
          </Route>
          <Route
            exact path='/alltime'
            render={({location}) => <AlltimeMainPage searchType="올타임" searchVal = {location.state.searchVal}></AlltimeMainPage>}>
          </Route>

          <Route
            exact path='/trend/:keyword'
            component = {TrendResultPage}>
          </Route>
          <Route
            exact path='/alltime/:keyword'
            component = {AlltimeResultPage}>
          </Route>
        </div>
      </Router>
    );
  }
}
export default App;