import React, { useState, useEffect } from "react";
import "../Styles.css";
import ProfileChip from "./ProfileChip";

const ProfileChipContainer = (props) => {

  const [titles, setTitles] = useState([]);

  useEffect(() => {
    console.log(props.titles);
    setTitles(props.titles);
  }, [props.titles]);

  return (
    <div>
      {titles.map((title) => {
        return (
          <ProfileChip
            title = {title}
            func={props.func1}
          ></ProfileChip>
        );
      })}
    </div>
  );
};

export default ProfileChipContainer;
