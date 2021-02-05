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
            height: "200px",
        }
    }
    componentDidMount () {
        this.refreshResults(this.props.newCtgr)
    }
    /**
     * ------------------------------------------------------------------------------> 김종규
     * 키워드 리스트 새로고침
     */
    refreshResults = (newCtgr) => {
        this.setState({
            seq: [newCtgr + "0", 
            newCtgr + "1", 
            newCtgr + "2", 
            newCtgr + "3", 
            newCtgr + "4", 
            newCtgr + "5"]
        });
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