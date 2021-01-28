import React, { Component } from 'react';
import axios, * as others from "axios";

import Counter from '../Practices/Counter.js';
import Trial from '../Practices/Trial.js';
import ListPrac from '../Practices/listPrac.js';

class Home extends Component {

    state = {
        ip: "http://222.232.15.205:8012",
        number: 0,
        trialList: [],
        trialList2: [],
        datas: []
    };

    getData = async () => {
        axios.get('https://jsonplaceholder.typicode.com/users')
            .then(({ data }) => {
                console.log(data);
                data.forEach((value, index) => {
                    if (this.state.trialList2.length < 10) {
                        this.setState(
                            {
                                datas: this.state.datas.concat(value),
                                trialList2: this.state.trialList2.concat(value.name + " | " + value.email)
                            }
                        )
                    }
                });
            })
            .catch(e => {
                console.error(e);
            });
    }

    componentDidMount() {
        this.addLine();
        this.getData();
    }

    addLine = () => {
        const { trialList } = this.state;
        var newArray = trialList.concat(Math.random());
        this.setState(
            {
                trialList: newArray
            }
        )
    }

    getRandomNumber = () => {
        const { trialList } = this.state;
        var rands = trialList.map(Math.random);
        console.log(rands);
        this.setState({
            trialList: rands
        })
    }

    handleIncrease = () => {
        const { number } = this.state;
        this.setState({
            number: number + 1
        });
    }

    handleDecrease = () => {
        const { number } = this.state;
        this.setState({
            number: number - 1
        });
    }

    render() {
        return (
            <div>
                <div>
                    <Counter
                        handleIncrease={this.handleIncrease}
                        handleDecrease={this.handleDecrease}
                        number={this.state.number}
                    />
                </div>
                <div>
                    <button onClick={this.getData}> Trial </button>
                    <ListPrac
                        items={this.state.trialList2}>
                    </ListPrac>
                </div>
                <div>
                    <button onClick={this.getRandomNumber}> 난수 생성 </button>
                    <button onClick={this.addLine}> 배열 증가 </button>
                    <ListPrac
                        items={this.state.trialList}>
                    </ListPrac>
                </div>
            </div>
        );
    }
}

export default Home;