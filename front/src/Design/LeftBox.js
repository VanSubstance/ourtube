import React from "react";
import "./Css/leftbox.css"
import { LeftBoxFont } from "./";

const LeftBox = (props) => {

  return (
    <div
      className="mp_LeftBox">
      <div className="mp_leftThumbnailBox">
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
      <LeftBoxFont
        keyword={props.keyword}
        data={props.data}
        ctgr={props.ctgr}
        clickEvent={props.clickEvent}>
      </LeftBoxFont>
      <a
        className="mp_lookWellBox"
        href={/trend/ + props.keyword.title}>
        <div
          className="mp_lookWell">
          자세히 보기
        </div>
      </a>
    </div>
  );
};

export default LeftBox;

{/* <a
        className="mP_lookWellBox"
        href={/trend/ + props.keyword.title}>
        <div
          className="mP_lookWell">
          자세히 보기
        </div>
      </a> */}
