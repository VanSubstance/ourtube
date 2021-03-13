import React, { useState } from "react";
import axios from "axios";
import "./Styles.css";
import moment from "moment";

const LeftBoxFont = (props) => {
    const [dateTarget, setDateTarget] = useState(
        {
            right: moment().format('yyyy-MM-DD'),
            left: moment().subtract(1, 'days').format('yyyy-MM-DD')
        }
    );

  return (
    <div>
      <div id="_gamestyle">
        <span>장르 나열</span>
      </div>
      <div id="_gamename">
        {props.keyword !== undefined ? (
          <span>{props.keyword.title}</span>
        ) : (
          <span>없음</span>
        )}
      </div>
      <div id="_glorystyle1">
        <span>이 주의 최고 조회수</span>
      </div>
      <div id="_glorystyle2">
        <span>이 주의 최고 동영상</span>
      </div>
      <div id="_glorystyle3">
        <span>이 주의 최고 게임</span>
      </div>
      <div id="_leftboxmainfont1">
        <span>순위</span>
      </div>
      <div id="_leftboxmainfont2">
        <span>조회수</span>
      </div>
      <div id="_leftboxmainfont3">
        <span>신규 동영상</span>
      </div>
      <div id="_leftboxmainfont4">
        <span>아울스코어</span>
      </div>
      <div id="_now">
        <span>02/10</span>
      </div>
      <div id="_past">
        <span>02/03</span>
      </div>
      <div id="_pastranknumber1">
        <span>1</span>
      </div>
      <div id="_pastranknumber2">
        <span>{props.data[dateTarget.left].viewCount}</span>
      </div>
      <div id="_pastranknumber3">
        <span>3</span>
      </div>
      <div id="_pastranknumber4">
        <span>4</span>
      </div>
      <div id="_nowranknumber1">
        <span>5</span>
      </div>
      <div id="_nowranknumber2">
        <span>{props.data[dateTarget.right].viewCount}</span>
      </div>
      <div id="_nowranknumber3">
        <span>7</span>
      </div>
      <div id="_nowranknumber4">
        <span>8</span>
      </div>
    </div>
  );
};

export default LeftBoxFont;
