import React, { Component } from 'react';
import './Styles.css';
import { ItemBox } from './';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

SwiperCore.use([Navigation, Pagination, Autoplay]);
class ItemBoxList extends Component {
    render() {
        return (
            <div>
                <Swiper id="_itemboxposition"
                    spaceBetween={20}
                    navigation
                    pagination={{ clickable: true }}
                    onSlideChange={() => console.log('slide change')}
                    slidesPerView={4.5}>
                    
                
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                    <SwiperSlide id= "_itemboxdiv">
                        <ItemBox></ItemBox>
                    </SwiperSlide>
                </Swiper>
               
            </div>
        );
    }
}

export default ItemBoxList;