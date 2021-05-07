import { TableHead } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import "../Styles.css";

const ListFont = (props) => {
  return (
    <div>
      <div className="tmp_BoxNameBarNoPad">
        <div className="tmp_KeywordRankHead">순위</div>
        <div className="tmp_KeywordNameHead">이름</div>
        <div className="tmp_KeywordScoreHead">아울스코어</div>
        <div className="tmp_KeywordScoreHead">누적 조회수</div>
        <div className="tmp_KeywordScoreHead">누적 동영상</div>
        <div className="tmp_KeywordScoreHead">누적 댓글수</div>
        <div className="tmp_KeywordScoreHead">검색량</div>
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
        {props.keywords.map((data, index) => {
          return (
            <div className="tmp_KeywordRankParent">
              <button
                className="tmp_KeywordRankButton"
                onClick={() => props.func(data.title, 0)}
              ></button>
              <div className="tmp_KeywordRankChip">{data.rank}</div>
              <div className="tmp_KeywordNameChip">{data.title}</div>
              <div className="tmp_KeywordScoreChip">아울스코어</div>
              <div className="tmp_KeywordScoreChip">{data.avgAccuView}</div>
              <div className="tmp_KeywordScoreChip">{data.numAccuVid}</div>
              <div className="tmp_KeywordScoreChip">{data.avgAccuComment}</div>
              <div className="tmp_KeywordScoreChip">검색량</div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ListFont;
