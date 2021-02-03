import React, { Component } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation, Pagination, Autoplay } from 'swiper';
import 'swiper/components/navigation/navigation.scss';
import 'swiper/components/pagination/pagination.scss';
import 'swiper/swiper.scss';

import RankListItem from './RankListItem';

SwiperCore.use([Navigation, Pagination, Autoplay]);
class RankList extends Component {

    state = {
        seq: [0, 1, 2, 3, 4, 5, 6, 7],
        style: {
            width: "800px",
            height: "400px",
        }
    }

    render() {
        return (
            <div>
                <p>랭크 리스트</p>
                <Swiper
                    style = {this.state.style}
                    spaceBetween={50}
                    slidesPerView={5}
                    direction={'vertical'}
                    onSlideChange={() => console.log('slide change')}
                    pagination={{ clickable: true }}

                >
                    {this.state.seq.map((element) => (
                        <SwiperSlide>
                            <RankListItem
                                seq={element}>

                            </RankListItem>
                        </SwiperSlide>
                    )
                    )}
                </Swiper>
            </div>
        );
    }
}

export default RankList;