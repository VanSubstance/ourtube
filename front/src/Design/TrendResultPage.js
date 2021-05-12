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
        getDataKeyword(props.match.params.keyword);
        getDataKeywordsRelavant(props.match.params.keyword);
        getDatasetForDetails(props.match.params.keyword);
        getDatasetForDetailsDate(props.match.params.keyword);
        console.log(dataKeyword);
        console.log(dataKeywordsRelavant);
    }, []);

    const [url] = useState("http://222.232.15.205:8082");

    let [dataViewCounts, setDataViewCounts] = useState({
        viewCounts: "초기값",
        words: "초기 단어",
        likeCount: "초기값",
        dislikeCount: "초기값",
        commentCount: "초기값",
    });

    let [dataViewCountsDate, setDataViewCountsDate] = useState({
        infoDate: "0",
        resultCount: "0",
        viewCount: "초기값",
        likeCount: "초기 단어",
        dislikeCount: "초기값",
        commentCount: "초기값",
    });

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
                console.log(data);
                setDataKeywordsRelavant(data);
            })
            .catch((e) => {
                console.error(e);
            });
    };

    const getDatasetForDetails = async (selectedkeyword) => {
        await axios
            .get(url + "/deploy/keyword/" + selectedkeyword)
            .then(({ data }) => {
                setDataViewCounts(data);
            })
            .catch((e) => {
                console.error(e);
            });
    };

    const getDatasetForDetailsDate = async (selectedkeyword) => {
        await axios
            .get(url + "/deploy/game/main/" + selectedkeyword)
            .then(({ data }) => {
                setDataViewCountsDate(data);
            })
            .catch((e) => {
                console.error(e);
            });
    };

    const [wordCloudInfo, setWordCloudInfo] = useState({
        data: [
            { text: "Hey", value: 1000 },
            { text: "lol", value: 200 },
            { text: "first impression", value: 800 },
            { text: "very cool", value: 14000 },
            { text: "duck", value: 10 },
            { text: "goeiedag", value: 492 },
            { text: "mirdita", value: 332 },
            { text: "1235", value: 33 },
            { text: "tasg", value: 14 },
            { text: "tbwae", value: 456 },
            { text: "b31b", value: 894 },
            { text: "asdfdg", value: 12132 },
            { text: "wehda", value: 3242 },
            { text: "65d4a8", value: 842 },
            { text: "ubhasdg", value: 32110 },
            { text: "7q48f3a1", value: 1955 },
            { text: "asd", value: 235 },
            { text: "gwe", value: 116 },
            { text: "rqwree", value: 123 },
            { text: "eqwty", value: 323 },
            { text: "gasdf", value: 44 },
            { text: "czxcbb", value: 56 },
        ],
        fontSizeMapper: (word) => Math.log2(word.value) * 5,
    });

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
                            <WordCloud
                                width={675}
                                height={300}
                                data={wordCloudInfo.data}
                                fontSizeMapper={wordCloudInfo.fontSizeMapper}
                                font="impact"
                            ></WordCloud>
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
                        <div className="trp_BoxNameBar">좋아요 싫어요 평균</div>
                        <div className="trp_BarGraphContainer">
                            <ResponsiveBar
                                data={[
                                    {
                                        "country": "AD",
                                        "hot dog": 103,
                                        "hot dogColor": "hsl(121, 70%, 50%)",
                                        "burger": 181,
                                        "burgerColor": "hsl(350, 70%, 50%)",
                                        "sandwich": 194,
                                        "sandwichColor": "hsl(129, 70%, 50%)",
                                        "kebab": 21,
                                        "kebabColor": "hsl(4, 70%, 50%)",
                                    },
                                    {
                                        "country": "AE",
                                        "hot dog": 6,
                                        "hot dogColor": "hsl(325, 70%, 50%)",
                                        "burger": 155,
                                        "burgerColor": "hsl(151, 70%, 50%)",
                                        "sandwich": 192,
                                        "sandwichColor": "hsl(56, 70%, 50%)",
                                        "kebab": 138,
                                        "kebabColor": "hsl(254, 70%, 50%)",
                                    },
                                    {
                                        "country": "AF",
                                        "hot dog": 154,
                                        "hot dogColor": "hsl(297, 70%, 50%)",
                                        "burger": 55,
                                        "burgerColor": "hsl(25, 70%, 50%)",
                                        "sandwich": 1,
                                        "sandwichColor": "hsl(65, 70%, 50%)",
                                        "kebab": 196,
                                        "kebabColor": "hsl(85, 70%, 50%)",
                                    },
                                    {
                                        "country": "AG",
                                        "hot dog": 1,
                                        "hot dogColor": "hsl(195, 70%, 50%)",
                                        "burger": 177,
                                        "burgerColor": "hsl(350, 70%, 50%)",
                                        "sandwich": 48,
                                        "sandwichColor": "hsl(49, 70%, 50%)",
                                        "kebab": 46,
                                        "kebabColor": "hsl(126, 70%, 50%)",
                                    },
                                ]}
                                keys={['hot dog', 'burger', 'sandwich', 'kebab']}
                                indexBy="country"
                                margin={{ top: 15, right: 20, bottom: 30, left: 45 }}
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
                    <div className="trp_GraphBox_2">
                        <div className="trp_BoxNameBar">활동 비</div>
                        <div className="trp_BarGraphContainer">
                            <ResponsiveBar
                                data={[
                                    {
                                        "country": "AD",
                                        "hot dog": 103,
                                        "hot dogColor": "hsl(121, 70%, 50%)",
                                        "burger": 181,
                                        "burgerColor": "hsl(350, 70%, 50%)",
                                        "sandwich": 194,
                                        "sandwichColor": "hsl(129, 70%, 50%)",
                                        "kebab": 21,
                                        "kebabColor": "hsl(4, 70%, 50%)",
                                    },
                                    {
                                        "country": "AE",
                                        "hot dog": 6,
                                        "hot dogColor": "hsl(325, 70%, 50%)",
                                        "burger": 155,
                                        "burgerColor": "hsl(151, 70%, 50%)",
                                        "sandwich": 192,
                                        "sandwichColor": "hsl(56, 70%, 50%)",
                                        "kebab": 138,
                                        "kebabColor": "hsl(254, 70%, 50%)",
                                    },
                                    {
                                        "country": "AF",
                                        "hot dog": 154,
                                        "hot dogColor": "hsl(297, 70%, 50%)",
                                        "burger": 55,
                                        "burgerColor": "hsl(25, 70%, 50%)",
                                        "sandwich": 1,
                                        "sandwichColor": "hsl(65, 70%, 50%)",
                                        "kebab": 196,
                                        "kebabColor": "hsl(85, 70%, 50%)",
                                    },
                                    {
                                        "country": "AG",
                                        "hot dog": 1,
                                        "hot dogColor": "hsl(195, 70%, 50%)",
                                        "burger": 177,
                                        "burgerColor": "hsl(350, 70%, 50%)",
                                        "sandwich": 48,
                                        "sandwichColor": "hsl(49, 70%, 50%)",
                                        "kebab": 46,
                                        "kebabColor": "hsl(126, 70%, 50%)",
                                    },
                                ]}
                                keys={['hot dog', 'burger', 'sandwich', 'kebab']}
                                indexBy="country"
                                margin={{ top: 15, right: 20, bottom: 30, left: 45 }}
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
                    <div className="trp_GraphBox_3">
                        <div className="trp_BoxNameBar">피드백 지수</div>
                        <div className="trp_BarGraphContainer">
                            <ResponsiveBar
                                data={[
                                    {
                                        "country": "AD",
                                        "hot dog": 103,
                                        "hot dogColor": "hsl(121, 70%, 50%)",
                                        "burger": 181,
                                        "burgerColor": "hsl(350, 70%, 50%)",
                                        "sandwich": 194,
                                        "sandwichColor": "hsl(129, 70%, 50%)",
                                        "kebab": 21,
                                        "kebabColor": "hsl(4, 70%, 50%)",
                                    },
                                    {
                                        "country": "AE",
                                        "hot dog": 6,
                                        "hot dogColor": "hsl(325, 70%, 50%)",
                                        "burger": 155,
                                        "burgerColor": "hsl(151, 70%, 50%)",
                                        "sandwich": 192,
                                        "sandwichColor": "hsl(56, 70%, 50%)",
                                        "kebab": 138,
                                        "kebabColor": "hsl(254, 70%, 50%)",
                                    },
                                    {
                                        "country": "AF",
                                        "hot dog": 154,
                                        "hot dogColor": "hsl(297, 70%, 50%)",
                                        "burger": 55,
                                        "burgerColor": "hsl(25, 70%, 50%)",
                                        "sandwich": 1,
                                        "sandwichColor": "hsl(65, 70%, 50%)",
                                        "kebab": 196,
                                        "kebabColor": "hsl(85, 70%, 50%)",
                                    },
                                    {
                                        "country": "AG",
                                        "hot dog": 1,
                                        "hot dogColor": "hsl(195, 70%, 50%)",
                                        "burger": 177,
                                        "burgerColor": "hsl(350, 70%, 50%)",
                                        "sandwich": 48,
                                        "sandwichColor": "hsl(49, 70%, 50%)",
                                        "kebab": 46,
                                        "kebabColor": "hsl(126, 70%, 50%)",
                                    },
                                ]}
                                keys={['hot dog', 'burger', 'sandwich', 'kebab']}
                                indexBy="country"
                                margin={{ top: 15, right: 20, bottom: 30, left: 45 }}
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
                    <div className="trp_GraphBox_4">
                        <div className="trp_BoxNameBar">
                            일일 키워드 조회 수
                        </div>
                        <ResponsiveLine
                            className="tmp_ResponsiveLine"
                            data={[
                                {
                                    "id": "japan",
                                    "color": "hsl(266, 70%, 50%)",
                                    "data": [
                                        {
                                            "x": "day1",
                                            "y": 285
                                        },
                                        {
                                            "x": "day2",
                                            "y": 197
                                        },
                                        {
                                            "x": "day3",
                                            "y": 41
                                        },
                                        {
                                            "x": "day4",
                                            "y": 236
                                        },
                                        {
                                            "x": "day5",
                                            "y": 170
                                        },
                                        {
                                            "x": "day6",
                                            "y": 86
                                        },
                                        {
                                            "x": "day7",
                                            "y": 254
                                        }
                                    ]
                                }
                            ]}
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
                            legends={[]}
                        />
                    </div>
                    <div className="trp_GraphBox_5">
                        <div className="trp_BoxNameBar">일일 키워드 검색량</div>
                        <ResponsiveLine
                            className="tmp_ResponsiveLine"
                            data={[
                                {
                                    "id": "japan",
                                    "color": "hsl(266, 70%, 50%)",
                                    "data": [
                                        {
                                            "x": "day1",
                                            "y": 412
                                        },
                                        {
                                            "x": "day2",
                                            "y": 53
                                        },
                                        {
                                            "x": "day3",
                                            "y": 345
                                        },
                                        {
                                            "x": "day4",
                                            "y": 168
                                        },
                                        {
                                            "x": "day5",
                                            "y": 523
                                        },
                                        {
                                            "x": "day6",
                                            "y": 423
                                        },
                                        {
                                            "x": "day7",
                                            "y": 771
                                        }
                                    ]
                                }
                            ]}
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
                            legends={[]}
                        />
                    </div>
                    <div className="trp_GraphBox_6">
                        <div className="trp_BoxNameBar">주별 순위</div>
                        <ResponsiveLine
                            className="tmp_ResponsiveLine"
                            data={[
                                {
                                    "id": "japan",
                                    "color": "hsl(266, 70%, 50%)",
                                    "data": [
                                        {
                                            "x": "day1",
                                            "y": 9
                                        },
                                        {
                                            "x": "day2",
                                            "y": 5
                                        },
                                        {
                                            "x": "day3",
                                            "y": 4
                                        },
                                        {
                                            "x": "day4",
                                            "y": 12
                                        },
                                        {
                                            "x": "day5",
                                            "y": 15
                                        },
                                        {
                                            "x": "day6",
                                            "y": 14
                                        },
                                        {
                                            "x": "day7",
                                            "y": 8
                                        }
                                    ]
                                }
                            ]}
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
                                data={[
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
