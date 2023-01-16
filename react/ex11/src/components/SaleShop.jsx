import React, { useEffect, useState } from 'react'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from 'react-slick';
import axios from 'axios';
const SaleShop = () => {
    const [shops, setShop] = useState([]);
    const callgetSale = async () => {
        const result = await axios.get(`/api/shop/getSale`);
        setShop(result.data);
    }

    useEffect(() => {
        callgetSale();
    }, [])

    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 3
    };

    if (!shops) return <h1>Loading....</h1>

    return (
        <div>
            <Slider {...settings}>
                {shops.map(shop =>
                    <>
                        <img src={shop.image} width={300} />
                    </>
                )}
            </Slider>
        </div>
    )
}

export default SaleShop