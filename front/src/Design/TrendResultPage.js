import React, { Component } from 'react';

class TrendResultPage extends Component {

    state = {
        ctgr: this.props.match.params.ctgr,
    }

    componentDidMount() {
    }

    render() {
        return (
            <div>
                <p>
                    트렌드 키워드 세부 페이지
                </p>
                <p>
                    카테고리: {this.state.ctgr}
                </p>
            </div>
        );
    }
}

export default TrendResultPage;