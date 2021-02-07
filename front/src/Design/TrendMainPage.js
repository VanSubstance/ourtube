import React, { Component } from 'react';
import { RankList } from './Comps';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Paper from '@material-ui/core/Paper';
import {Bar, Line} from 'react-chartjs-2';

class TrendMainPage extends Component {

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
            background: "red",
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
        }
    }
    componentDidMount() {
        if (this.props.searchVal != undefined) {
            console.log("searchVal is " + this.props.searchVal);
            this.state.searchVal = this.props.searchVal;
        } else {
            console.log("searchVal is " + this.props.searchVal);
            this.state.searchVal = "기본값";
        }
        this.searchCtgr();
        this.selectCtgr(this.state.ctgrs[0]);
    }
    searchTracker = (track) => {
        this.state.searchVal = track.target.value;
    }
    /**
     * ------------------------------------------------------------------------------> 김종규
     * 현재 입력되어있는 searchVal로 카테고리 검색
     */
    searchCtgr = () => {
        this.setState({
            ctgrs: [
                this.state.searchVal + "1",
                this.state.searchVal + "2",
                this.state.searchVal + "3",
                this.state.searchVal + "4",
                this.state.searchVal + "5",
            ],
            selectedCtgr: this.state.searchVal + "1",
        })
        this.refs.CtgrResults.refreshResults(this.state.searchVal + "1");
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
                    트렌드
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

                <RankList type = "트렌드" ref="CtgrResults">

                </RankList>
                <div
                    style={this.state.chart}>
                    <p> 바 그래프: 좋아요 싫어요 비율 </p>
                    <Bar 
                        data = {{
                            labels: ['좋아요', '싫어요'],
                            datasets: [{
                                label: 'test',
                                data: [
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100)
                                ],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(54, 162, 235, 1)'
                                ],
                                borderWidth: 1
                            }]                            
                        }}                        
                        options={{
                            maintainAspectRatio: false,
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                }
                            }]
                        }
                    }}
                    />
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 월별 순위 변동 </p>
                    <Line 
                        data = {{
                            labels: ['JANUARY','FEBRUARY','MARCH','APRIL','MAY','JUNE','JULY','AUGUST'
                                    , 'SEPTEMBER','OCTOBER','NOVEMBER','DECEMBER'],
                            datasets: [{
                                label: 'test',
                                data: [
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
                                    Math.floor(Math.random()*100),
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
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)'
                                ],
                                borderWidth: 1
                            }]                            
                        }}
                        options={{
                            maintainAspectRatio: false,
                            scales: {
                                yAxes: [{
                                    ticks: {
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
                                    Math.floor(Math.random()*100),                                    
                                ],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                                                       
                                ],
                                borderColor: [
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)',
                                    'rgba(255, 99, 132, 1)'                                    
                                ],
                                borderWidth: 1
                            }]                            
                        }}
                        options={{
                            maintainAspectRatio: false,
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true
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

export default TrendMainPage;