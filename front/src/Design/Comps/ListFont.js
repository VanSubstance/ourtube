import React, { useState, useEffect } from "react";
import "../Styles.css";

const ListFont = (props) => {
  // 1. ListFont에 지역 변수 선언 <- 정렬된 prop.keywords를 받기 위한 변수
  const [sortByRankList, setSortByRankList] = useState([]);

  useEffect(() => {
    setSortByRankList([]);
    sortByRank(props.keywords);
  }, [props.keywords]);

  // 2. ListFont에 정렬 함수 선언
  const sortByRank = (dataRaw) => {
    let arraySort = [];
    dataRaw.forEach(({ rank }) => {
      arraySort = arraySort.concat(rank);
      // 정렬된 arraySort
      arraySort.sort(function (a, b) {
        if (a > b) return 1;
        if (a === b) return 0;
        if (a < b) return -1;
      });
    });
    let result = [];
    // 정렬된 rank와 이전의 rank와 비교 후 같으면 map 전체 출력
    arraySort.forEach((rankSorted) => {
      dataRaw.forEach((dataTemp) => {
        if (rankSorted === dataTemp.rank) {
          result = result.concat(dataTemp);
        }
      });
    });
    setSortByRankList(result);
  };
  return (
    <div>
      <div className="tmp_BoxNameBarNoPad">
        <div className="tmp_KeywordRankHead">순위</div>
        <div className="tmp_KeywordNameHead">이름</div>
        <div className="tmp_KeywordScoreHead">아울스코어</div>
        <div className="tmp_KeywordScoreHead">평균 조회수</div>
        <div className="tmp_KeywordScoreHead">누적 동영상</div>
        <div className="tmp_KeywordScoreHead">평균 댓글수</div>
        <div className="tmp_KeywordScoreHead">평균 좋싫비</div>
      </div>

      <div id="scrollbitch">
        {/* 
        data 테이터 형태:
        {
          title: element.title,
          rank: data.rank,
          numNewVid: data.numNewVid,
          avgNewView: data.avgNewView,
          avgNewLike: data.avgNewLike,
          avgNewDislike: data.avgNewDislike,
          avgNewComment: data.avgNewComment,
          numAccuVid: data.numAccuVid,
          avgAccuView: data.avgAccuView,
          avgAccuLike: data.avgAccuLike,
          avgAccuDislike: data.avgAccuDislike,
          avgAccuComment: data.avgAccuComment
        }
        */}
        {sortByRankList.map((data) => {
          return (
            <div className="tmp_KeywordRankParent">
              <button
                className="tmp_KeywordRankButton"
                onClick={() => props.func(data.title, 0)}
              ></button>
              <div className="tmp_KeywordRankChip">{data.rank}</div>
              <div className="tmp_KeywordNameChip">{data.title}</div>
              <div className="tmp_KeywordScoreChip">
                {(100 * data.ourScore).toFixed(1)}
              </div>
              <div className="tmp_KeywordScoreChip">{data.avgAccuView}</div>
              <div className="tmp_KeywordScoreChip">{data.numAccuVid}</div>
              <div className="tmp_KeywordScoreChip">{data.avgAccuComment}</div>
              <div className="tmp_KeywordScoreChip">
                {(data.avgAccuLike / data.avgAccuDislike).toFixed(1)}
                &nbsp;: 1
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ListFont;
