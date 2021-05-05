import { TableHead } from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import '../Styles.css';
import ProfileChip from "./ProfileChip";

const ProfileChipContainer = (props) => {

    // ProfileChip 추가 함수
    const addProfileChip = (keyword) => {
        return (
            <ProfileChip keyword = {{
                title: "테스트",
                thumbnail: "xptmxm"
              }}>
            </ProfileChip>
        );
    }

    return (
        <div>
            <ProfileChip keyword = {{
                title: "테스트",
                thumbnail: "xptmxm"
              }}>
            </ProfileChip>
            {
                props.titles !== undefined ? ( props.titles.forEach((title) => {
                    console.log("작동 ",title);
                    return (addProfileChip(title));                    
                })) : "없음"
            }
        </div>
    );
}

export default ProfileChipContainer;