import React, { Component } from 'react';
import { KeywordList } from './Comps';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import Paper from '@material-ui/core/Paper';
import "./Css/styles.css";

class MainPageSimple extends Component {

    state = {
        currentType: "트렌드",
        searchVal: "",
        selectedCtgr: "",
        ctgrs: [],
    }

    //렌더링을 하기 전에 작업을 해주는 것 :)
    componentWillMount() {
        this.searchCtgr();
    }

    //렌더링을 끝낸 다음에 작업 :)
    componentDidMount() {
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
        this.state.searchVal = "";
        this.state.currentType = target;
        this.refs.searchBar.value = "";
        this.searchCtgr();
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
        // searchVal -> 가장 핫한 카테고리 8선
        if (this.state.searchVal === "") {
            this.setState({
                ctgrs: [
                    "핫 " + "1",
                    "핫 " + "2",
                    "핫 " + "3",
                    "핫 " + "4",
                    "핫 " + "5",
                    "핫 " + "6",
                    "핫 " + "7",
                    "핫 " + "8",
                ],
                selectedCtgr: "핫 " + "1",
            });
        } else {
            this.setState({
                ctgrs: [
                    this.state.searchVal + "1",
                    this.state.searchVal + "2",
                    this.state.searchVal + "3",
                    this.state.searchVal + "4",
                    this.state.searchVal + "5",
                    this.state.searchVal + "6",
                    this.state.searchVal + "7",
                    this.state.searchVal + "8",
                ],
                selectedCtgr: this.state.searchVal + "1",
            });
        }
    }

    render() {
        return (
            <div
                className="mainBackground">
                <div
                    className="linkBox">
                    <Link
                        to={{
                            pathname: "/trend",
                            state: {
                            searchVal: this.state.searchVal
                            }
                        }}
                        className="chartLink"
                    >Chart View
                    </Link>    
                </div>
                <div
                    className="mainBanner">
                    <a
                        href="http://localhost:3012/"
                        className="bannerA">
                        <img
                            className="bannerImage"
                            src="/Ex/ourtubeLogo.PNG">
                        </img>
                    </a>
                </div>
                <div
                    className="mainCategoriesBox">

                    {/* <button
                            className = "trendImage">
                                <d1>TREND</d1>
                        </button> */}

                    {/* {
                        this.state.currentType === "트렌드"
                            ? (
                                <button
                                    className="logoTrend"
                                    onClick={this.changeType}>
                                    {this.state.currentType}
                                </button>
                            )
                            : (
                                <button
                                    className="logoAlltime"
                                    onClick={this.changeType}>
                                    {this.state.currentType}
                                </button>s
                            )
                    } */}
                    <div
                        className="searchBar">
                        <input
                            className="searchInput"
                            placeholder="검색어를 입력하세요"
                            autoComplete="off"
                            ref="searchBar"
                            type="text"
                            onChange={this.searchTracker} />
                        <button className="searchButton" onClick={this.searchCtgr}>
                            <img
                                className="searchButtonImg"
                                src="/Ex/searchbutton.png"></img>
                        </button>
                    </div>

                    {/* {
                        this.state.currentType === "트렌드"
                            ? (
                                <Link
                                    to={{
                                        pathname: "/trend",
                                        state: {
                                            searchVal: this.state.searchVal
                                        }
                                    }}
                                >Chart View</Link>
                            )
                            : (
                                <Link
                                    to={{
                                        pathname: "/alltime",
                                        state: {
                                            searchVal: this.state.searchVal
                                        }
                                    }}
                                >Chart View</Link>
                            )
                    } */}

                </div>
                <div
                    className="mainChipBox">

                    <div
                        className="chipBackground">
                        {
                            this.state.ctgrs.map((element) => {
                                return (
                                    <Chip
                                        className="chipStyles"
                                        label={element}
                                        clickable
                                        component="button"
                                        onClick={() => this.selectCtgr(element)}>
                                    </Chip>
                                );
                            })
                        }
                    </div>

                    {/* {
                        this.state.searchType === "트렌드"
                            ? (
                                <div
                                    className="chipBackground">
                                    {
                                        this.state.ctgrs.map((element) => {
                                            return (
                                                <Chip
                                                    className="chipStyles"
                                                    label={element}
                                                    clickable
                                                    component="button"
                                                    onClick={() => this.selectCtgr(element)}>
                                                </Chip>
                                            );
                                        })
                                    }
                                </div>
                            )

                            올타임 Chip 관련 내용
                            : (
                                <div
                                    className="chipBackground">
                                    {
                                        this.state.ctgrs.map((element) => {
                                            const path = "/alltime/" + element;
                                            return (
                                                <Chip
                                                    className="chipStyles"
                                                    label={element}
                                                    clickable
                                                    component="button"
                                                    onClick={() => this.selectCtgr(element)}>
                                                </Chip>
                                            );
                                        })
                                    }
                                </div>
                            )
                    } */}

                </div>
                <KeywordList
                    type={this.state.currentType}
                    ref="CtgrResults"
                >

                </KeywordList>
                <footer
                    className="footer">
                    <div
                        className="footerBox">
                        <d1>FOOTER, 공지사항, 이용약관, 버그리포팅, 사이트 설명</d1>
                    </div>
                </footer>
            </div>
        );
    }
}

export default MainPageSimple;