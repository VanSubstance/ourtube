import React from "react";
import "./Styles.css";
import "./Css/styles.css";
import { LeftBoxFont } from "./";

const LeftBox = (props) => {

  return (
    <div
      className="mp_LeftBox">
      <div className="mp_thumbnailBox">
        {props.keyword != undefined ? (
          <img
            className="mp_leftThumnail"
            src={props.keyword.thumbnail}>
          </img>
        ) : (
          <img src="https://upload.wikimedia.org/wikipedia/commons/9/92/Question_mark_alternate_inverted.svg">
          </img>
        )}
      </div>
      <a
        className="mP_lookWellBox"
        href={/trend/ + props.keyword.title}>
        <div
          className="mP_lookWell">
            자세히 보기
        </div>
      </a>
      {/* <a
        className="mP_lookWellBox"
        href={/trend/ + props.keyword.title}>
        <div
          className="mP_lookWell">
          자세히 보기
        </div>
      </a> */}
      <LeftBoxFont
        keyword={props.keyword}
        data={props.data}
        clickEvent = {props.clickEvent}>
      </LeftBoxFont>
    </div>
  );
};

export default LeftBox;
