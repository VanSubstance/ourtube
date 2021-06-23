import React, { Component } from 'react';
import axios, * as others from "axios";

import Counter from '../Practices/Counter.js';
import Trial from '../Practices/Trial.js';
import ListPrac from '../Practices/listPrac.js';
import moment from 'moment'

class Home extends Component {

    state = {
        ip: "http://localhost:8082",
        number: 0,
        trialList: [],
        trialList2: [],
        data: []
    };

    getData = async () => {
        axios.get(this.state.ip+'/data/topic/rank/today')
            .then(({ data }) => {
                data.forEach((value, index) => {
                    if (this.state.trialList2.length < 10) {
                        console.log("작동");
                        this.setState(
                            {
                                data: this.state.data.concat(
                                    {
                                        text: value.topic,
                                        value: value.resultCount
                                    }
                                    ),
                                trialList2: this.state.trialList2.concat(value.topic + " | " + moment(Date(value.infoDate)).format('YY/MM/DD'))
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
        console.log(this.state.data);
        console.log(this.state.trialList2);
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
        var rands = trialList.concat(Math.floor(Math.random()*100));
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
                {
                    console.log(this.state.data)
                }
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