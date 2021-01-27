import React, { Component } from 'react';

class ListPrac extends Component {
    render() {
        return (
            this.props.items.map((item) => (
                <li>{item}</li>
            ))
        );
    }
}
export default ListPrac;