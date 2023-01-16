import React, { useEffect, useState } from 'react'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import { ArrowBackIos, ArrowForwardIos } from '@material-ui/icons';
import './Carousel.css';

const data = [
    { id: 1, url: 'https://picsum.photos/seed/1/900/250' },
    { id: 2, url: 'https://picsum.photos/seed/2/900/250' },
    { id: 3, url: 'https://picsum.photos/seed/3/900/250' },
    { id: 4, url: 'https://picsum.photos/seed/4/900/250' },
    { id: 5, url: 'https://picsum.photos/seed/5/900/250' },
]

function SampleNextArrow(props) {
    const { className, onClick } = props;
    console.log(className)
    return (
        <div className={className} onClick={onClick}>
            <ArrowForwardIos style={{color:'black',fontSize:30}}/>
        </div>
    );
}

function SamplePrevArrow(props) {
    const { className, onClick } = props;
    return (
        <div className={className} onClick={onClick}>
            <ArrowBackIos style={{color:'black',fontSize:30}}/>
        </div>
    );
}

const Banner = () => {
    const [banners, setBanners] = useState();

    useEffect(() => {
        setBanners(data);
    }, []);

    const settings = {
        dots: true,
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        nextArrow: <SampleNextArrow />,
        prevArrow: <SamplePrevArrow />
    };

    if (!banners) return <h1>데이터를 불러오는 중입니다.</h1>

    return (
        <Slider {...settings}>
            {banners.map(banner =>
                <img src={banner.url} alt={banner.url} key={banner.id} />
            )}
        </Slider>
    )
}

export default Banner