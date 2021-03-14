import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Styles.css";
import Chip from "@material-ui/core/Chip";
import { ListFont } from "./Comps";
import { Bar } from "react-chartjs-2";

const TrendMainPage = () => {
  const [url] = useState("http://222.232.15.205:8082");

  let [searchVal, setSearchVal] = useState("비디오 게임");

  let [selectedCtgr, setSelectedCtgr] = useState("");

  let [ctgrs, setCtgrs] = useState([]);

  let [keywords, setKeywords] = useState([]);

  const [dataBar, setDataBar] = useState({
    labels: ["좋아요", "싫어요"],
    datasets: [
      {
        data: [1000, 2000],
        backgroundColor: ["red", "blue"],
      },
    ],
  });

  const [optionBar, setOptionBar] = useState({
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
  });

  useEffect(() => {
    getDataset();
    getDatasetForKeyword(searchVal);
  }, []);

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

  const searchCtgr = () => {
    if (searchVal === "") {
      getDataset();
      getDatasetForKeyword("비디오 게임");
    } else {
      getDataset();
      getDatasetForKeyword(searchVal);
      if (ctgrs.length === 0) {
        searchVal = "비디오 게임";
        getDataset();
        getDatasetForKeyword("비디오 게임");
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
    <div>
      {/* //배경 및 그라데이션  */}
      <div id="scollWallpaper_">
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
      </svg>
      <div id="scollOURTUBE">
        <div>
          <a href="http://localhost:3012/" className="bannerTrandMain">
            <img className="bannerImage" src="/Ex/ourtubeLogoWhite.PNG"></img>
          </a>
        </div>
      </div>
      <div>
        <svg className="_scrollSearchbox1">
          <rect
            id="_Searchbox1"
            rx="0"
            ry="0"
            x="0"
            y="0"
            width="680"
            height="31.702"
          ></rect>
        </svg>
      </div>
      <div className="_scrollSearchbox">
        <input
          className="searchInput"
          placeholder="검색어를 입력하세요"
          autoComplete="off"
          type="text"
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
          <img className="searchButtonImg" src="/Ex/searchbutton.png"></img>
        </button>
      </div>
          <div className="mainChipBox5">
            <div className="chipBackground5">
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
      <svg className="listtop">
        <rect
          id="listtop"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="923"
          height="41"
        ></rect>
      </svg>
      <svg className="listbox">
        <rect
          id="listbox"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="923"
          height="270"
        ></rect>
      </svg>
      <ListFont keywords={keywords}></ListFont>
      <svg className="rightbox">
        <rect
          id="rightbox"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="339"
          height="311"
        ></rect>
      </svg>
      <span id="ourscore">아울스코어</span>
      <span id="ourscorenumber">98</span>
      <div id="rightboxfontgps">
        <div>
          <span id="rightboxfont">리그 오브 섹스</span>
          <span id="rightboxfont2">1996년</span>
          <span id="rightboxfont3">양승혁 골짜기</span>
        </div>
      </div>
      <svg className="listtop2">
        <rect
          id="listtop2"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="923"
          height="41"
        ></rect>
      </svg>
      <svg className="secondbox">
        <rect
          id="secondbox"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="923"
          height="227"
        ></rect>
      </svg>
      <span id="secondboxfont">키워드 월별 순위변동</span>
      <svg className="exbox">
        <rect
          id="exbox"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="339"
          height="772"
        ></rect>
      </svg>
      <svg className="thirdtop">
        <rect
          id="thirdtop"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="456"
          height="41"
        ></rect>
      </svg>
      <svg className="thirdbox">
        <rect
          id="thirdbox"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="456"
          height="196"
        ></rect>
      </svg>
      <span id="thirdfont1">신규 조회수</span>
      <svg className="thirdtop2">
        <rect
          id="thirdtop2"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="456"
          height="41"
        ></rect>
      </svg>

      <svg className="thirdbox2">
        <rect
          id="thirdbox2"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="456"
          height="196"
        ></rect>
      </svg>
      <span id="thirdfont2">신규 동영상수</span>
      <svg className="fourthtop">
        <rect
          id="fourthtop"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="923"
          height="41"
        ></rect>
      </svg>
      <div className="fourthbox">
          <Bar data={dataBar} options = {optionBar} />
        <rect
          id="fourthbox"
          rx="0"
          ry="0"
          x="0"
          y="0"
          width="923"
          height="196"
        ></rect>
      </div>
      <span id="fourthfont">채널 구독자수</span>      
    </div>
  );
};

export default TrendMainPage;
