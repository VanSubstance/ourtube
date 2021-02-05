import React, { Component } from 'react';
import { RankList } from './Comps';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Paper from '@material-ui/core/Paper';

class TrendMainPage extends Component {

    constructor (props) {
        super(props);
        console.log("TrendMainPage 생성");
        console.log(this.props.location);
    }

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
            width: "200px",
            height: "100px",
            background: "#000000",
            color: "#FFFFFF",
            margin: "100 100 100 100",
        }
    }
    componentDidMount() {
        this.selectCtgr(this.state.ctgrs[0]);
    }
    searchTracker = (track) => {
        this.setState({
            searchVal: track.target.value
        })
    }
    searchCtgr = () => {
        this.setState({
            ctgrs: [
                this.state.searchVal + "1",
                this.state.searchVal + "2",
                this.state.searchVal + "3",
                this.state.searchVal + "4",
                this.state.searchVal + "5",
            ]
        })
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
            console.log("SearchPage opened."),
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

                <RankList ref="CtgrResults">

                </RankList>
                <div
                    style={this.state.chart}>
                    <p> 바 그래프: 좋아요 싫어요 비율 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 월별 순위 변동 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 동영상 신규 조회수 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 신규 동영상 개수 </p>
                </div>

                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 키워드 관련 동영상 신규 조회수 </p>
                </div>
            </div>
        );
    }
}

export default TrendMainPage;