import React, { Component } from 'react';

class TrendMainPage extends Component {
    
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

export default TrendMainPage;