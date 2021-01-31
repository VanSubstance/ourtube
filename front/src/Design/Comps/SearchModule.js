import React, { Component } from 'react';
import CtgrResults from './CtgrResults';

class SearchModule extends Component {
    
    state = {
        outside: {
            margin: "100px",
            background: "#DEDEDE",
        },
        searchBar: {
            width: "180px",
            height: "40px",
            background: "#c8c8c8",
            display: "inline",
        },
        logo: {
            width: "40px",
            height: "40px",
            background: "blue",
            color: "white",
            display: "inline",
        }
        
    }
    render () {
        return (
            <div 
                className = "SearchModule"
                style = {this.state.outside}>
                <div 
                    style = {this.state.logo}> 
                    {this.props.searchType} 
                </div>
                <div style = {this.state.searchBar}> 검색어 </div>
                <div style = {this.state.logo}> 검색 </div>
                <CtgrResults>

                </CtgrResults>
            </div>
        );
    }
}

export default SearchModule;