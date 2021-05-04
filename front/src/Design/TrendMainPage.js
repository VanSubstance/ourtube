import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Styles.css";
import "./Css/TrendMainPage.css";
import Chip from "@material-ui/core/Chip";
import { ListFont } from "./Comps";
import { ResponsiveLine } from "@nivo/line";

const TrendMainPage = () => {
  const [url] = useState("http://222.232.15.205:8082");

  let [searchVal] = useState("FPS");

  let [ctgrs, setCtgrs] = useState([]);
  let [ctgrSelected, setCtgrSelected] = useState("");

  let [keywords, setKeywords] = useState([]);
  // 현재 선택된 게임 리스트
  const [titlesSelected, setTitlesSelected] = useState([]);
  // 현재 선택한 /선택 해제한 게임
  let [titleSelected] = useState({
    title: "",
    add: true,
  });

  // AvgNewView 데이터
  const [dataForAvgNewView, setDataForAvgNewView] = useState([]);
  // numNewVid 데이터
  const [dataForNumNewVid, setDataForNumNewVid] = useState([]);
  // rank 데이터
  const [dataForRank, setDataForRank] = useState([]);

  // 새로고침 시에 일회성으로 작동하는 함수 -> useEffect(리엑트 훅) 검색해서 숙지
  useEffect(() => {
    getDataset();
    getDatasetForKeyword(searchVal);
  }, []);

  // method: 0 -> 추가, 1 -> 삭제
  const selectGame = (keyword, method) => {
    if (!titlesSelected.includes(keyword)) {
      if (method == 0) {
        setTitlesSelected(titlesSelected.concat(keyword));
        titleSelected = {
          title: keyword,
          add: true,
        };
      } else {
        setTitlesSelected(titlesSelected.filter((k) => k !== keyword));
        titleSelected = {
          title: keyword,
          add: false,
        };
      }
      titleSelected.add === true
        ? addDataByGame(titleSelected.title)
        : deleteDataByGame(titleSelected.title);
    }
  };

  // 게임 선택 = 해당 게임 데이터 추가
  const addDataByGame = (title) => {
    getDatasetForChart(title);
  };

  // 게임 선택 해제 = 해당 게임 데이터 삭제
  const deleteDataByGame = (title) => {
    setDataForAvgNewView(dataForAvgNewView.filter((dataForLine) => dataForLine.id !== title));
    setDataForNumNewVid(dataForNumNewVid.filter((dataForLine) => dataForLine.id !== title));
    setDataForRank(dataForRank.filter((dataForLine) => dataForLine.id !== title));
  };

  // 장르 변경 시 데이터 초기화
  const clearTitlesSelected = () => {
    setDataForAvgNewView([]);
    setDataForNumNewVid([]);
    setDataForRank([]);
    setTitlesSelected([]);    
    titleSelected = {
      title: "",
      add: true,
    };
  };

  // 해당 게임 데이터 가져오기
  const getDatasetForChart = (title) => {
    fetch(url + "/deploy/game/chart/" + title)
      .then((response) => response.json())
      .then((dataForChart) => {
        setDataForAvgNewView(dataForAvgNewView.concat({
          id: title,
          color: "white",
          data: dataForChart.avgNewView
        }));
        setDataForNumNewVid(dataForNumNewVid.concat({
          id: title,
          color: "white",
          data: dataForChart.numNewVid
        }));
        setDataForRank(dataForRank.concat({
          id: title,
          color: "white",
          data: dataForChart.rank
        }))
      });
  };

  // 장르 별 리스트 가져오기: 최대 7개
  const getDataset = async () => {
    await axios
      .get(url + "/deploy/topic/" + searchVal)
      .then(({ data }) => {
        if (data.length >= 8) {
          setCtgrs(data.slice(0, 7));
          setCtgrSelected(searchVal);
        } else {
          setCtgrs(data);
          setCtgrSelected(searchVal);
        }
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 장르 별 검색 api 함수: Default 값: 현재 FPS -> 장르별 순위 메김으로 1위 장르로 고정
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

  // 장르 별 게임 탑 10 리스트 api 호출 함수
  const getDatasetForKeyword = async (ctgr) => {
    await axios
      .get(url + "/deploy/game/list/" + ctgr)
      .then(({ data }) => {
        setCtgrSelected(ctgr);
        clearTitlesSelected();
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

  // 검색 엔터키와 연결 함수
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
            <ListFont keywords={keywords} func = {selectGame}></ListFont>
          </div>
          <div className="tmp_RankChangeBox">
            <div className="tmp_BoxNameBar">키워드 월별 순위변동</div>
            <ResponsiveLine
              data={dataForRank}
              margin={{ top: 20, right: 110, bottom: 100, left: 60 }}
              xScale={{ type: "point" }}
              yScale={{
                type: "linear",
                min: "0",
                max: "auto",
                stacked: false,
                reverse: true,
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
                legend: null,
                legendOffset: 36,
                legendPosition: "middle",
              }}
              axisLeft={{
                orient: "left",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendOffset: -40,
                legendPosition: "middle",
              }}
              theme={{
                textColor: "white",
                axis: {
                  tickColor: "white",
                  ticks: {
                    line: {
                      stroke: "white"
                    },
                    text: {
                      fill: "white"
                    }
                  },
                  legend: {
                    text: {
                      fill: "white"
                    }
                  }
                },
                grid: {
                  line: {
                    stroke: "white"
                  }
                }
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
          <div className="tmp_NewViewBox">
            <div className="tmp_BoxNameBar">신규 조회수</div>
            <ResponsiveLine
              data={dataForAvgNewView}
              margin={{ top: 40, right: 25, bottom: 75, left: 45 }}
              xScale={{ type: "point" }}
              yScale={{
                type: "linear",
                min: "0",
                max: "auto",
                stacked: false,
                reverse: false,
              }}
              yFormat=" >-.2f"
              curve="monotoneX"
              textColor="#ffffff"
              axisTop={null}
              axisRight={null}
              axisBottom={{
                orient: "bottom",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendOffset: 36,
                legendPosition: "middle",
              }}
              axisLeft={{
                orient: "left",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendOffset: -50,
                legendPosition: "middle",
              }}
              theme={{
                textColor: "white",
                axis: {
                  tickColor: "white",
                  ticks: {
                    line: {
                      stroke: "white"
                    },
                    text: {
                      fill: "white"
                    }
                  },
                  legend: {
                    text: {
                      fill: "white"
                    }
                  }
                },
                grid: {
                  line: {
                    stroke: "white"
                  }
                }
              }}
              pointSize={4}
              pointColor={{ theme: "background" }}
              pointBorderWidth={2}
              pointBorderColor={{ from: "serieColor" }}
              pointLabelYOffset={-12}
              useMesh={true}
              legends={[
                {
                  dataFrom: "keys",
                  anchor: "top-left",
                  direction: "row",
                  justify: false,
                  translateX: -25,
                  translateY: -35,
                  itemsSpacing: 30,
                  itemDirection: "left-to-right",
                  itemWidth: 60,
                  itemHeight: 20,
                  itemOpacity: 0.8,
                  itemTextColor: "#ffffff",
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
            <ResponsiveLine
              data={dataForAvgNewView}
              margin={{ top: 40, right: 25, bottom: 75, left: 45 }}
              xScale={{ type: "point" }}
              yScale={{
                type: "linear",
                min: "0",
                max: "auto",
                stacked: false,
                reverse: false,
              }}
              yFormat=" >-.2f"
              curve="monotoneX"
              textColor="#ffffff"
              axisTop={null}
              axisRight={null}
              axisBottom={{
                orient: "bottom",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendOffset: 36,
                legendPosition: "middle",
              }}
              axisLeft={{
                orient: "left",
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendOffset: -50,
                legendPosition: "middle",
              }}
              theme={{
                textColor: "white",
                axis: {
                  tickColor: "white",
                  ticks: {
                    line: {
                      stroke: "white"
                    },
                    text: {
                      fill: "white"
                    }
                  },
                  legend: {
                    text: {
                      fill: "white"
                    }
                  }
                },
                grid: {
                  line: {
                    stroke: "white"
                  }
                }
              }}
              pointSize={4}
              pointColor={{ theme: "background" }}
              pointBorderWidth={2}
              pointBorderColor={{ from: "serieColor" }}
              pointLabelYOffset={-12}
              useMesh={true}
              legends={[
                {
                  dataFrom: "keys",
                  anchor: "top-left",
                  direction: "row",
                  justify: false,
                  translateX: -25,
                  translateY: -35,
                  itemsSpacing: 30,
                  itemDirection: "left-to-right",
                  itemWidth: 60,
                  itemHeight: 20,
                  itemOpacity: 0.8,
                  itemTextColor: "#ffffff",
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
          <div className="tmp_RankChangeBox">
            <div className="tmp_BoxNameBar">채널 구독자 수 (미정)</div>
          </div>
        </div>
        <div className="tmp_RightBox">
          <div className="tmp_PFBox">
            <div className="tmp_PFLeftBox">
              <div className="tmp_PFThumbnailCircle">
                <img className="tmp_PFThumbnail" src=
                  {
                    keywords === [] || keywords[0] === undefined
                      ? ("/Ex/happy.jpg")
                      : (keywords[0].thumbnail)
                  }
                ></img>
              </div>
              <div
                className="tmp_PFRelaCTGRBox">
                <div
                  className="tmp_PFRelaCTGRLineBox">
                  <svg width="35" height="220" >
                    <g>
                      <line stroke="#ffffff" stroke-width="2" id="svg_8" y2="32" x2="27" y1="32" x1="9" />
                      <line stroke="#ffffff" stroke-width="2" id="svg_9" y2="88" x2="27" y1="88" x1="9" />
                      <line stroke="#ffffff" stroke-width="2" id="svg_10" y2="141" x2="27" y1="141" x1="9" />
                      <line stroke="#ffffff" stroke-width="2" id="svg_5" y2="195" x2="27" y1="195" x1="9" />
                      <line stroke="#ffffff" stroke-width="2" id="svg_6" y2="195" x2="10" y1="32" x1="10" />
                    </g>
                  </svg>
                </div>
                <div className="tmp_PFMainCTGR">
                  {
                    ctgrSelected === ""
                      ? ("장르 이름")
                      : (ctgrSelected)
                  }
                </div>
                <div className="tmp_PFRelaCTGR">연관카테고리</div>
                <div className="tmp_PFRelaCTGR">연관카테고리</div>
                <div className="tmp_PFRelaCTGR">연관카테고리</div>
              </div>
            </div>
            <div className="tmp_PFRightBox">
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">장르 순위</div>
                <div
                  className="tmp_PFKeywordInfoBottom">1</div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">장르 아울스코어</div>
                <div
                  className="tmp_PFKeywordInfoBottom">65.4</div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">평균 검색량</div>
                <div
                  className="tmp_PFKeywordInfoBottom">23.2K</div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">평균 조회수</div>
                <div
                  className="tmp_PFKeywordInfoBottom">85.4K</div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">좋싫비</div>
                <div
                  className="tmp_PFKeywordInfoBottom">95.4%</div>
              </div>
            </div>
          </div>
          <div className="tmp_KeywordChipBox">
            <button className="tmp_KeywordChipClearAllbutton">전부 지우기</button>
            <div
              className="tmp_KeywordChipScroll">
            </div>
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
