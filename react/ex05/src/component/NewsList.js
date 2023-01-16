import axios from 'axios';
import React, { useEffect, useState } from 'react'
import NewsItem from './NewsItem';

const NewsList = () => {
    const [data, setData] = useState();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState();

    const callAPI = async() => {
        try {
            setLoading(true);
            const res = await axios.get('https://newsapi.org/v2/top-headlines?country=kr&apiKey=0d2cfc3452514a4f93903b35e762e40d');
            setData(res.data.articales);    
            setLoading(false);
        } catch (e) {
            setError(e.message);
            setLoading(false);
        }
    }

    useEffect (() => {
        callAPI();
    }, []);
    
    if(loading) {
        return(<h1>데이터를 불러오는 중입니다.</h1>);
    }
    
    if(!data) {
        return(<h1>에러 : {error}</h1>);
    }

    return (
        <div>
            {data.map(article => <NewsItem key = {article.url} article = {article}/>)}
        </div>
    )
}

export default NewsList