import React, { Component } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import CloudItem from './CloudItem';

SwiperCore.use([Navigation, Pagination, Autoplay]);
class RankList extends Component {

    state = {
        items: [],
        style: {
            Parent: {
                backgroundColor: "grey",
                width: "900px",
                height: "400px",
                float: "center"
            },
            CloudImg: {
                width: "400px",
                height: "200px",
                float: "left",
                backgroundColor: "purple"
            },
            CloudList: {
                width: "400px",
                height: "100%",
                float: "right",
                backgroundColor: "purple"
            }
        }
    }

    componentWillMount() {
        this.refreshResults(this.props.keyword);
    }
    componentDidMount() {
    }
    /**
     * ------------------------------------------------------------------------------> 김종규
     * 클라우드 아이템 리스트 새로고침
     */
    refreshResults = (newKeyword) => {
        this.state.items = [newKeyword + "0",
        newKeyword + "1",
        newKeyword + "2",
        newKeyword + "3",
        newKeyword + "4",
        newKeyword + "5",
        newKeyword + "6",
        newKeyword + "7"];
        this.renderLists();
    }

    renderLists = () => {
        return (
            this.state.items.map((element) => (
                <SwiperSlide>
                    <CloudItem
                        type={this.props.type}
                        item={element}>
                    </CloudItem>
                </SwiperSlide>
            )
            )
        );
    }

    render() {
        return (
            <div
                className="CloudSpace"
                style={this.state.style.Parent}
            >
                <div
                    className="CloudImg"
                    style={this.state.style.CloudImg}>
                    <p>클라우드 이미지</p>
                    <p>{this.props.keyword}</p>
                </div>
                <Swiper
                    className="CloudList"
                    style={this.state.style.CloudList}
                    spaceBetween= {50}
                    slidesPerView={5}
                    direction={'vertical'}
                    pagination={{ clickable: true }}
                >
                    {this.renderLists()}
                </Swiper>
            </div>
        );
    }
}

export default RankList;