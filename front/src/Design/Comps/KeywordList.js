import React, { Component } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import KeywordItemVertical from './KeywordItemVertical';
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
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={element}>

                    </KeywordItemVertical>
                </SwiperSlide>
            )
            )
        );
    }

    render() {
        return (
            <div
                style = {{
                    background: "transparent",
                    border: "1px solid white"
                }}>
                <p>{this.props.type} 내 선택한 카테고리 내 키워드 순위 리스트</p>
                <Swiper
                    className= "keywordListVertical"
                    spaceBetween={20}
                    slidesPerView={7}
                    navigation
                    loop={true}
                    autoplay={true}
                    onSlideChange={() => console.log('slide change')}
                    pagination={{ clickable: true }}

                >
                    {/* {this.renderLists()} */}

                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[0]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[1]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[2]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[3]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[4]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[5]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[6]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[7]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[8]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                <SwiperSlide>
                    <KeywordItemVertical
                        type = {this.props.type}
                        keyword={this.state.keywords[9]}>

                    </KeywordItemVertical>
                </SwiperSlide>
                </Swiper>
                <div
                    className="keywordMarginBox"></div>
            </div>
        );
    }
}

export default KeywordList;