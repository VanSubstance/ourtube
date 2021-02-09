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
            width: "800px",
            height: "300px",
            background: "#FFFFFF",
            color: "#000000",
            border: "3px solid red",
            margin: "100 100 100 100",
            padding: '60px'
            
        },
        options: {
            // 좋아요 싫어요 막대 그래프 옵션
            barLikes: {
                maintainAspectRatio: false,
                scales: {
                    xAxes: [{
                        stacked: true
                    }],
                    yAxes: [{
                        stacked: true,
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            },
            // 순위 선 그래프 옵션
            lineRankPerMonth: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            reverse: true
                        }
                    }]
                }
            },
            // 동영상 신규 조회수 선 그래프 옵션
            lineNumNewViews: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
                plugins: {
                    datalabels: {
                        display: false
                    }
                }
            },
            // 신규 동영상 개수 선 그래프 옵션
            lineNumNewVid: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                },
                plugins: {
                }
            }

        },
        datasets: {
            // 좋아요 싫어요 막대 그래프 변수
            barLikes: {
                datasets: [
                    {
                        label: "싫어요",
                        // 싫어요 데이터셋 위치
                        data: [],
                        backgroundColor: "#ff3399"
                    },
                    {
                        label: "좋아요",
                        // 좋아요 데이터셋 위치
                        data: [],
                        backgroundColor: "#00cc99"
                    }
                ],
                // 라벨 데이터셋 위치
                labels: []
            },
            // 순위 선 그래프 변수
            lineRankPerMonth: {
                datasets: [
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "red",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "blue",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "green",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "orange",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "purple",
                        borderWidth: 1
                    }

                ],
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월'
                , '9월', '10월', '11월', '12월']
            },
            // 동영상 신규 조회수 선 그래프 변수
            lineNumNewViews: {
                datasets: [
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "red",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "blue",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "green",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "orange",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "purple",
                        borderWidth: 1
                    }

                ],
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월'
                    , '9월', '10월', '11월', '12월']
            },
            // 신규 동영상 개수 선 그래프 변수
            lineNumNewVid: {
                datasets: [
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "red",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "blue",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "green",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "orange",
                        borderWidth: 1
                    },
                    {
                        label: "",
                        data: [],
                        fill: false,
                        borderColor: "purple",
                        borderWidth: 1
                    }

                ],
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월'
                    , '9월', '10월', '11월', '12월']
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
        // 좋아요 막대 그래프 변수
        var barLikes = [];
        barLikes.like = Math.floor(Math.random() * 100);
        barLikes.hate = Math.floor(Math.random() * 100);
        // 월별 순위 선 그래프 변수
        var lineRankPerMonth = [];
        for (var i = 0; i < 12; i++) {
            lineRankPerMonth.push(Math.floor(Math.random() * 20));
        }
        // 동영상 조회수 선 그래프 변수
        var lineNumNewViews = [];
        for (var i = 0; i < 12; i++) {
            lineNumNewViews.push(Math.floor(Math.random() * 1000));
        }
        // 새 동영상 선 그래프 변수
        var lineNumNewVid = [];
        for (var i = 0; i < 12; i++) {
            lineNumNewVid.push(Math.floor(Math.random() * 50));
        }
        return ({ 
            barLikes: barLikes, 
            lineRankPerMonth: lineRankPerMonth, 
            lineNumNewViews: lineNumNewViews,
            lineNumNewVid: lineNumNewVid
        });
    }

    // 데이터 가져오는 함수
    getDatasets = () => {
        const { datasets, ctgrs } = this.state;
        var dataLikes = [];
        var dataHates = [];
        var lineRankPerMonth = [];
        var lineNumNewViews = [];
        var lineNumNewVid = [];
        // 카테고리 별 데이터 가져오기: getDatasetFromCtgr(ctgr) 불러오기
        ctgrs.forEach((value, index) => {
            var data = this.getDatasetFromCtgr(value);
            dataLikes.push(data.barLikes.like);
            dataHates.push(data.barLikes.hate);
            lineRankPerMonth.push(data.lineRankPerMonth);
            lineNumNewViews.push(data.lineNumNewViews);
            lineNumNewVid.push(data.lineNumNewVid);
        })
        datasets.barLikes.datasets[0].data = dataHates;
        datasets.barLikes.datasets[1].data = dataLikes;
        datasets.barLikes.labels = ctgrs;
        for (var i = 0; i < 5; i++) {
            // 순위 선 그래프
            datasets.lineRankPerMonth.datasets[i].data = lineRankPerMonth[i];
            datasets.lineRankPerMonth.datasets[i].label = ctgrs[i];
            // 동영상 조회수 선 그래프
            datasets.lineNumNewViews.datasets[i].data = lineNumNewViews[i];
            datasets.lineNumNewViews.datasets[i].label = ctgrs[i];
            // 새 동영상 선 그래프
            datasets.lineNumNewVid.datasets[i].data = lineNumNewVid[i];
            datasets.lineNumNewVid.datasets[i].label = ctgrs[i];
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
     * 해당 카테고리에 따른 카테고리 리스트 새로고침
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
                    <p> 선 그래프: 카테고리 관련 동영상 신규 조회수 </p>
                    <Line
                        data={this.state.datasets.lineNumNewViews}
                        options={this.state.options.lineNumNewViews}
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 카테고리 관련 신규 동영상 개수 </p>
                    <Line
                        data={this.state.datasets.lineNumNewVid}
                        options={this.state.options.lineNumNewVid}
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 카테고리 관련 동영상 신규 조회수 </p>
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