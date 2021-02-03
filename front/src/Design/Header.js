import React, { Component } from 'react';

import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Home, About, User } from '../Yang';
import { MainPage, TrendMainPage, AlltimeMainPage } from './';

class App extends Component {

  render() {
    return (
      <Router>
        <div>
          <Route exact path='/home' component={Home} />
          <Route path='/about' component={About} />
          <Route path='/user' component={User} />
          <Route exact path='/' component={MainPage} />
          <Route
            exact path = '/trend'
            render = {() => <TrendMainPage searchType = "트렌드"></TrendMainPage>}>
          </Route>
          <Route
            exact path = '/alltime'
            render = {() => <AlltimeMainPage searchType = "올타임"></AlltimeMainPage>}>
          </Route>
          
        </div>
      </Router>
    );
  }
}
export default App;