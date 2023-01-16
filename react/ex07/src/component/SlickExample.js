import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Slider from 'react-slick';

const SlickExample = () => {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000
    };

    const [data, setData] = useState();

    const callAPI = async () => {
        const result = await axios.get('/banners/show');
        setData(result.data);
    }

    useEffect(() => {
        callAPI();
    }, [])

    if (!data) {
        return (<h1>로딩중</h1>);
    }

    return (
        <div>
            <Slider {...settings}>
                {data.map((item) => (
                    <div key={item.id} className="item">
                        <h1 className='title'>{item.title}</h1>
                        <img src={item.url} alt={item.title} />
                    </div>
                ))}
            </Slider>
        </div>
    )
}

export default SlickExample