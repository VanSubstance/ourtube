import React, { Component } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import CtgrItem from './CtgrItem';

SwiperCore.use([Navigation, Pagination, Autoplay]);
class CtgrResults extends Component {

    state = {
        keywords: [0, 1, 2, 3, 4, 5],
    }

    componentDidMount () {
        this.refreshResults(this.props.newCtgr)
    }

    /**
     * ------------------------------------------------------------------------------> 김종규
     * 카테고리 리스트 새로고침
     */
    refreshResults = (newCtgr) => {
        this.state.keywords = [newCtgr + "0", 
        newCtgr + "1", 
        newCtgr + "2", 
        newCtgr + "3", 
        newCtgr + "4", 
        newCtgr + "5"];
        this.renderLists();
    }
    
    renderLists = () => {
        return (
            this.state.keywords.map((element) => (
                <SwiperSlide>
                    <CtgrItem
                        type = {this.props.type}
                        keyword={element}>

                    </CtgrItem>
                </SwiperSlide>
            ))
        );
    }

    render() {
        return (
            <div>
                <Swiper
                style = {
                    {
                        height: "400px",
                    }
                }
                    spaceBetween={50}
                    slidesPerView={3}
                    direction={'horizontal'}
                    navigation
                    loop={true}
                    pagination={{ clickable: true }}

                >
                    {this.renderLists()}
                </Swiper>
            </div>
        );
    }
}

export default CtgrResults;