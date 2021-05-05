import { TableHead } from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import '../Styles.css';

const ProfileChip = (props) => {

    return (
      <div>
        <div className="tmp_PFBoxChip">
          <button className="tmp_PFBoxDeleteButton" onClick = {() => {props.func(props.keyword.title, 1)}}>X</button>
          <a
            className="tmp_lookWellBox"
            // href={/game/ + props.keyword.title}
          >
            <div className="tmp_lookWell">자세히 보기</div>
          </a>
          <div className="tmp_PFThumbnailCircle">
            <img className="tmp_PFThumbnail" src="/Ex/happy.jpg"></img>
          </div>
          <div className="tmp_PFKeywordName">
            {props.keyword !== undefined && props.keyword.title !== undefined ? props.keyword.title : "제목"}
          </div>
          <div className="tmp_PFKeywordYear">장르 1</div>
          <div className="tmp_PFKeywordCompony">장르 2</div>
        </div>
      </div>
    );
}

export default ProfileChip;