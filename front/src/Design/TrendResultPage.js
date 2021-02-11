import React, { Component } from 'react';
import {KeywordThumbnail, Cloud} from './Comps';
import { Bar, Line, Radar, Pie } from 'react-chartjs-2';

class TrendResultPage extends Component {

    state = {
        keyword: this.props.match.params.keyword,
        options: {
            // 좋아요 싫어요 막대 그래프 옵션
            barLikes: {
                maintainAspectRatio: false,
                scales: {
                    xAxes: [{
                        stacked: true
                    }],
                    yAxes: [{
                        stacked: true,
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            },
            // 선 그래프: 주별 동영상 1개 당 평균 누적 조회수
            lineView: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                        }
                    }]
                }
            },
            // 파이 그래프: 조회수 ~ 좋아요 / 싫어요 비율
            pieLikes: {
            },
            // 선 그래프: 주별 순위
            lineRankPerMonth: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            reverse: true
                        }
                    }]
                }
            },
        },
        datasets: {
            // 좋아요 싫어요 막대 그래프 변수
            barLikes: {
                datasets: [
                    {
                        // 좋아요 / 싫어요 데이터셋 위치
                        label: "좋아요/싫어요 개수",
                        data: [],
                        backgroundColor: "#ff3399"
                    }
                ],
                // 라벨 데이터셋 위치
                labels: ['좋아요', '싫어요']
            },
            // 선 그래프: 주별 동영상 1개 당 평균 누적 조회수
            lineView: {
                datasets: [
                    {
                        label: "조회수",
                        data: [],
                        fill: false,
                        borderColor: "#ff3399",
                        borderWidth: 1
                    }

                ],
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월'
                , '9월', '10월', '11월', '12월']
            },
            // 파이 그래프: 조회수 ~ 좋아요 / 싫어요 비율
            pieLikes: {
                datasets: [
                    {
                        // 좋아요 / 싫어요 데이터셋 위치
                        label: "비율",
                        backgroundColor: ["#00cc99", "#ff3399"],
                        data: [],
                    }
                ],
                // 라벨 데이터셋 위치
                labels: ['좋아요', '싫어요', '없음']
            },
            // 선 그래프: 주별 순위
            lineRankPerWeek: {
                datasets: [
                    {
                        label: "순위",
                        data: [],
                        fill: false,
                        borderColor: "red",
                        borderWidth: 1
                    },
                ],
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월'
                , '9월', '10월', '11월', '12월']
            },
        }
    }

    componentDidMount() {
        this.setDataset();
    }

    // 키워드로부터 정보 추출
    getDatasetFromKeyword = (keyword) => {
        var barLikes = [];
        var lineView = [];
        var pieLikes = [];
        var lineRankPerWeek = [];
        // 평균 좋아요 / 싫어요 변수
        barLikes.push(Math.floor(Math.random() * 1000));
        barLikes.push(Math.floor(Math.random() * 400));
        // 선 그래프: 주별 동영상 1개 당 평균 누적 조회수
        for (var i = 0; i < 12; i++) {
            lineView.push(Math.floor(Math.random() * (10000 - 8000) + 8000));
        }
        // 선 그래프: 주별 순위
        for (var i = 0; i < 12; i++) {
            lineRankPerWeek.push(Math.floor(Math.random() * (20 - 1) + 1));
        }
        // 평균 좋아요 / 싫어요 변수
        pieLikes.push(Math.floor(Math.random() * (10000 - 2000) + 2000));
        pieLikes.push(Math.floor(Math.random() * (5000 - 500) + 500));
        pieLikes.push(Math.floor(Math.random() * (10000 - 2000) + 2000));
        return (
            {
                barLikes: barLikes,
                lineView: lineView,
                pieLikes: pieLikes,
                lineRankPerWeek: lineRankPerWeek,
            }
        );
    }

    // 각 컴포넌트에 변수 전달
    setDataset = () => {
        const {datasets, keyword} = this.state;
        var data = this.getDatasetFromKeyword(keyword);

        // 막대그래프: 평균 좋아요/싫어요
        datasets.barLikes.datasets[0].data = data.barLikes;
        datasets.lineView.datasets[0].data = data.lineView;
        datasets.pieLikes.datasets[0].data = data.pieLikes;
        datasets.lineRankPerWeek.datasets[0].data = data.lineRankPerWeek;

        // 키워드 썸네일
        this.refs.KeywordThumbnail.setThumbnailInfo();
    }

    render() {
        return (
            <div>
                <p>
                    트렌드 키워드 세부 페이지
                </p>
                <p>
                    키워드: {this.state.keyword}
                </p>
                <Cloud
                    ref = "Cloud" keyword = {this.state.keyword}>

                </Cloud>
                <KeywordThumbnail ref = "KeywordThumbnail">

                </KeywordThumbnail>
                <div
                    style={this.state.chart}>
                    <p> 막대 그래프: 동영상 1개 당 평균 좋아요 / 싫어요 </p>
                    <Bar
                        data={this.state.datasets.barLikes}
                        options={this.state.options.barLikes}
                    />
                </div>
                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 주별 동영상 1개 당 평균 누적 조회수 </p>
                    <Line
                        data={this.state.datasets.lineView}
                        options={this.state.options.lineView}
                    />
                </div>
                <div>
                    <p> 파이 그래프: 조회수 ~ 좋아요 / 싫어요 비율 </p>
                    <Pie
                        data={this.state.datasets.pieLikes}
                        options={this.state.options.pieLikes}
                    />
                </div>
                <div
                    style={this.state.chart}>
                    <p> 선 그래프: 주별 순위 </p>
                    <Line
                        data={this.state.datasets.lineRankPerWeek}
                        options={this.state.options.lineRankPerWeek}
                    />
                </div>
            </div>
        );
    }
}

export default TrendResultPage;