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
        {props.keywords.map((dataRow, index) => {
          return (
            <div className="tmp_KeywordRankParent">
              <button
                className="tmp_KeywordRankButton"
                onClick={() => props.func(dataRow.title, 0)}
              ></button>
              <div className="tmp_KeywordRankChip">{index + 1}</div>
              <div className="tmp_KeywordNameChip">{dataRow.title}</div>
              <div className="tmp_KeywordScoreChip">아울스코어</div>
              <div className="tmp_KeywordScoreChip">누적 조회수</div>
              <div className="tmp_KeywordScoreChip">누적 동영상</div>
              <div className="tmp_KeywordScoreChip">누적 댓글수</div>
              <div className="tmp_KeywordScoreChip">검색량</div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ListFont;
