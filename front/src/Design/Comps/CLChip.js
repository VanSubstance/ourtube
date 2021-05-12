import React, { useState, useEffect } from "react";
import "../Styles.css";
import axios from "axios";

const CLChip = () => {
  const [url] = useState("http://222.232.15.205:8082");
  // // 데이터
  // const [data, setData] = useState({
  //   title: "",
  //   thumbnail: "/Ex/happy.jpg",
  //   genre1: "",
  //   genre2: "",
  // });

  // // 게임 제목 변경 시 -> 필요한 데이터 불러오기
  // useEffect(() => {
  //   getData(props.title);
  // }, [props.title]);

  // const getData = async (title) => {
  //   await axios
  //     .get(url + "/deploy/game/profile/" + title)
  //     .then(({ data }) => {
  //       setData({
  //         title: title,
  //         thumbnail: data.thumbnail,
  //         genre1: data.genre1,
  //         genre2: data.genre2,
  //       });
  //     })
  //     .catch((e) => {
  //       console.error(e);
  //     });
  // };

  return (
    <div
      className="trp_CLChipContainer">
      <div className="trp_CLChip_L">
        순위
      </div>
      <div className="trp_CLChip_M">
        내용
      </div>
      <div className="trp_CLChip_R">
        빈도
      </div>
    </div >
  );
};

export default CLChip;
