import React, { useState, useEffect } from 'react';
import '../Styles.css';
import axios from "axios";

const ProfileChip = (props) => {
  const [url] = useState("http://222.232.15.205:8082");
  // 데이터
  const [data, setData] = useState({
    title: "",
    thumbnail: "/Ex/happy.jpg",
    genre1:"",
    genre2:""
  });

  // 생성시 게임 제목 -> 필요한 데이터 불러오기
  useEffect(() => {
    getData(props.title);
  }, []);

  // 장르 리스트 가져오기: 최대 7개
  const getData = async(title) => {
    await axios
      .get(url + "/deploy/game/profile/" + title)
      .then(({data}) => {
        setData({
          title: title,
          thumbnail: data.thumbnail,
          genre1: data.genre1,
          genre2: data.genre2
        })
      })
      .catch((e) => {
        console.error(e);
      });
  };
    return (
      <div>
        <div className="tmp_PFBoxChip">
          <button className="tmp_PFBoxDeleteButton" onClick = {() => {props.func(data.title, 1)}}>X</button>
          <a
            className="tmp_lookWellBox"
            // href={/game/ + props.keyword.title}
          >
            <div className="tmp_lookWell">자세히 보기</div>
          </a>
          <div className="tmp_PFThumbnailCircle">
            <img className="tmp_PFThumbnail" src=
            {data.thumbnail !== undefined ? data.thumbnail : "/Ex/happy.jpg"}></img>
          </div>
          <div className="tmp_PFKeywordName">
            {data.title !== undefined ? data.title : "제목"}
          </div>
          <div className="tmp_PFKeywordYear">
            {data.genre1 !== undefined ? data.genre1 : "장르 1"}
          </div>
          <div className="tmp_PFKeywordCompony">
            {data.genre2 !== undefined ? data.genre2 : "장르 2"}
          </div>
        </div>
      </div>
    );
}

export default ProfileChip;