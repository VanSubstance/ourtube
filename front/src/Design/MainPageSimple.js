import React, { useState, useEffect } from "react";
import { KeywordList } from "./Comps";
import axios from "axios";
import { BrowserRouter as Link } from "react-router-dom";
import Chip from "@material-ui/core/Chip";
import "./Css/styles.css";
import LeftBox from "./LeftBox";

const MainPageSimple = (props) => {
  const [url] = useState("http://222.232.15.205:8082");

  let [searchVal] = useState("FPS");

  let [ctgrs, setCtgrs] = useState([]);

  let [keywords, setKeywords] = useState([
    {
      title: "초기값",
      thumbnail:
        "https://upload.wikimedia.org/wikipedia/commons/9/92/Question_mark_alternate_inverted.svg",
    },
  ]);

  let [selectedKeyword, setSelectedKeyword] = useState({
    title: "선택 초기값",
    thumbnail:
      "https://upload.wikimedia.org/wikipedia/commons/9/92/Question_mark_alternate_inverted.svg",
  });

  let [dataLeft, setDataLeft] = useState(
    [

    ]
  );

  const selectKeyword = async (keyword) => {
    selectedKeyword = keyword;
    await setSelectedKeyword(keyword);
    getDatasetForLeftByGame(selectedKeyword.title);
  };

  useEffect(() => {
    getDataset();
    getDatasetForKeyword("FPS");
  }, []);

  const getDatasetForKeyword = async (ctgr) => {
    await axios
      .get(url + "/deploy/game/list/" + ctgr)
      .then(({ data }) => {
        if (data.length >= 10) {
          setKeywords(data.slice(0, 10));
          selectKeyword(data[0]);
        } else {
          setKeywords(data);
          selectKeyword(data[0]);
        }
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const getDataset = async () => {
    await axios
      .get(url + "/deploy/topic/" + searchVal)
      .then(({ data }) => {
        if (data.length >= 8) {
          setCtgrs(data.slice(0, 7));
        } else {
          setCtgrs(data);
        }
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const getDatasetForLeftByGame = async (title) => {
    await axios
      .get(url + "/deploy/game/main/" + title)
      .then(({ data }) => {
        setDataLeft(data);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const searchCtgr = () => {
    if (searchVal === "") {
      getDataset();
      getDatasetForKeyword("FPS");
    } else {
      getDataset();
      getDatasetForKeyword(searchVal);
      if (ctgrs.length === 0) {
        searchVal = "FPS";
        getDataset();
        getDatasetForKeyword("FPS");
      }
    }
  };

  const searchCtgrPress = (e) => {
    if (e.key === "Enter") {
      searchVal = e.target.value;
      searchCtgr();
      console.log(searchVal);
    }
  };

  return (
    <div className="mainContainor">
      <img className="backGroundImg" src="/Ex/backGroundIMG.PNG"></img>
      <div className="sectionContainor">
        <div className="mainLeftSection">
          <LeftBox
            clickEvent = {props.clickEvent}
            keyword = {selectedKeyword}
            data = {dataLeft}>
          </LeftBox>
        </div>
        <div className="mainRightSection">
          <a
            className="chartLinkBox"
            href="http://localhost:3012/trend">
            <div
              className="chartLink">
              Chart View
              </div>
          </a>
          <div className="mainBannerBox">
            <a href="http://localhost:3012/" className="bannerMain">
              <img className="bannerImage" src="/Ex/ourtubeLogoWhite.PNG"></img>
            </a>
          </div>
          <div className="mainCategoriesBox">
            <div className="searchBar">
              <input
                className="searchInput"
                placeholder="검색어를 입력하세요"
                autoComplete="off"
                type="text"
                maxLength="30"
                onKeyPress={(e) => {
                  searchCtgrPress(e);
                }}
              />
              <button
                className="searchButton"
                onClick={() => {
                  searchCtgr();
                }}
              >
                <img
                  className="searchButtonImg"
                  src="/Ex/searchbutton.png"
                ></img>
              </button>
            </div>
          </div>
          <div className="mainChipBox">
            <div className="chipBackground">
              {ctgrs.map((element) => {
                return (
                  <Chip
                    className="chipStyles"
                    label={element}
                    clickable
                    onClick={() => {
                      getDatasetForKeyword(element);
                    }}
                    component="button"
                  ></Chip>
                );
              })}
            </div>
          </div>
          <KeywordList keywords={keywords} clickEvent={selectKeyword} />
          <div className="testTxtTop">OURTUBE Analytics, Inc. © 2021</div>
          <div className="testTxtBottom">
            Ourtube is hosted by Ourtube Analytics, Inc. Ourtube isn’t endorsed
            by Youtube and doesn’t reflect the views or opinions of youtube or
            anyone officially involved in producing or managing Youtube. youtube
            and Google are trademarks or registered trademarks of Google.Inc.
            Youtube © Google.Inc.
          </div>
        </div>
      </div>
      {/* <footer className="footer">
        <div className="footerBox">
          FOOTER, 공지사항, 이용약관, 버그리포팅, 사이트 설명
        </div>
      </footer> */}
    </div>
  );
};

export default MainPageSimple;
