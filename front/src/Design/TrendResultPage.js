import React, { Component, useState, useEffect } from "react";
import "./Styles.css";
import "./Css/TrendResultPage.css";
import axios from "axios";
import { Bar, Line, Radar } from "react-chartjs-2";
import Chart from "chart.js";
import { ResponsiveBar } from "@nivo/bar";
import { ResponsiveLine } from "@nivo/line";
import { ResponsiveRadar } from "@nivo/radar";
import { ProfileChipContainer } from "./Comps";
import WordCloud from "react-d3-cloud";
import { CLChipContainer } from "./Comps";

const TrendResultPage = (props) => {

  // 데이터 호출 판단 변수
  const [complete, setComplete] = useState(false);
  const [url] = useState("http://222.232.15.205:8082");

  // 신규 조회수 데이터
  const [dataForAvgNewView, setDataForAvgNewView] = useState([]);

  // 일별 신규 비디오량 데이터
  const [dataForNumNewVid, setDataForNumNewVid] = useState([]);

  // 랭크 데이터
  const [dataForRank, setDataForRank] = useState([]);

  // 장르 별 평균 채널량 데이터
  const [dataForAvgChannel, setDataForAvgChannel] = useState([]);

  // 장르별 평균 채널 당 비디오량 데이터
  const [dataForNumChannelVidByGenre, setDataForNumChannelVidByGenre] = useState([]);

  // 좋싫비 데이터
  const [dataForAvgRatio, setDataForAvgRatio] = useState([]);

  // 레이더 데이터
  const [dataForRadar, setDataForRadar] = useState([
    {
      score: "트렌디",
      keyword: 37,
    },
    {
      score: "롱런",
      keyword: 79,
    },
    {
      score: "참여도",
      keyword: 57,
    },
    {
      score: "충성도",
      keyword: 74,
    },
    {
      score: "챌린지",
      keyword: 96,
    },
  ]);

  // 우측 상단 데이터
  const [dataKeyword, setDataKeyword] = useState({
    title: "",
    thumbnail: "/Ex/happy.jpg",
    genre1: "",
    genre2: "",
    genre3: "",
  });

  // 우측 하단 데이터
  const [dataKeywordsRelavant, setDataKeywordsRelavant] = useState([]);

  useEffect(() => {
    console.log(props.match.params.keyword);
    getDataKeyword(props.match.params.keyword);
    getDataKeywordsRelavant(props.match.params.keyword);
    getDataForLines(props.match.params.keyword);
  }, []);

  useEffect(() => {
    console.log("1: ", dataForAvgRatio);
    console.log("2: ", dataForAvgChannel);
    console.log("3: ", dataForNumChannelVidByGenre);
  }, [dataForNumChannelVidByGenre]);

  // 우측 상단 데이터 연결
  const getDataKeyword = async (title) => {
    await axios
      .get(url + "/deploy/game/profile/" + title)
      .then(({ data }) => {
        setDataKeyword({
          title: title,
          thumbnail: data.thumbnail,
          genre1: data.genre1,
          genre2: data.genre2,
          genre2: data.genre3,
        });
        getDataForBar(title, data.genre1, data.genre2, data.genre3);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 우측 하단 데이터 연결
  const getDataKeywordsRelavant = async (title) => {
    await axios
      .get(url + "/deploy/game/relavant/" + title)
      .then(({ data }) => {
        setDataKeywordsRelavant(data);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 선 그래프 데이터
  const getDataForLines = async (title) => {
    await axios
      .get(url + "/deploy/game/chart/" + title)
      .then(({ data }) => {
        setDataForRank([{
          id: title,
          color: "red",
          data: data.rank
        }]);
        setDataForAvgNewView([{
          id: title,
          color: "red",
          data: data.avgNewView
        }]);
        setDataForNumNewVid([{
          id: title,
          color: "red",
          data: data.numNewVid
        }]);
      })
      .catch((e) => {
        console.error(e);
      });
  };

  // 막대 그래프 데이터
  const getDataForBar = async (title, topic1, topic2, topic3) => {
    let tempForAvgRatio = [];
    let tempForAvgChannel = [];
    let tempForNumChannelVidByGenre = [];
    await axios.get(url + "/deploy/game/resultBar/" + title)
      .then(({data}) => {
        tempForAvgRatio = tempForAvgRatio.concat({
          country: title,
          "좋아요" : data.likeCount,
          "좋아요Color": "hsl(297, 70%, 50%)",
          "싫어요": data.dislikeCount,
          "싫어요Color": "hsl(297, 70%, 50%)"
        });
        tempForAvgChannel = tempForAvgChannel.concat({
          country: title,
          "count" : data.numChannel,
          "countColor": "hsl(297, 70%, 50%)"
        });
        tempForNumChannelVidByGenre = tempForNumChannelVidByGenre.concat({
          country: title,
          "count" : data.numVideo,
          "countColor": "hsl(297, 70%, 50%)"
        });
      }).catch((e) => {
        console.error(e);
      });
    await axios.get(url + "/deploy/topic/resultBar/" + topic1)
      .then(({data}) => {
        tempForAvgRatio = tempForAvgRatio.concat({
          country: topic1,
          "좋아요" : data.likeCount,
          "좋아요Color": "hsl(297, 70%, 50%)",
          "싫어요": data.dislikeCount,
          "싫어요Color": "hsl(297, 70%, 50%)"
        });
        tempForAvgChannel = tempForAvgChannel.concat({
          country: topic1,
          "count" : data.numChannel,
          "countColor": "hsl(297, 70%, 50%)"
        });
        tempForNumChannelVidByGenre = tempForNumChannelVidByGenre.concat({
          country: topic1,
          "count" : data.numVideo,
          "countColor": "hsl(297, 70%, 50%)"
        });
      }).catch((e) => {
        console.error(e);
      });
    await axios.get(url + "/deploy/topic/resultBar/" + topic2)
      .then(({data}) => {
        tempForAvgRatio = tempForAvgRatio.concat({
          country: topic2,
          "좋아요" : data.likeCount,
          "좋아요Color": "hsl(297, 70%, 50%)",
          "싫어요": data.dislikeCount,
          "싫어요Color": "hsl(297, 70%, 50%)"
        });
        tempForAvgChannel = tempForAvgChannel.concat({
          country: topic2,
          "count" : data.numChannel,
          "countColor": "hsl(297, 70%, 50%)"
        });
        tempForNumChannelVidByGenre = tempForNumChannelVidByGenre.concat({
          country: topic2,
          "count" : data.numVideo,
          "countColor": "hsl(297, 70%, 50%)"
        });
      }).catch((e) => {
        console.error(e);
      });
    await axios.get(url + "/deploy/topic/resultBar/" + topic3)
      .then(({data}) => {
        tempForAvgRatio = tempForAvgRatio.concat({
          country: topic3,
          "좋아요" : data.likeCount,
          "좋아요Color": "hsl(297, 70%, 50%)",
          "싫어요": data.dislikeCount,
          "싫어요Color": "hsl(297, 70%, 50%)"
        });
        tempForAvgChannel = tempForAvgChannel.concat({
          country: topic3,
          "count" : data.numChannel,
          "countColor": "hsl(297, 70%, 50%)"
        });
        tempForNumChannelVidByGenre = tempForNumChannelVidByGenre.concat({
          country: topic3,
          "count" : data.numVideo,
          "countColor": "hsl(297, 70%, 50%)"
        });
      }).catch((e) => {
        console.error(e);
      });
    setDataForAvgRatio(tempForAvgRatio);
    setDataForAvgChannel(tempForAvgChannel);
    setDataForNumChannelVidByGenre(tempForNumChannelVidByGenre);
  }

  return (
    <div className="trp_MainWrapper">
      <div className="trp_BackGroundPanel"></div>
      {/* <div
                className="trp_BackGroundPanelLine">
            </div> */}

      {/* //배경 및 그라데이션  */}

      <div id="trp_ScollWallPaper">
        <img
          className="trp_ScrollWallPaperImg"
          src="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png"
          srcSet="/Ex/andy-holmes-rCbdp8VCYhQ-unspla@2x.png"
        ></img>
      </div>

      {/* 헤더 */}

      <div id="header">
        <div className="trp_BannerBox">
          <a className="trp_BannerA" href={window.location.origin}>
            <img
              className="trp_BannerImage"
              src="/Ex/ourtubeLogoWhite.PNG"
            ></img>
          </a>
        </div>
        <div className="trp_InfoBar">
          <div className="trp_BadgeBar">
            <div className="trp_BadgeChip">이 주의 조회수 1위</div>
            <div className="trp_BadgeChip">이 주의 신규 동영상 1위</div>
            <div className="trp_BadgeChip">이 주의 최고 RPG 게임</div>
            <div className="trp_BadgeChip">양승혁의 골짜기</div>
            <div className="trp_BadgeChip">양승혁의 골짜기</div>
          </div>
          <div className="trp_CtgrChip">{dataKeyword.genre1}</div>
          <div className="trp_CtgrChip">{dataKeyword.genre2}</div>
          <div className="trp_CtgrChip">{dataKeyword.genre3}</div>
        </div>
      </div>

      {/* 컨테이너 */}

      <div id="container">
        <div className="trp_LeftBox">
          <div className="trp_TextCloudBox">
            <div className="trp_BoxNameBar">텍스트 클라우드</div>
            <div className="trp_WordCloudContainer">
            </div>
          </div>
          <div className="trp_CommentListBox">
            <div className="trp_BoxNameBarNoPad">
              <div className="trp_CLChip_L">순위</div>
              <div className="trp_CLChip_M">내용</div>
              <div className="trp_CLChip_R">빈도</div>
            </div>
            <div className="trp_CommentList">
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
              <CLChipContainer></CLChipContainer>
            </div>
          </div>
          <div className="trp_GraphBox_1">
            <div className="trp_BoxNameBar">연관 게임 평균 좋싫비</div>
            <div className="trp_BarGraphContainer">
              <ResponsiveBar
                data={dataForAvgRatio}
                keys={["좋아요", "싫어요"]}
                indexBy="country"
                margin={{ top: 15, right: 20, bottom: 30, left: 45 }}
                padding={0.4}
                valueScale={{ type: "linear" }}
                indexScale={{ type: "band", round: true }}
                colors={{ scheme: "nivo" }}
                fill={[
                  {
                    match: {
                      id: "day5",
                    },
                    id: "dots",
                  },
                  {
                    match: {
                      id: "day3",
                    },
                    id: "lines",
                  },
                ]}
                theme={{
                  textColor: "white",
                  axis: {
                    tickColor: "white",
                    ticks: {
                      line: {
                        stroke: "white",
                      },
                      text: {
                        fill: "white",
                      },
                    },
                    legend: {
                      text: {
                        fill: "white",
                      },
                    },
                  },
                  grid: {
                    line: {
                      stroke: "white",
                    },
                  },
                }}
                borderColor={{ from: "color", modifiers: [["brighter", "0"]] }}
                axisTop={null}
                axisRight={null}
                axisBottom={{
                  tickSize: 5,
                  tickPadding: 5,
                  tickRotation: 0,
                  legend: null,
                  legendPosition: "middle",
                  legendOffset: 32,
                }}
                axisLeft={{
                  tickSize: 5,
                  tickPadding: 5,
                  tickRotation: 0,
                  legend: null,
                  legendPosition: "middle",
                  legendOffset: -40,
                }}
                labelTextColor={{
                  from: "color",
                  modifiers: [["brighter", "3"]],
                }}
                labelSkipHeight={12}
                legends={[]}
                animate={true}
                motionStiffness={90}
                motionDamping={15}
              />
            </div>
          </div>
          <div className="trp_GraphBox_2">
            <div className="trp_BoxNameBar">연관 게임 평균 count</div>
            <div className="trp_BarGraphContainer">
              <ResponsiveBar
                data={dataForNumChannelVidByGenre}
                keys={["count"]}
                indexBy="country"
                margin={{ top: 15, right: 20, bottom: 30, left: 45 }}
                padding={0.4}
                valueScale={{ type: "linear" }}
                indexScale={{ type: "band", round: true }}
                colors={{ scheme: "nivo" }}
                fill={[
                  {
                    match: {
                      id: "day5",
                    },
                    id: "dots",
                  },
                  {
                    match: {
                      id: "day3",
                    },
                    id: "lines",
                  },
                ]}
                theme={{
                  textColor: "white",
                  axis: {
                    tickColor: "white",
                    ticks: {
                      line: {
                        stroke: "white",
                      },
                      text: {
                        fill: "white",
                      },
                    },
                    legend: {
                      text: {
                        fill: "white",
                      },
                    },
                  },
                  grid: {
                    line: {
                      stroke: "white",
                    },
                  },
                }}
                borderColor={{ from: "color", modifiers: [["brighter", "0"]] }}
                axisTop={null}
                axisRight={null}
                axisBottom={{
                  tickSize: 5,
                  tickPadding: 5,
                  tickRotation: 0,
                  legend: null,
                  legendPosition: "middle",
                  legendOffset: 32,
                }}
                axisLeft={{
                  tickSize: 5,
                  tickPadding: 5,
                  tickRotation: 0,
                  legend: null,
                  legendPosition: "middle",
                  legendOffset: -40,
                }}
                labelTextColor={{
                  from: "color",
                  modifiers: [["brighter", "3"]],
                }}
                labelSkipHeight={12}
                legends={[]}
                animate={true}
                motionStiffness={90}
                motionDamping={15}
              />
            </div>
          </div>
          <div className="trp_GraphBox_3">
            <div className="trp_BoxNameBar">채널당 평균 동영상 수</div>
            <div className="trp_BarGraphContainer">
              <ResponsiveBar
                data={dataForAvgChannel}
                keys={["count"]}
                indexBy="country"
                margin={{ top: 15, right: 20, bottom: 30, left: 45 }}
                padding={0.4}
                valueScale={{ type: "linear" }}
                indexScale={{ type: "band", round: true }}
                colors={{ scheme: "nivo" }}
                fill={[
                  {
                    match: {
                      id: "day5",
                    },
                    id: "dots",
                  },
                  {
                    match: {
                      id: "day3",
                    },
                    id: "lines",
                  },
                ]}
                theme={{
                  textColor: "white",
                  axis: {
                    tickColor: "white",
                    ticks: {
                      line: {
                        stroke: "white",
                      },
                      text: {
                        fill: "white",
                      },
                    },
                    legend: {
                      text: {
                        fill: "white",
                      },
                    },
                  },
                  grid: {
                    line: {
                      stroke: "white",
                    },
                  },
                }}
                borderColor={{ from: "color", modifiers: [["brighter", "0"]] }}
                axisTop={null}
                axisRight={null}
                axisBottom={{
                  tickSize: 5,
                  tickPadding: 5,
                  tickRotation: 0,
                  legend: null,
                  legendPosition: "middle",
                  legendOffset: 32,
                }}
                axisLeft={{
                  tickSize: 5,
                  tickPadding: 5,
                  tickRotation: 0,
                  legend: null,
                  legendPosition: "middle",
                  legendOffset: -40,
                }}
                labelTextColor={{
                  from: "color",
                  modifiers: [["brighter", "3"]],
                }}
                labelSkipHeight={12}
                legends={[]}
                animate={true}
                motionStiffness={90}
                motionDamping={15}
              />
            </div>
          </div>
          <div className="trp_GraphBox_4">
            <div className="trp_BoxNameBar">일일 키워드 조회 수</div>
            <ResponsiveLine
              className="tmp_ResponsiveLine"
              data={dataForAvgNewView}
              margin={{ top: 10, right: 25, bottom: 70, left: 50 }}
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
                      stroke: "white",
                    },
                    text: {
                      fill: "white",
                    },
                  },
                  legend: {
                    text: {
                      fontSize: 10,
                      fill: "white",
                    },
                  },
                },
                grid: {
                  line: {
                    stroke: "white",
                  },
                },
              }}
              pointSize={4}
              pointColor={{ theme: "background" }}
              pointBorderWidth={2}
              pointBorderColor={{ from: "serieColor" }}
              pointLabelYOffset={-12}
              useMesh={true}
              legends={[]}
            />
          </div>
          <div className="trp_GraphBox_5">
            <div className="trp_BoxNameBar">일일 키워드 검색량</div>
            <ResponsiveLine
              className="tmp_ResponsiveLine"
              data={dataForNumNewVid}
              margin={{ top: 10, right: 25, bottom: 70, left: 50 }}
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
                      stroke: "white",
                    },
                    text: {
                      fill: "white",
                    },
                  },
                  legend: {
                    text: {
                      fontSize: 10,
                      fill: "white",
                    },
                  },
                },
                grid: {
                  line: {
                    stroke: "white",
                  },
                },
              }}
              pointSize={4}
              pointColor={{ theme: "background" }}
              pointBorderWidth={2}
              pointBorderColor={{ from: "serieColor" }}
              pointLabelYOffset={-12}
              useMesh={true}
              legends={[]}
            />
          </div>
          <div className="trp_GraphBox_6">
            <div className="trp_BoxNameBar">주별 순위</div>
            <ResponsiveLine
              className="tmp_ResponsiveLine"
              data={dataForRank}
              margin={{ top: 10, right: 25, bottom: 75, left: 35 }}
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
                      stroke: "white",
                    },
                    text: {
                      fill: "white",
                    },
                  },
                  legend: {
                    text: {
                      fontSize: 10,
                      fill: "white",
                    },
                  },
                },
                grid: {
                  line: {
                    stroke: "white",
                  },
                },
              }}
              pointSize={4}
              pointColor={{ theme: "background" }}
              pointBorderWidth={2}
              pointBorderColor={{ from: "serieColor" }}
              pointLabelYOffset={-12}
              useMesh={true}
              legends={[]}
            />
          </div>
        </div>
        <div className="trp_RightBox">
          <div className="trp_PFBox">
            <div className="trp_PFThumbnailCircle">
              <img
                className="trp_PFThumbnail"
                src={
                  dataKeyword.thumbnail !== null
                    ? dataKeyword.thumbnail
                    : "/Ex/happy.jpg"
                }
              ></img>
            </div>
            <div className="trp_PFKeywordName">{dataKeyword.title}</div>
            <div className="trp_PFKeywordYear">(순위 데이터)위</div>
            <div className="trp_PFKeywordCompony">
              아워스코어 / (아워스코어)
            </div>
          </div>
          <div className="trp_RadarBox">
            <div className="trp_BoxNameBar">레이더 그래프</div>
            <div className="trp_RadarContainer">
              <ResponsiveRadar
                data={dataForRadar}
                theme={{
                  textColor: "white",
                  axis: {
                    tickColor: "white",
                    ticks: {
                      line: {
                        stroke: "white",
                      },
                      text: {
                        fill: "white",
                      },
                    },
                    legend: {
                      text: {
                        fontSize: 10,
                        fill: "white",
                      },
                    },
                  },
                  grid: {
                    line: {
                      stroke: "white",
                    },
                  },
                }}
                keys={["keyword"]}
                indexBy="score"
                maxValue="100"
                margin={{ top: 40, right: 30, bottom: 20, left: 30 }}
                curve="linearClosed"
                borderWidth={2}
                borderColor={{ from: "color" }}
                gridLevels={5}
                gridShape="linear"
                gridLabelOffset={16}
                enableDots={true}
                dotSize={10}
                dotColor="#ffffff"
                dotBorderWidth={2}
                dotBorderColor={{ from: "color" }}
                enableDotLabel={true}
                dotLabel="value"
                dotLabelYOffset={-12}
                colors={{ scheme: "nivo" }}
                fillOpacity={0.8}
                blendMode="multiply"
                animate={true}
                motionConfig="wobbly"
                isInteractive={false}
                legends={[
                  {
                    anchor: "top-left",
                    direction: "column",
                    translateX: -15,
                    translateY: -25,
                    itemWidth: 80,
                    itemHeight: 20,
                    itemTextColor: "#999",
                    symbolSize: 12,
                    symbolShape: "circle",
                    effects: [
                      {
                        on: "hover",
                        style: {
                          itemTextColor: "rgba(255,255,255,0.8)",
                        },
                      },
                    ],
                  },
                ]}
              />
            </div>
          </div>
          <div className="trp_KWDRecommend">
            <div className="trp_BoxNameBar">추천 게임</div>
            <div className="trp_KeywordChipScroll">
              <ProfileChipContainer
                titles={dataKeywordsRelavant}
                static={true}
              ></ProfileChipContainer>
            </div>
          </div>
        </div>
        <div className="trp_footer">
          <div className="trp_TxtTop">OURTUBE Analytics, Inc. © 2021</div>
          <div className="trp_TxtBottom">
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

export default TrendResultPage;
