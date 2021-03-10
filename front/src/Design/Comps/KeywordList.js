import React, { Component } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import KeywordItem from './KeywordItem';
import './Css/Comps.css';

SwiperCore.use([Navigation, Pagination, Autoplay]);
class KeywordList extends Component {

    state = {
        keywords: [0, 1, 2, 3, 4, 5, 6, 7],
        style: {
            width: "800px",
            height: "200px",
        }
    }
    componentDidMount() {
        this.refreshResults(this.props.newCtgr);
    }
    /**
     * ------------------------------------------------------------------------------> 김종규
     * 키워드 리스트 새로고침
     */
    refreshResults = (newCtgr) => {
        this.state.keywords = [newCtgr + "0", 
        newCtgr + "1", 
        newCtgr + "2", 
        newCtgr + "3", 
        newCtgr + "4", 
        newCtgr + "5",
        newCtgr + "6",
        newCtgr + "7",
        newCtgr + "8",
        newCtgr + "9"];
        this.renderLists();
    }

    renderLists = () => {
        return (
            this.state.keywords.map((element) => (
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={element}>

                    </KeywordItem>
                </SwiperSlide>
            )
            )
        );
    }

    render() {
        return (
            <div
                className="keywordList">
                <Swiper
                    className= "keywordList"
                    spaceBetween={20}
                    slidesPerView={4}
                    navigation
                    loop={true}
                    autoplay={true}
                    onSlideChange={() => console.log('slide change')}
                    pagination={{ clickable: true }}

                >
                    {/* {this.renderLists()} 
                    <p>{this.props.type} 내 선택한 카테고리 내 키워드 순위 리스트</p>*/}

                <SwiperSlide className="keywordItem">
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[0]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[1]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[2]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[3]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[4]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[5]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[6]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[7]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[8]}>

                    </KeywordItem>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItem
                        type = {this.props.type}
                        keyword={this.state.keywords[9]}>

                    </KeywordItem>
                </SwiperSlide>
                </Swiper>
                <div
                    className="keywordMarginBox"></div>
            </div>
        );
    }
}

export default KeywordList;