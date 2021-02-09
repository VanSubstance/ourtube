import React, { Component } from 'react';
import { RankList } from './Comps';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Paper from '@material-ui/core/Paper';
import {Bar, Line} from 'react-chartjs-2';
import 'chartjs-plugin-datalabels';


class AlltimeMainPage extends Component {

    
    state = {
        searchVal: "",
        selectedCtgr: "",
        ctgrs: ["카테고리 예시 1", "카테고리 예시 2", "예시 3", "예시 4", "카테고리 예시 5"],
        outside: {
            margin: "100px",
            background: "#DEDEDE",
        },
        searchBar: {
            width: "180px",
            height: "40px",
            background: "#c8c8c8",
            display: "inline",
        },
        logo: {
            width: "80px",
            height: "40px",
            background: "green",
            color: "white",
            display: "inline",
        },
        chart: {
            width: "300px",
            height: "300px",
            background: "#FFFFFF",
            color: "#000000",
            border: "3px solid red",
            margin: "100 100 100 100",
            padding: '60px'
            
        },
        options: {
            barLikes: {
                maintainAspectRatio: false,
                scales: {
                    xAxes: [{
                        stacked: true,
                        gridLines: {
                            color:'white'
                        }
                    }],
                    yAxes: [{
                        stacked: true,
                        ticks: {
                            beginAtZero: true
                        },
                        gridLines: {
                            color:'white'
                        }
                    }],
                    
                }
            },
            lineRankPerMonth: {
                elements: {
                    line: {
                        tension: 0 // disables bezier curves
                    }
                },
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        gridLines: {
                            color:'white'
                        },
                        ticks: {
                            max: 100,
                            beginAtZero: true,
                            
                        }
                    }],
                    xAxes: [{
                        stacked: true,
                        gridLines: {
                            color:'white'
                        }
                    }]
                }
            }
        },
        datasets: {
            barLikes: {
                
                datasets: [
                    {
                        label: "싫어요",
                        data: [7, 14],
                        backgroundColor: "#ff3399",
                        datalabels:{
                            color: 'white',
                            font: 'bold',
                            align:'center',
                            anchor:'center'
                        }
                    },
                    
                    {
                        label: "좋아요",
                        data: [35, 22],
                        backgroundColor: "#00cc99",
                        datalabels:{
                            
                            color: 'black',
                            font: 'bold',
                            align:'center',
                            anchor:'center',
                           
                        }
                    }
                ],
                labels: ['라벨 1', '라벨 2']
            },
            lineRankPerMonth: {
                datasets: [
                    {
                        label: "test1",
                        data: [],
                        fill: false,
                        borderColor: "red",
                        borderWidth : 1,
                        datalabels:{
                            backgroundColor:'orange',
                            color: 'white',
                            font: 'bold',
                            align:'center',
                            anchor:'center',
                           
                        }
                    },
                    {
                        label: "test2",
                        data: [],
                        fill: false,
                        borderColor: "blue",
                        borderWidth : 1,
                        
                    },
                    {
                        label: "test3",
                        data: [],
                        fill: false,
                        borderColor: "yellow",
                        borderWidth : 1
                    },
                    {
                        label: "test4",
                        data: [],
                        fill: false,
                        borderColor: "orange",
                        borderWidth : 1
                    },
                    {
                        label: "test5",
                        data: [],
                        fill: false,
                        borderColor: "black",
                        borderWidth : 1,
                        
                    }

                ],
                labels: ['JANUARY','FEBRUARY','MARCH','APRIL','MAY','JUNE','JULY','AUGUST'
                , 'SEPTEMBER','OCTOBER','NOVEMBER','DECEMBER']
                
            }
        }
    }
    // 컴포넌트 생성 직후 실행
    componentWillMount() {
        if (this.props.searchVal != undefined) {
            console.log("searchVal is " + this.props.searchVal);
            this.state.searchVal = this.props.searchVal;
        } else {
            console.log("searchVal is " + this.props.searchVal);
            this.state.searchVal = "기본값";
        }
        this.setState({
            ctgrs: [
                this.state.searchVal + "1",
                this.state.searchVal + "2",
                this.state.searchVal + "3",
                this.state.searchVal + "4",
                this.state.searchVal + "5",
            ],
            selectedCtgr: this.state.searchVal + "1",
        });
    }

    // 랜더링 후 실행
    componentDidMount() {
        this.searchCtgr();
    }

    // 카테고리의 데이터를 가져오는 함수
    getDatasetFromCtgr = (ctgr) => {
        // 좋아요 막대 그래프
        var barLikes = [];
        barLikes.like = Math.floor(Math.random()*100);
        barLikes.hate = Math.floor(Math.random()*100);

        // 월별 순위 선 그래프
        var lineRankPerMonth = [];
        for (var i = 0; i < 12; i ++) {
            lineRankPerMonth.push(Math.floor(Math.random()*20));
        }

        return ({
            barLikes: barLikes, 
            lineRankPerMonth: lineRankPerMonth
        });
    }

    // 데이터 가져오는 함수
    getDatasets = () => {
        const {datasets, ctgrs} = this.state;
        var dataLikes = [];
        var dataHates = [];
        var lineRankPerMonth =[];
        ctgrs.forEach((value, index) => {
            var data = this.getDatasetFromCtgr(value);
            dataLikes.push(data.barLikes.like);
            dataHates.push(data.barLikes.hate);
            lineRankPerMonth.push(data.lineRankPerMonth);
        })
        datasets.barLikes.datasets[0].data = dataHates;
        datasets.barLikes.datasets[1].data = dataLikes;
        datasets.barLikes.labels = ctgrs;
        for (var i = 0; i < 5; i++) {
            datasets.lineRankPerMonth.datasets[i].data = lineRankPerMonth[i];
            datasets.lineRankPerMonth.datasets[i].label = ctgrs[i];
        }

    }
    
    // 카테고리의 데이터를 가져오는 함수
    getDatasetFromCtgr = (ctgr) => {
        // 좋아요 막대 그래프 변수
        var barLikes = [];
        barLikes.like = Math.floor(Math.random()*100);
        barLikes.hate = Math.floor(Math.random()*100);
        // 월별 순위 선 그래프 변수
        var lineRankPerMonth = [];
        for (var i = 0; i < 12; i ++) {
            lineRankPerMonth.push(Math.floor(Math.random()*100));
        }
        return ({barLikes: barLikes, lineRankPerMonth: lineRankPerMonth});
    }

    // 데이터 가져오는 함수
    getDatasets = () => {
        const {datasets, ctgrs} = this.state;
        var dataLikes = [];
        var dataHates = [];
        var lineRankPerMonth =[];
        ctgrs.forEach((value, index) => {
            var data = this.getDatasetFromCtgr(value);
            dataLikes.push(data.barLikes.like);
            dataHates.push(data.barLikes.hate);
            lineRankPerMonth.push(data.lineRankPerMonth);
        })
        datasets.barLikes.datasets[0].data = dataHates;
        datasets.barLikes.datasets[1].data = dataLikes;
        datasets.barLikes.labels = ctgrs;
        for (var i = 0; i < 5; i++) {
            datasets.lineRankPerMonth.datasets[i].data = lineRankPerMonth[i];
            datasets.lineRankPerMonth.datasets[i].label = ctgrs[i];
        }

    }

    searchTracker = (track) => {
        this.setState({
            searchVal: track.target.value
        })
    }

    /**
     * ------------------------------------------------------------------------------> 김종규
     * 현재 입력되어있는 searchVal로 카테고리 검색
     */
    searchCtgr = () => {
        this.state.ctgrs = [
            this.state.searchVal + "1",
            this.state.searchVal + "2",
            this.state.searchVal + "3",
            this.state.searchVal + "4",
            this.state.searchVal + "5",
        ];
        this.state.selectedCtgr = this.state.ctgrs[0];
        this.selectCtgr(this.state.selectedCtgr);
        this.getDatasets();
    }
    
    /**
     * ------------------------------------------------------------------------------> 김종규
     * 카테고리 선택
     * 해당 카테고리에 따른 키워드 리스트 새로고침
     */
    selectCtgr = (element) => {
        this.setState({
            selectedCtgr: element
        });
        this.refs.CtgrResults.refreshResults(element);
    }

    render() {
        return (
            <div
                className="SearchModule"
                style={this.state.outside}>
                <Link to="/">메인으로</Link>
                <br></br>
                <div
                    style={this.state.logo}>
                    올타임
                            </div>
                <input style={this.state.searchBar} type="text" onChange={this.searchTracker} />
                <button style={this.state.logo} onClick={this.searchCtgr}> 검색 </button>
                <Paper>
                    {
                        this.state.ctgrs.map((element) => {
                            return (
                                <Chip
                                    label={element}
                                    clickable
                                    component="button"
                                    onClick={() => this.selectCtgr(element)}>
                                </Chip>
                            );
                        })
                    }
                </Paper>
                <RankList type = "올타임" ref="CtgrResults">

                </RankList>
                <div
                    style={this.state.chart}>
                    <Bar 
                        data = {this.state.datasets.barLikes}                        
                        options={this.state.options.barLikes}
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 월별 순위 변동 </p>
                    <Line 
                        data = {this.state.datasets.lineRankPerMonth}
                        options={this.state.options.lineRankPerMonth}
                        
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 동영상 신규 조회수 </p>
                    <Line 
                        data = {{
                            labels: ['test1','test2','test3','test4','test5'],
                            datasets: [{
                                label: 'test',
                                data: [
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),                                    
                                ],
                                backgroundColor: [
                                    'white',
                                                                       
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)'                                    
                                ],
                                borderWidth: 1,
                                datalabels:{
                                    backgroundColor:'rgba(255, 99, 132, 1)',
                                    color: 'white',
                                    font: 'bold',
                                    align:'start',
                                    anchor:'end',
                                    offset: -25,
                                    borderRadius: 4,
                                    padding : 6
                                   
                                },
                                
                            }]                            
                        }}
                        options={{
                            elements: {
                                line: {
                                    tension: 0 // disables bezier curves
                                }
                            },
                            maintainAspectRatio: false,
                            scales: {
                                yAxes: [{
                                    gridLines: {
                                        display: false
                                    },
                                    ticks: {
                                        max: 100,
                                        beginAtZero: true
                                }
                            }],
                                xAxes: [{
                                    gridLines: {
                                        display: false
                                    }
                                }]
                        }
                    }}
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 신규 동영상 개수 </p>
                    <Line 
                        data = {{
                            labels: ['test1','test2','test3','test4','test5'],
                            datasets: [{
                                label: 'test',
                                data: [
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100)
                                   
                                ],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                                                       
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    
                                ],
                                borderWidth: 1
                            }]                            
                        }}
                        options={{
                            maintainAspectRatio: false,
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        max: 100,
                                        beginAtZero: true
                                }
                            }]
                        }
                    }}
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 동영상 신규 조회수 </p>
                    <Line 
                        data = {{
                            labels: ['test1','test2','test3','test4','test5'],
                            datasets: [{
                                label: 'test',
                                data: [
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100)
                                    
                                ],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                                                       
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    
                                ],
                                borderWidth: 1
                            }]                            
                        }}
                        options={{
                            maintainAspectRatio: false,
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        max: 100,
                                        beginAtZero: true
                                }
                            }]
                        }
                    }}
                    />
                </div>
            </div>
        );
    }
}

export default AlltimeMainPage;