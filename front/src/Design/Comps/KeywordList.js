import React, { useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import KeywordItem from './KeywordItem';
import './Css/Comps.css';

SwiperCore.use([Navigation, Pagination, Autoplay]);

const KeywordList = (props) => {
    return (
        <div
            className="keywordList">
            <Swiper
                className= "keywordList"
                spaceBetween={20}
                slidesPerView={5}
                navigation
                loop={false}
                autoplay={false}
                pagination={{ clickable: true }}

            >
                {
                    props.keywords.length === 0
                    ? (
                        <SwiperSlide>
                            <KeywordItem
                                keyword={
                                    {
                                        title: "없음",
                                        thumbnail: "썸네일 주소"
                                    }
                                }>
                            </KeywordItem>
                        </SwiperSlide>
                    )
                    : (
                        props.keywords.map((element) => {
                            return (
                                <SwiperSlide>
                                    <KeywordItem
                                        keyword={element}>
                                    </KeywordItem>
                                </SwiperSlide>
                            );
                        })
                    )
                }

            </Swiper>
            <div
                className="keywordMarginBox"></div>
        </div>
    );
}

export default KeywordList;