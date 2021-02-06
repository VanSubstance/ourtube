import React, { Component } from 'react';
import { CtgrResults } from './Comps';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Paper from '@material-ui/core/Paper';

class MainPageSimple extends Component {

    state = {
        currentType: "트렌드",
        searchVal: "",
        selectedCtgr: "",
        ctgrs: ["카테고리 예시 1", "카테고리 예시 2", "예시 3", "예시 4", "카테고리 예시 5"],
        outside: {
            margin: "100px",
            background: "#DEDEDE",
        },
        searchBar: {
            width: "180px",
            height: "20px",
            background: "white",
            display: "inline",
            margin: "20px",
        },
        logo: {
            width: "80px",
            height: "40px",
            background: "red",
            color: "white",
            display: "inline",
        },
    }

    getValues = () => {
        this.setState({
            searchType: this.props.searchType,
        })
    }
    /** 
     * 트렌드 <-> 올타임
     * 변경 시 해당 타입에 따른 카테고리 호출
     * 카테고리 1번째 자동 선택
     * */
    changeType = () => {
        var target = "";
        var color = "green";
        if (this.state.currentType == "트렌드") {
            target = "올타임";
            color = "green";
        } else {
            target = "트렌드";
            color = "red";
        }
        this.setState({
            searchVal: "",
            currentType: target,
            logo: {
                width: "80px",
                height: "40px",
                background: color,
                color: "white",
                display: "inline",
            },
        });
        this.searchCtgr();
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
        console.log("Refresh Keywords")
        this.refs.CtgrResults.refreshResults(element);
    }

    componentDidMount() {
        this.getValues();
        this.selectCtgr(this.state.ctgrs[0]);
    }

    searchTracker = (track) => {
        this.setState({
            searchVal: track.target.value
        })
    }
    /**
     * ------------------------------------------------------------------------------> 김종규
     * 카테고리 검색 
     * 검색 결과 반환
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

    render() {
        return (
            <div
                className="SearchModule"
                style={this.state.outside}>
                {
                    this.state.currentType === "트렌드"
                        ? (
                            <button
                                style={this.state.logo}
                                onClick={this.changeType}>
                                {this.state.currentType}
                            </button>
                        )
                        : (
                            <button
                                style={this.state.logo}
                                onClick={this.changeType}>
                                {this.state.currentType}
                            </button>
                        )
                }

                <input style={this.state.searchBar} type="text" onChange={this.searchTracker} />
                <button style={this.state.logo} onClick={this.searchCtgr}> 검색 </button>

                {
                    this.state.currentType === "트렌드"
                        ? (
                            <Link
                                style={this.state.logo}
                                to={{
                                    pathname: "/trend",
                                    state: {
                                        searchVal: this.state.searchVal
                                    }
                                }}
                            >자세히 보기</Link>
                        )
                        : (
                            <Link
                                to="/alltime"
                                style={this.state.logo}
                            >자세히 보기</Link>
                        )
                }

                {
                    // 
                    this.state.searchType === "트렌드"
                        ? (
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
                        )
                        : (
                            <Paper>
                                {
                                    this.state.ctgrs.map((element) => {
                                        const path = "/alltime/" + element;
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
                        )
                }
                <CtgrResults
                    ref="CtgrResults"
                >

                </CtgrResults>
            </div>
        );
    }
}

export default MainPageSimple;