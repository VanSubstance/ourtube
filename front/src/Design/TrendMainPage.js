import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Styles.css";
import "./Css/TrendMainPage.css";
import Chip from "@material-ui/core/Chip";
import { ListFont } from "./Comps";
import moment from "moment";
import { ResponsiveLine } from "@nivo/line";

const TrendMainPage = () => {
  const [url] = useState("http://222.232.15.205:8082");

  let [searchVal] = useState("FPS");

  let [ctgrs, setCtgrs] = useState([]);

  let [keywords, setKeywords] = useState([]);
  let [titlesSelected] = useState([]);
  let [titleSelected] = useState({
    title: "",
    add: true,
  });

  const [dataExample] = useState([
    {
      id: "japan",
      color: "hsl(193, 70%, 50%)",
      data: [
        {
          x: "plane",
          y: 126,
        },
        {
          x: "helicopter",
          y: 99,
        },
        {
          x: "boat",
          y: 205,
        },
        {
          x: "train",
          y: 153,
        },
        {
          x: "subway",
          y: 66,
        },
        {
          x: "bus",
          y: 76,
        },
        {
          x: "car",
          y: 185,
        },
        {
          x: "moto",
          y: 291,
        },
        {
          x: "bicycle",
          y: 10,
        },
        {
          x: "horse",
          y: 280,
        },
        {
          x: "skateboard",
          y: 268,
        },
        {
          x: "others",
          y: 76,
        },
      ],
    },
    {
      id: "france",
      color: "hsl(172, 70%, 50%)",
      data: [
        {
          x: "plane",
          y: 73,
        },
        {
          x: "helicopter",
          y: 201,
        },
        {
          x: "boat",
          y: 287,
        },
        {
          x: "train",
          y: 298,
        },
        {
          x: "subway",
          y: 286,
        },
        {
          x: "bus",
          y: 141,
        },
        {
          x: "car",
          y: 155,
        },
        {
          x: "moto",
          y: 186,
        },
        {
          x: "bicycle",
          y: 291,
        },
        {
          x: "horse",
          y: 2,
        },
        {
          x: "skateboard",
          y: 57,
        },
        {
          x: "others",
          y: 13,
        },
      ],
    },
    {
      id: "us",
      color: "hsl(79, 70%, 50%)",
      data: [
        {
          x: "plane",
          y: 278,
        },
        {
          x: "helicopter",
          y: 36,
        },
        {
          x: "boat",
          y: 140,
        },
        {
          x: "train",
          y: 158,
        },
        {
          x: "subway",
          y: 223,
        },
        {
          x: "bus",
          y: 247,
        },
        {
          x: "car",
          y: 241,
        },
        {
          x: "moto",
          y: 27,
        },
        {
          x: "bicycle",
          y: 112,
        },
        {
          x: "horse",
          y: 68,
        },
        {
          x: "skateboard",
          y: 223,
        },
        {
          x: "others",
          y: 102,
        },
      ],
    },
    {
      id: "germany",
      color: "hsl(79, 70%, 50%)",
      data: [
        {
          x: "plane",
          y: 145,
        },
        {
          x: "helicopter",
          y: 41,
        },
        {
          x: "boat",
          y: 131,
        },
        {
          x: "train",
          y: 149,
        },
        {
          x: "subway",
          y: 13,
        },
        {
          x: "bus",
          y: 243,
        },
        {
          x: "car",
          y: 85,
        },
        {
          x: "moto",
          y: 178,
        },
        {
          x: "bicycle",
          y: 281,
        },
        {
          x: "horse",
          y: 259,
        },
        {
          x: "skateboard",
          y: 28,
        },
        {
          x: "others",
          y: 277,
        },
      ],
    },
    {
      id: "norway",
      color: "hsl(27, 70%, 50%)",
      data: [
        {
          x: "plane",
          y: 52,
        },
        {
          x: "helicopter",
          y: 159,
        },
        {
          x: "boat",
          y: 96,
        },
        {
          x: "train",
          y: 296,
        },
        {
          x: "subway",
          y: 184,
        },
        {
          x: "bus",
          y: 218,
        },
        {
          x: "car",
          y: 146,
        },
        {
          x: "moto",
          y: 56,
        },
        {
          x: "bicycle",
          y: 185,
        },
        {
          x: "horse",
          y: 166,
        },
        {
          x: "skateboard",
          y: 85,
        },
        {
          x: "others",
          y: 174,
        },
      ],
    },
  ]);

  useEffect(() => {
    getDataset();
    getDatasetForKeyword(searchVal);
  }, []);

  useEffect(() => {
    console.log(titleSelected);
    titleSelected.add++ + true
      ? addDataByGame(titleSelected.title)
      : deleteDataByGame(titleSelected.title);
  }, [titleSelected]);

  const checkKeywords = (keyword, method) => {
    if (method == 0) {
      titlesSelected = titlesSelected.concat(keyword);
      titleSelected = {
        title: keyword,
        add: true,
      };
    } else {
      titlesSelected = titlesSelected.filter((k) => k != keyword);
      titleSelected = {
        title: keyword,
        add: false,
      };
    }
    console.log(titlesSelected);
    console.log(titleSelected);
  };

  // 게임 체크 = 해당 게임 데이터 추가
  const addDataByGame = (title) => {
    getDatasetForChart(title);
  };

  // 게임 체크 해제 = 해당 게임 데이터 삭제
  const deleteDataByGame = (title) => {};

  // 해당 게임 데이터 가져오기
  const getDatasetForChart = (title) => {
    fetch(url + "/deploy/game/chart/" + title)
      .then((response) => response.json())
      .then((dataForChart) => {

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
    <div className="tmp_MainWrapper">
      <div className="tmp_BackGroundPanel"></div>

      {/* //배경 및 그라데이션  */}

      <div id="tmp_ScollWallPaper">
        <img
          className="tmp_ScrollWallPaperImg"
          src="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png"
          srcSet="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png"
        ></img>
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
        <div className="tmp_BannerBox">
          <a className="tmp_BannerA" href="http://localhost:3012/">
            <img
              className="tmp_BannerImage"
              src="/Ex/ourtubeLogoWhite.PNG"
            ></img>
          </a>
        </div>
      </div>

      {/* 컨테이너 */}

      <div id="container">
        <div className="tmp_LeftBox">
          <div className="tmp_SearchBox">
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
          <div className="tmp_KeywordRankBox">
            <div className="tmp_BoxNameBar"></div>
            <ListFont keywords={keywords} func={checkKeywords}></ListFont>
          </div>
          <div className="tmp_RankChangeBox">
            <div className="tmp_BoxNameBar">키워드 월별 순위변동</div>
          </div>
          <div className="tmp_NewViewBox">
            <div className="tmp_BoxNameBar">신규 조회수</div>
            <ResponsiveLine
              data={dataExample}
              margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
              xScale={{ type: "point" }}
              yScale={{
                type: "linear",
                min: "auto",
                max: "auto",
                stacked: true,
                reverse: false,
              }}
              yFormat=" >-.2f"
              curve="monotoneX"
              axisTop={null}
              axisRight={null}
              axisBottom={{
                orient: "bottom",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: "transportation",
                legendOffset: 36,
                legendPosition: "middle",
              }}
              axisLeft={{
                orient: "left",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: "count",
                legendOffset: -40,
                legendPosition: "middle",
              }}
              pointSize={4}
              pointColor={{ theme: "background" }}
              pointBorderWidth={2}
              pointBorderColor={{ from: "serieColor" }}
              pointLabelYOffset={-12}
              useMesh={true}
              legends={[
                {
                  anchor: "top-right",
                  direction: "column",
                  justify: false,
                  translateX: 100,
                  translateY: 0,
                  itemsSpacing: 0,
                  itemDirection: "left-to-right",
                  itemWidth: 80,
                  itemHeight: 20,
                  itemOpacity: 0.75,
                  symbolSize: 12,
                  symbolShape: "circle",
                  symbolBorderColor: "rgba(0, 0, 0, .5)",
                  effects: [
                    {
                      on: "hover",
                      style: {
                        itemBackground: "rgba(0, 0, 0, .03)",
                        itemOpacity: 1,
                      },
                    },
                  ],
                },
              ]}
            />
          </div>
          <div className="tmp_NewVideoBox">
            <div className="tmp_BoxNameBar">신규 동영상 수</div>
          </div>
          <div className="tmp_RankChangeBox">
            <div className="tmp_BoxNameBar">채널 구독자 수 (미정)</div>
          </div>
        </div>
        <div className="tmp_RightBox">
          <div className="tmp_PFBox">
            <div className="tmp_PFTopBox">
              <div className="tmp_PFThumbnailCircle">
                <img className="tmp_PFThumbnail" src="/Ex/happy.jpg"></img>
              </div>
              <div className="tmp_PFKeywordName">
                키워드 이름
                {/* {props.match.params.keyword} */}
              </div>
              <div className="tmp_PFKeywordYear">테스트 제작연도</div>
              <div className="tmp_PFKeywordCompany">테스트 제작사</div>
            </div>
            <div className="tmp_PFBottomBox">
              <div className="tmp_PFKeywordInfoBox">
                <div className="tmp_PFKeywordInfoTop">조회수</div>
                <div className="tmp_PFKeywordInfoBottom">10000</div>
              </div>
              <div className="tmp_PFKeywordInfoBox">
                <div className="tmp_PFKeywordInfoTop">검색량</div>
                <div className="tmp_PFKeywordInfoBottom">10000</div>
              </div>
              <div className="tmp_PFKeywordInfoBox">
                <div className="tmp_PFKeywordInfoTop">신규 동영상</div>
                <div className="tmp_PFKeywordInfoBottom">10000</div>
              </div>
              <div className="tmp_PFKeywordInfoBox">
                <div className="tmp_PFKeywordInfoTop">댓글 수</div>
                <div className="tmp_PFKeywordInfoBottom">10000</div>
              </div>
              <div className="tmp_PFKeywordInfoBox">
                <div className="tmp_PFKeywordInfoTop">아워 스코어</div>
                <div className="tmp_PFKeywordInfoBottom">10000</div>
              </div>
              <div className="tmp_PFKeywordInfoBox">
                <div className="tmp_PFKeywordInfoTop">순위</div>
                <div className="tmp_PFKeywordInfoBottom">10000</div>
              </div>
            </div>
          </div>
          <div className="tmp_KeywordExplainBox">
            {/* 키워드 설명 및 관련 링크 (위키 등) 추가 */}
            This boy, well known as 'tanoshii boy' is based on meme. did you
            know that?
          </div>
        </div>
        <div className="tmp_footer">
          <div className="tmp_TxtTop">OURTUBE Analytics, Inc. © 2021</div>
          <div className="tmp_TxtBottom">
            Ourtube is hosted by Ourtube Analytics, Inc. Ourtube isn’t endorsed
            by Youtube and doesn’t reflect the views or opinions of youtube or
            anyone officially involved in producing or managing Youtube. youtube
            and Google are trademarks or registered trademarks of Google.Inc.
            Youtube © Google.Inc.
          </div>
        </div>
      </div>
    </div>
  );
};

export default TrendMainPage;
