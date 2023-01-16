import axios from 'axios';
import React, { useEffect, useState } from 'react'

const BannerRead = ({ match }) => {
    const id = match.params.id;

    const [banner, setBanner] = useState();
    
    const callAPI = async () => {
        const result = await axios.get('/banners/read/' + id);
        setBanner(result.data);
    }

    useEffect(() => {
        callAPI();
    }, []);

    if(!banner) {
        return(<h1>로딩중</h1>);
    }

    return (
        <div>
            <h1>{id}번의 Banner Infomation</h1>
            <h3>{banner.title} ({banner.bshow === 1 ? '진행중' : '종료'})</h3>
            <img src={banner.url} style={{width:'90%'}}/>
        </div>
    )
}

export default BannerRead