import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import { MainPageSimple, TrendMainPage, TrendResultPage } from "./";

const App = () => {
  return (
    <Router>
      <div>
        <Route exact path="/" component={MainPageSimple} />

        <Route
          exact
          path="/chart"
          render={({ location }) =>
            location.state !== undefined ? (
              <TrendMainPage
                searchType="트렌드"
                searchVal={location.state.searchVal}
              ></TrendMainPage>
            ) : (
              <TrendMainPage
                searchType="트렌드"
                searchVal="비디오 게임"
              ></TrendMainPage>
            )
          }
        ></Route>

        <Route exact path="/game/:keyword" component={TrendResultPage}></Route>
      </div>
    </Router>
  );
};
export default App;
