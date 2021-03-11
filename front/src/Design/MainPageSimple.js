import React, { useState } from 'react';
import { KeywordList } from './Comps';
import axios, * as others from "axios";
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import Chip from '@material-ui/core/Chip';
import "./Css/styles.css";

const MainPageSimple = (props) => {
    const [searchVal, setSearchVal] = useState(
        "비디오 게임"
    );
    
    const [selectedCtgr, setSelectedCtgr] = useState(
        ""
    );

    const [ctgrs, setCtgrs] = useState(
        [

        ]
    );

    const getDataset = async() => {
        axios.get('http://222.232.15.205:8082/deploy/topic/' + searchVal)
            .then(({ data }) => {
                if (data.length >= 8) {
                    setCtgrs(data.slice(0, 7));
                } else {
                    setCtgrs(data);
                }
            })
            .catch(e => {
                console.error(e);
            });
    };

    const searchCtgr = () => {
        // searchVal -> 가장 핫한 카테고리 8선
        if (searchVal === "") {
            getDataset();
            setSelectedCtgr("비디오 게임");
        } else {
            getDataset();
            setSelectedCtgr(searchVal);
            if (ctgrs.length == 0) {
                setSearchVal("비디오 게임");
                getDataset();
                setSelectedCtgr("비디오 게임");
            }
        }
    };

    const searchCtgrPress = (e) => {
        if(e.key === 'Enter') {
          setSearchVal(e.target.value);
          searchCtgr();
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