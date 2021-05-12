import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Styles.css";
import "./Css/TrendMainPage.css";
import Chip from "@material-ui/core/Chip";
import { ListFont } from "./Comps";
import { ProfileChipContainer } from "./Comps";
import { ResponsiveLine } from "@nivo/line";
import { ResponsivePie } from "@nivo/pie";
import { ResponsiveBar } from "@nivo/bar";
import moment from "moment";

const TrendMainPage = () => {
  const [url] = useState("http://222.232.15.205:8082");

  // 검색어
  let [searchVal] = useState("FPS");

  // 장르 리스트
  let [ctgrs, setCtgrs] = useState([]);
  // 선택된 장르
  let [ctgrSelected, setCtgrSelected] = useState("");
  // 선택된 장르 통계치
  const [ctgrData, setCtgrData] = useState({
    ourScore: 0.0,
    resultCount: 0,
    viewCount: 0,
    likeCount: 0,
    dislikeCount: 0
  });
  // 선택된 장르 관련 값 리스트
  const [ctgrsRelevant, setCtgrsRelevant] = useState([]);

  // 현재 장르에 해당되는 불러와진 게임 리스트 : 최대 10개
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
  // AccuComment 데이터
  const [dataForAvgAccuComment, setDataForAvgAccuComment] = useState([]);
  // NumAccuView 데이터
  const [dataForAvgAccuView, setDataForAvgAccuView] = useState([]);
  const datesForBar = [
    moment().subtract(6, "days").format("MM/DD"),
    moment().subtract(5, "days").format("MM/DD"),
    moment().subtract(4, "days").format("MM/DD"),
    moment().subtract(3, "days").format("MM/DD"),
    moment().subtract(2, "days").format("MM/DD"),
    moment().subtract(1, "days").format("MM/DD"),
    moment().format("MM/DD")
  ];

  // 새로고침 시에 일회성으로 작동하는 함수 -> useEffect(리엑트 훅) 검색해서 숙지
  useEffect(() => {
    getDataset();
    getDatasetForKeyword(searchVal);
  }, []);

  useEffect(() => {
  }, [titlesSelected]);

  // method: 0 -> 추가, 1 -> 삭제
  const selectGame = (title, method) => {
    if (!titlesSelected.includes(title)) {
      if (method == 0) {
        setTitlesSelected(titlesSelected.concat(title));
        titleSelected = {
          title: title,
          add: true,
        };
      }
    } else {
      if (method == 1) {
        setTitlesSelected(titlesSelected.filter((k) => k !== title));
        titleSelected = {
          title: title,
          add: false,
        };
      }
    }
    titleSelected.add === true
      ? addDataByGame(titleSelected.title)
      : deleteDataByGame(titleSelected.title);
  };

  // 게임 선택 = 해당 게임 데이터 추가
  // 후측 하단 컴포넌트에 게임 객체 생성 -> ProfileChipContainer 안에 ProfileChip 생성
  const addDataByGame = (title) => {
    getDatasetForChart(title);
  };

  // 게임 선택 해제 = 해당 게임 데이터 삭제
  // 후측 하단 컴포넌트에 게임 객체 삭제 -> ProfileChipContainer 안에 해당 ProfileChip 삭제
  const deleteDataByGame = (title) => {
    setDataForAvgNewView(dataForAvgNewView.filter((dataForLine) => dataForLine.id !== title));
    setDataForNumNewVid(dataForNumNewVid.filter((dataForLine) => dataForLine.id !== title));
    setDataForRank(dataForRank.filter((dataForLine) => dataForLine.id !== title));
    setDataForAvgAccuComment(dataForAvgAccuComment.filter((dataForPie) => dataForPie.id !== title));
    setDataForAvgAccuView(dataForAvgAccuView.filter((dataForBar) => dataForBar.country != title));
    setTitlesSelected(titlesSelected.filter((data) => data !== title));
  };

  // 장르 변경 시 데이터 초기화
  const clearTitlesSelected = () => {
    setDataForAvgNewView([]);
    setDataForNumNewVid([]);
    setDataForRank([]);
    setTitlesSelected([]);
    setDataForAvgAccuComment([]);
    setDataForAvgAccuView([]);
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
        }));
        setDataForAvgAccuComment(dataForAvgAccuComment.concat({
          "id": title,
          "label": title,
          "value": dataForChart.avgAccuComment,
          "color": "hsl(284, 70%, 50%)"
        }));
        let temp = { "country": title };
        temp[datesForBar[0]] = dataForChart.avgNewViewForBar[0];
        temp[datesForBar[0] + "Color"] = "white";
        temp[datesForBar[1]] = dataForChart.avgNewViewForBar[1];
        temp[datesForBar[1] + "Color"] = "white";
        temp[datesForBar[2]] = dataForChart.avgNewViewForBar[2];
        temp[datesForBar[2] + "Color"] = "white";
        temp[datesForBar[3]] = dataForChart.avgNewViewForBar[3];
        temp[datesForBar[3] + "Color"] = "white";
        temp[datesForBar[4]] = dataForChart.avgNewViewForBar[4];
        temp[datesForBar[4] + "Color"] = "white";
        temp[datesForBar[5]] = dataForChart.avgNewViewForBar[5];
        temp[datesForBar[5] + "Color"] = "white";
        temp[datesForBar[6]] = dataForChart.avgNewViewForBar[6];
        temp[datesForBar[6] + "Color"] = "white";
        setDataForAvgAccuView(dataForAvgAccuView.concat(temp));
      });
  };

  // 장르 리스트 가져오기: 최대 7개
  const getDataset = async () => {
    await axios
      .get(url + "/deploy/topic/" + searchVal)
      .then(({ data }) => {
        if (data.length >= 8) {
          setCtgrs(data.slice(0, 7));
          setCtgrSelected(searchVal);
          getDataByTopic(searchVal);
          getTopicRelevant(searchVal);
        } else {
          setCtgrs(data);
          setCtgrSelected(searchVal);
          getDataByTopic(searchVal);
          getTopicRelevant(searchVal);
        }
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 관련 장르 가져오기: 최대 3개
  const getTopicRelevant = async (topic) => {
    await axios
      .get(url + "/deploy/topic/" + topic)
      .then(({ data }) => {
        if (data.length >= 8) {
          setCtgrsRelevant(data.slice(0, 3));
        } else {
          setCtgrsRelevant(data);
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
        getDataByTopic(ctgr);
        getTopicRelevant(ctgr);
        clearTitlesSelected();
        let games = [];
        if (data.length >= 10) {
          games = data.slice(0, 10);
        } else {
          games = data;
        }
        let temp = [];
        games.forEach(async (element) => {
          await axios
            .get(url + "/deploy/game/chart/today/" + element.title)
            .then(({ data }) => {
              temp = temp.concat(data);
              setKeywords(temp);
            })
            .catch((e) => {
              console.error(e);
            })
        });
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 장르 통계 수치 api 호출 함수
  const getDataByTopic = async (topic) => {
    await axios
      .get(url + "/deploy/topic/statistic/" + topic)
      .then(({ data }) => {
        setCtgrData(data);
      })
      .catch((e) => {
        console.error(e);
      });
  }

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
          <a className="tmp_BannerA" href={""}>
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
            <ListFont keywords={keywords} func={selectGame}></ListFont>
          </div>
          <div className="tmp_RankChangeBox">
            <div className="tmp_BoxNameBar">키워드 일별 순위변동</div>
            <ResponsiveLine
              className="tmp_ResponsiveLine"
              data={dataForRank}
              margin={{ top: 5, right: 230, bottom: 70, left: 40 }}
              xScale={{ type: "point" }}
              yScale={{
                type: "linear",
                min: "0",
                max: "auto",
                stacked: false,
                reverse: true,
              }}
              yFormat=" >-.2f"
              curve="linear"
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
                      fontSize: 10,
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
              margin={{ top: 15, right: 25, bottom: 70, left: 45 }}
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
            />
          </div>
          <div className="tmp_NewVideoBox">
            <div className="tmp_BoxNameBar">신규 동영상 수</div>
            <ResponsiveLine
              data={dataForNumNewVid}
              margin={{ top: 15, right: 25, bottom: 70, left: 45 }}
              xScale={{ type: "point" }}
              yScale={{
                type: "linear",
                min: "0",
                max: "auto",
                stacked: false,
                reverse: false,
              }}
              yFormat=" >-.2f"
              curve="linear"
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
            />
          </div>
          <div className="tmp_pieBox">
            <div className="tmp_BoxNameBar">평균 댓글 수</div>
            <ResponsivePie
              data={dataForAvgAccuComment}
              margin={{ top: 35, right: 80, bottom: 75, left: 80 }}
              sortByValue={true}
              innerRadius={0.6}
              cornerRadius={4}
              isInteractive={true}
              activeOuterRadiusOffset={8}
              colors={{ scheme: 'nivo' }}
              borderWidth={2}
              borderColor={{ theme: 'background' }}
              arcLinkLabelsTextColor="#ffffff"
              arcLinkLabelsDiagonalLength={15}
              arcLinkLabelsStraightLength={18}
              arcLinkLabelsThickness={2}
              arcLinkLabelsColor={{ from: 'color' }}
              arcLabel="value"
              arcLabelsTextColor={{ from: 'color', modifiers: [['brighter', '3']] }}
              legends={[]}
            />
          </div>
          <div className="tmp_barBox">
            <div className="tmp_BoxNameBar">누적 조회수</div>
            <ResponsiveBar
              data={dataForAvgAccuView}
              keys={datesForBar}
              indexBy="country"
              margin={{ top: 15, right: 20, bottom: 70, left: 45 }}
              padding={0.4}
              valueScale={{ type: 'linear' }}
              indexScale={{ type: 'band', round: true }}
              colors={{ scheme: 'nivo' }}
              fill={[
                {
                  match: {
                    id: 'day5'
                  },
                  id: 'dots'
                },
                {
                  match: {
                    id: 'day3'
                  },
                  id: 'lines'
                }
              ]}
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
              borderColor={{ from: 'color', modifiers: [['brighter', '0']] }}
              axisTop={null}
              axisRight={null}
              axisBottom={{
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendPosition: 'middle',
                legendOffset: 32
              }}
              axisLeft={{
                tickSize: 5,
                tickPadding: 5,
                tickRotation: 0,
                legend: null,
                legendPosition: 'middle',
                legendOffset: -40
              }}
              labelTextColor={{ from: 'color', modifiers: [['brighter', '3']] }}
              labelSkipHeight={12}
              legends={[]}
              animate={true}
              motionStiffness={90}
              motionDamping={15}
            />
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
                <div className="tmp_PFRelaCTGR">
                  {
                    ctgrsRelevant !== null && ctgrsRelevant[0] !== null
                      ? (ctgrsRelevant[0])
                      : ("연관카테고리")
                  }
                </div>
                <div className="tmp_PFRelaCTGR">
                  {
                    ctgrsRelevant !== null && ctgrsRelevant[1] !== null
                      ? (ctgrsRelevant[1])
                      : ("연관카테고리")
                  }
                </div>
                <div className="tmp_PFRelaCTGR">
                  {
                    ctgrsRelevant !== null && ctgrsRelevant[2] !== null
                      ? (ctgrsRelevant[2])
                      : ("연관카테고리")
                  }
                </div>
              </div>
            </div>
            <div className="tmp_PFRightBox">
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">장르 순위</div>
                <div
                  className="tmp_PFKeywordInfoBottom">
                  {
                    ctgrData === null
                      ? (0)
                      : (ctgrData.rank)
                  }
                </div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">장르 아울스코어</div>
                <div
                  className="tmp_PFKeywordInfoBottom">
                  {
                    ctgrData === null
                      ? (50.00)
                      : (100 * (ctgrData.ourScore)).toFixed(1)
                  }
                </div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">평균 검색량</div>
                <div
                  className="tmp_PFKeywordInfoBottom">
                  {
                    ctgrData === null
                      ? (50.00)
                      : (ctgrData.resultCount)
                  }
                </div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">평균 조회수</div>
                <div
                  className="tmp_PFKeywordInfoBottom">
                  {
                    ctgrData === null
                      ? (50.00)
                      : (ctgrData.viewCount)
                  }
                </div>
              </div>
              <div
                className="tmp_PFKeywordInfoBox">
                <div
                  className="tmp_PFKeywordInfoTop">평균 좋싫비</div>
                <div
                  className="tmp_PFKeywordInfoBottom">
                  {
                    ctgrData === null
                      ? (50.00)
                      : (ctgrData.likeCount / ctgrData.dislikeCount).toFixed(1)
                  }
                    &nbsp;: 1
                  </div>
              </div>
            </div>
          </div>
          <div className="tmp_KeywordChipBox">
            <button className="tmp_KeywordChipClearAllbutton" onClick={() => { clearTitlesSelected() }}>전부 지우기</button>
            <div
              className="tmp_KeywordChipScroll">
              <ProfileChipContainer titles={titlesSelected} func={selectGame}>
              </ProfileChipContainer>
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
