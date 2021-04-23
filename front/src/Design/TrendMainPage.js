import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Styles.css";
import './Css/TrendMainPage.css';
import Chip from "@material-ui/core/Chip";
import { ListFont } from "./Comps";
import { Bar, Line } from "react-chartjs-2";

const TrendMainPage = () => {
  const [url] = useState("http://222.232.15.205:8082");

  let [searchVal] = useState("FPS");

  let [selectedCtgr, setSelectedCtgr] = useState("");

  let [ctgrs, setCtgrs] = useState([]);

  let [keywords, setKeywords] = useState([]);

  let [selectedKeywords] = useState([]);

  const [barInfo, setBarInfo] = useState({
    data: {
      labels: ['1', '2', '3', '4', '5'],
      datasets: [
        {
          data: [11000, 12000, 13000, 14000, 15000],
          backgroundColor: ["red", "blue", "yellow", "black", "purple"],
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
      },
    },
  });

  const [lineRankInfo, setlineRankInfo] = useState({
    data: {
      labels: ['1', '2', '3', '4', '5'],
      datasets: [
        {
          data: [11000, 12000, 13000, 14000, 15000],
          borderColor: "red"
        }
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
      },
    }
  });

  const [lineViewInfo, setlineViewInfo] = useState({
    data: {
      labels: ['1', '2', '3', '4', '5'],
      datasets: [
        {
          data: [11000, 12000, 13000, 14000, 15000],
          borderColor: "blue"
        }
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
      },
    }
  });

  const [line3Info, setline3Info] = useState({
    data: {
      labels: ['1', '2', '3', '4', '5'],
      datasets: [
        {
          data: [11000, 12000, 13000, 14000, 15000],
          borderColor: "white"
        }
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
      },
    }
  });

  const setDataBarEx = (data) => {
    let dataBar = [];
    data.foreach((value, key) => {
      dataBar.push(value.viweCount);
    });
    const newInfo = {
      data: {
        labels: [data.key],
        datasets: [
          {
            data: dataBar,
            backgroundColor: ["red", "blue"],
            background: "",
            label: ["조회수"],
          },
        ],
      },
      options: {
        maintainAspectRatio: false,
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
            },
          ],
        },
      },
    };
    setBarInfo(newInfo);
  };

  useEffect(() => {
    getDataset();
    getDatasetForKeyword(searchVal);
  }, []);

  const checkKeywords = (keyword, method) => {
    if (method == 0) {
      selectedKeywords = selectedKeywords.concat(keyword);
    } else {
      selectedKeywords = selectedKeywords.filter(k => k != keyword);
    }
    console.log(selectedKeywords);
  }

  const getDataset = async () => {
    await axios
      .get("http://222.232.15.205:8082/deploy/topic/" + searchVal)
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

  const getDataBar = async () => {
    await axios
      .get("http://222.232.15.205:8082/deploy/chart/" + searchVal)
      .then(({ data }) => {
        if (data.length >= 5) {
          setDataBarEx(data.slice(0, 5));
        } else {
          setDataBarEx(data);
        }
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

  const getDatasetForKeyword = async (ctgr) => {
    await axios
      .get(url + "/deploy/game/list/" + ctgr)
      .then(({ data }) => {
        if (data.length >= 10) {
          setKeywords(data.slice(0, 10));
        } else {
          setKeywords(data);
        }
      })
      .catch((e) => {
        console.error(e);
      });
  };

  const searchCtgrPress = (e) => {
    if (e.key === "Enter") {
      searchVal = e.target.value;
      searchCtgr();
      console.log();
    }
  };

  return (
    <div
      className="tmp_MainWrapper">
      <div
        className="tmp_BackGroundPanel">
      </div>

      {/* //배경 및 그라데이션  */}

      <div id="tmp_ScollWallPaper">
        <img
          className="tmp_ScrollWallPaperImg"
          src="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png"
          srcSet="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png">
        </img>
      </div>

      {/* <div id="scollWallpaper_">
      <img
        id="andy-holmes-rCbdp8VCYhQ-unspla1"
        src="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png"
        srcSet="./Ex/andy-holmes-rCbdp8VCYhQ-unspla.png 1x, ./Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png 2x"
      />
    </div>
    <svg className="scollGradient" viewBox="0 0 1920 1500">
      <linearGradient
        id="_137_bn"
        spreadMethod="pad"
        x1="0.952"
        x2="0.071"
        y1="0.067"
        y2="0.886"
      >
        <stop offset="0" stopColor="#f60" stopOpacity="1"></stop>
        <stop offset="0.1673" stopColor="#cf8845" stopOpacity="1"></stop>
        <stop offset="0.3389" stopColor="#b79967" stopOpacity="1"></stop>
        <stop offset="0.4937" stopColor="#b4838b" stopOpacity="1"></stop>
        <stop offset="0.6735" stopColor="#b16ab4" stopOpacity="1"></stop>
        <stop offset="0.7991" stopColor="#7c84b9" stopOpacity="1"></stop>
        <stop offset="1" stopColor="#28adc2" stopOpacity="1"></stop>
      </linearGradient>
      <path
        id="Gradient"
        d="M 0 0 L 1920 0 L 1920 1500 L 0 1500 L 0 0 Z"
      ></path>
    </svg> */}

      {/* 헤더 */}

      <div id="header">
        <div
          className="tmp_BannerBox">
          <a
            className="tmp_BannerA"
            href="http://localhost:3012/">
            <img
              className="tmp_BannerImage"
              src="/Ex/ourtubeLogoWhite.PNG">
            </img>
          </a>
        </div>
      </div>

      {/* 컨테이너 */}

      <div id="container">
        <div
          className="tmp_LeftBox">
          <div
            className="tmp_SearchBox">
            <div className="tmp_SearchBar">
              <input
                className="tmp_searchInput"
                placeholder="검색어를 입력하세요"
                autoComplete="off"
                type="text"
                maxLength="30"
                onKeyPress={(e) => {
                  searchCtgrPress(e);
                }}
              />
              <button
                className="tmp_searchButton"
                onClick={() => {
                  searchCtgr();
                }}
              >
                <img
                  className="tmp_searchButtonImg"
                  src="/Ex/searchButtonBlack.png"
                ></img>
              </button>
            </div>
            <div className="tmp_MainChipBox">
              <div className="tmp_chipBackground">
                {ctgrs.map((element) => {
                  return (
                    <Chip
                      className="mainChipSyle5"
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
          </div>
          <div
            className="tmp_KeywordRankBox">
            <div
              className="tmp_BoxNameBar">
            </div>
            <ListFont keywords={keywords} func={checkKeywords}></ListFont>
          </div>
        </div>
        <div
          className="tmp_RightBox">
          <div
            className="tmp_PFBox">
            <div
              className="tmp_PFThumbnailCircle">
              <img
                className="tmp_PFThumbnail"
                src="/Ex/happy.jpg"></img>
            </div>
            <div
              className="tmp_PFKeywordName">
              {/* {props.match.params.keyword} */}
            </div>
            <div
              className="tmp_PFKeywordYear">
              테스트 제작연도
                            </div>
            <div
              className="tmp_PFKeywordCompony">
              테스트 제작사
                            </div>
          </div>
        </div>
      </div>
      <div id="rightboxfontgps">
      </div>
    </div>
  );
};

export default TrendMainPage;