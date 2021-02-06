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
        seq: [0, 1, 2, 3, 4, 5],
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
                <Swiper
                    spaceBetween={50}
                    slidesPerView={5}
                    direction={'horizontal'}
                    onSlideChange={() => console.log('slide change')}
                    navigation
                    loop={true}
                    pagination={{ clickable: true }}

                >
                    {this.state.seq.map((element) => (
                        <SwiperSlide>
                            <CtgrThumbnail
                                seq={element}>

                            </CtgrThumbnail>
                        </SwiperSlide>
                    )
                    )}
                </Swiper>
            </div>
        );
    }
}

export default CtgrResults;