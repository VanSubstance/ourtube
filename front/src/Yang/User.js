import React, { Component } from 'react';
// import './Styles.css';
import { ItemBoxList } from '.';
import LeftBox from './LeftBox';
import WallPaper from './WallPaper';
class User extends Component {
    render() {
        return (
            <body>
                <WallPaper></WallPaper>
                <ItemBoxList></ItemBoxList>
                <LeftBox></LeftBox>
            </body>
        );
    }
}

export default User;