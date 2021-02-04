import React, { Component } from 'react';

class AlltimeResultPage extends Component {

    state = {
        ctgr: this.props.match.params.ctgr,
    }

    componentDidMount() {
    }

    render() {
        return (
            <div>
                <p>
                    올타임 키워드 세부 페이지
                </p>
                <p>
                    카테고리: {this.state.ctgr}
                </p>
            </div>
        );
    }
}

export default AlltimeResultPage;