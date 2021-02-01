import React, { Component } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss'; 
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import CtgrThumbnail from './CtgrThumbnail';

SwiperCore.use([Navigation, Pagination, Autoplay]);
class CtgrResults extends Component {

    state = {
    }

    render() {
        return (
            <div>
                <Swiper
                    spaceBetween={50}
                    slidesPerView={3}
                    direction = {'horizontal'}
                    onSlideChange={() => console.log('slide change')}
                    navigation
                    loop = {true} 
                    pagination={{ clickable: true }} 

                >
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                    <SwiperSlide>
                        <CtgrThumbnail></CtgrThumbnail>
                    </SwiperSlide>
                </Swiper>
            </div>
        );
    }
}

export default CtgrResults;