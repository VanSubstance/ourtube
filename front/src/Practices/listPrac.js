import React, { Component } from 'react';

class ListPrac extends Component {
    render() {
        return (
            <ul className="LisePrac">
                {
                    this.props.items.map((item) => (
                        <li>{item}</li>
                    ))
                }
            </ul>
        );
    }
}
export default ListPrac;