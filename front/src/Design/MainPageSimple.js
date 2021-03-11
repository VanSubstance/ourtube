import React, { useState } from 'react';
import { KeywordList } from './Comps';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import "./Css/styles.css";

const MainPageSimple = (props) => {
    const [searchVal, setSearchVal] = useState(
        ""
    );
    
    const [selectedCtgr, setSelectedCtgr] = useState(
        ""
    );

    const [ctgrs, setCtgrs] = useState([
        
    ]);

    // const getValues = () => {
    //     this.setState({
    //         searchType: this.props.searchType,
    //     })
    // };
    // const selectCtgr = (element) => {
    //     setSearchVal(element);
    //     this.refs.CtgrResults.refreshResults(element);
    // };

    // const searchTracker = (track) => {
    //     this.setState({
    //         searchVal: track.target.value
    //     })
    // };
    const searchCtgr = () => {
        // searchVal -> 가장 핫한 카테고리 8선
        if (searchVal === "") {
            setCtgrs([
                "핫 " + "1",
                "핫 " + "2",
                "핫 " + "3",
                "핫 " + "4",
                "핫 " + "5",
                "핫 " + "6",
                "핫 " + "7",
                "핫 " + "8",
            ]);
            setSelectedCtgr("핫 " + "1");
        } else {
            setCtgrs([
                searchVal + "1",
                searchVal + "2",
                searchVal + "3",
                searchVal + "4",
                searchVal + "5",
                searchVal + "6",
                searchVal + "7",
                searchVal + "8",
            ]);
            setSelectedCtgr(searchVal + "1");
        }
    };

    const searchCtgrPress = (e) => {
        if(e.key === 'Enter') {
          searchCtgr()
          console.log();
        }
    };

    return (
        <div
            className="mainContainor">
            <img
                className="backGroundImg"
                src="/Ex/backGroundIMG.PNG">
            </img>
            <div
                className="sectionContainor">
                <div
                    className="mainLeftSection">
                </div>
                <div
                    className="mainRightSection">
                    <div
                        className="linkBox">
                        <Link
                            to={{
                                pathname: "/trend",
                                state: {
                                    searchVal: searchVal
                                }
                            }}
                            className="chartLink"
                        >Chart View
                        </Link>
                    </div>
                    <div
                        className="mainBannerBox">
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
                        <div
                            className="searchBar">
                            <input
                                className="searchInput"
                                placeholder="검색어를 입력하세요"
                                autoComplete="off"
                                type="text"
                                maxlength="30"
                                onChange = {(e) => {setSearchVal(e.target.value)}}
                                onKeyPress = {(e) => {searchCtgrPress(e)}}
                            />
                            <button className="searchButton"
                                onClick = {() => {searchCtgr()}}
                            >
                                <img
                                    className="searchButtonImg"
                                    src="/Ex/searchbutton.png"></img>
                            </button>
                        </div>
                    </div>
                    <div
                        className="mainChipBox">
                        <div
                            className="chipBackground">
                            {
                                ctgrs.map((element) => {
                                    return (
                                        <Chip
                                            className="chipStyles"
                                            label={element}
                                            clickable
                                            component="button">
                                        </Chip>
                                    );
                                })
                            }
                        </div>
                    </div>
                    <KeywordList
                    >

                    </KeywordList>
                    <div
                        className="testTxtTop">
                            OURTUBE Analytics, Inc. © 2021
                    </div>
                    <div
                        className="testTxtBottom">
                        <d2>Ourtube is hosted by Ourtube  Analytics, Inc. Ourtube isn’t endorsed by Youtube and doesn’t reflect the views or opinions of youtube or anyone officially involved in producing or managing Youtube. youtube and Google are trademarks or registered trademarks of Google.Inc. Youtube ©  Google.Inc. </d2>
                    </div>
                </div>
            </div>    
            <footer
                    className="footer">
                    <div
                        className="footerBox">
                        <d1>FOOTER, 공지사항, 이용약관, 버그리포팅, 사이트 설명</d1>
                    </div>
            </footer>
        </div>
    );
};

export default MainPageSimple;