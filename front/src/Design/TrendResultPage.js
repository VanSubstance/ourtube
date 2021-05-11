import React, { Component, useState, useEffect } from "react";
import "./Styles.css";
import "./Css/TrendResultPage.css";
import axios from "axios";
import { Bar, Line, Radar } from "react-chartjs-2";
import Chart from "chart.js";
import { ResponsiveRadar } from "@nivo/radar";
import { ProfileChipContainer } from "./Comps";

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

    const [barInfo, setBarInfo] = useState({
        data: {
            labels: [dataViewCountsDate.infoDate, "2", "3", "4", "5"],
            datasets: [
                {
                    label: props.match.params.keyword,
                    data: [11000, 12000, 13000, 14000, 15000],
                    backgroundColor: ["red", "blue", "yellow", "black", "purple"],
                },
            ],
        },
        options: {
            layout: {
                padding: {
                    top: 0,
                    left: 10,
                    right: 165,
                    bottom: 15,
                },
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
        },
    });

    const [lineRankInfo, setlineRankInfo] = useState({
        data: {
            labels: ["1", "2", "3", "4", "5"],
            datasets: [
                {
                    label: props.match.params.keyword,
                    data: [11000, 12000, 13000, 14000, 15000],
                    borderColor: "red",
                },
            ],
        },
        options: {
            layout: {
                padding: {
                    top: 10,
                    left: 10,
                    right: 20,
                    bottom: 260,
                },
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
        },
    });

    const [lineViewInfo, setlineViewInfo] = useState({
        data: {
            labels: ["1", "2", "3", "4", "5"],
            datasets: [
                {
                    label: props.match.params.keyword,
                    data: [11000, 12000, 13000, 14000, 15000],
                    borderColor: "blue",
                },
            ],
        },
        options: {
            layout: {
                padding: {
                    top: 10,
                    left: 10,
                    right: 20,
                    bottom: 20,
                },
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
        },
    });

    const [line3Info, setline3Info] = useState({
        data: {
            labels: ["1", "2", "3", "4", "5"],
            datasets: [
                {
                    label: props.match.params.keyword,
                    data: [11000, 12000, 13000, 14000, 15000],
                    borderColor: "white",
                },
            ],
        },
        options: {
            layout: {
                padding: {
                    top: 10,
                    left: 10,
                    right: 20,
                    bottom: 20,
                },
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
        },
    });

    const [radarInfo, setradarInfo] = useState({
        data: {
            labels: ["트렌디", "롱런", "피드백", "로얄티", "챌린저"],
            datasets: [
                {
                    fill: false,
                    label: props.match.params.keyword,
                    data: [45, 60, 79, 83, 97],
                    backgroundColor: "rgba(0, 0, 0, 0)",
                    borderColor: "rgba(0, 0, 0, 1)",
                    borderWidth: 1,
                },
            ],
        },
        options: {
            legend: {
                labels: {
                    fontColor: "rgba(0, 0, 0, 1)",
                },
            },
            reponsive: true,
            maintainAspectRatio: false,
            layout: {
                padding: {
                    top: 5,
                    left: 5,
                    right: 5,
                    bottom: 5,
                },
            },
            tooltips: {
                bodyFontColor: "rgba(255, 255, 255, 1)",
                footerFontColor: "rgba(255, 255, 255, 1)",
                titleFontColor: "rgba(255, 255, 255, 1)",
                displayColor: "rgba(255, 255, 255, 1)",
                fontColor: "rgba(255, 255, 255, 1)",
            },
            scale: {
                ticks: { beginAtZero: true },
                line: {
                    Color: "rgba(255, 255, 255, 1)",
                },
            },
        },
    });

    Chart.defaults.global.defaultFontFamily = "Roboto";
    Chart.defaults.global.defaultFontColor = "rgba(0, 0, 0, 1)";
    Chart.defaults.global.defaultColor = "rgba(0, 0, 0, 1)";

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
                    <a className="trp_BannerA" href="http://localhost:3012/">
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
                    </div>
                    <div className="trp_CommentListBox">
                        <div className="trp_BoxNameBar">연관 댓글 순위</div>
                        <div className="trp_CommentList">
                            <div className="trp_CLChip_L">순위</div>
                            <div className="trp_CLChip_M">내용</div>
                            <div className="trp_CLChip_R">빈도</div>
                            <div className="trp_CLChip_4">테스트</div>
                            <div className="trp_CLChip_4">테스트</div>
                            <div className="trp_CLChip_4">테스트</div>
                        </div>
                    </div>
                    <div className="trp_GraphBox_1">
                        <div className="trp_BoxNameBar">좋아요 싫어요 평균</div>
                        <div className="trp_BarGraphContainer">
                            <Bar data={barInfo.data} options={barInfo.options} />
                        </div>
                    </div>
                    <div className="trp_GraphBox_2">
                        <div className="trp_BoxNameBar">활동 비</div>
                        <div className="trp_BarGraphContainer">
                            <Bar data={barInfo.data} options={barInfo.options} />
                        </div>
                    </div>
                    <div className="trp_GraphBox_3">
                        <div className="trp_BoxNameBar">피드백 지수</div>
                        <div className="trp_BarGraphContainer">
                            <Bar data={barInfo.data} options={barInfo.options} />
                        </div>
                    </div>
                    <div className="trp_GraphBox_4">
                        <div className="trp_BoxNameBar">
                            일별 키워드 관련 동영상 조회 수
            </div>
                        <Line data={line3Info.data} options={line3Info.options} />
                    </div>
                    <div className="trp_GraphBox_5">
                        <div className="trp_BoxNameBar">일별 키워드 검색량</div>
                        <Line data={lineViewInfo.data} options={lineViewInfo.options} />
                    </div>
                    <div className="trp_GraphBox_6">
                        <div className="trp_BoxNameBar">주별 순위</div>
                        <Line data={lineRankInfo.data} options={lineRankInfo.options} />
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
                        <div className="trp_PFKeywordCompony">아워스코어 / (아워스코어)</div>
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
                        <div className="trp_BoxNameBar">추천 키워드</div>
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
