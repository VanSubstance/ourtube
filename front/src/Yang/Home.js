import React, {Component} from 'react';
import logo from '../logo.svg';
import Counter from '../Counter.js';
import Trial from '../Trial.js';

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
          number: 0,
          trialList: []
        };
      }
    
      handleIncrease = () => {
        const { number, trialList } = this.state;
        this.setState({
          number: number + 1
        });
      }
    
      handleDecrease = () => {
        const { number, trialList } = this.state;
        this.setState({
          number: number - 1
        });
      }
    
    render() {
    
        return (
            <div className="App">
            <header className="App-header">
              <img
              src={logo} className="App-logo" alt="logo" />
              <Counter
                handleIncrease={this.handleIncrease}
                handleDecrease={this.handleDecrease}
                number={this.state.number}
              />
              <Trial
                trialList={this.state.trialList}
              />
            </header>
          </div>
        );
    }
}

export default Home;