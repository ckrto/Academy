import axios from 'axios';
import React, { useState } from 'react'

const ShopPage = () => {
    const [shops, setShop] = useState();
    
    const callAPI = async() => {
        const url = '/v1/search/shop.json';
        const config = {
            params : {
                query : '노트북'
            },
            headers : {
                'Content-Type' : 'application/json',
                'X-Naver-Client-Id' : 'DC8ojH4p4RE2lMhourmV',
                'X-Naver-Client-Secret' : '1KTqGCjPFg'
            }
        }
        const result = await axios(url, config);
        setShop(result.data);
    }

    return (
        <div>
            <textarea rows={5} cols={100} value={JSON.stringify(shops, null, 4)}/>
            <button onClick={callAPI}>불러오기</button>
        </div>
    )
}

export default ShopPage