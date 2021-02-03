import React, { Component } from 'react';

class AlltimeMainPage extends Component {
    
    state = {
    }
    render () {
        return (
            console.log("SearchPage opened."),
            <div>
                <p>상세 페이지</p>
                <p>{this.props.searchType}</p>
            </div>
        );
    }
}

export default AlltimeMainPage;