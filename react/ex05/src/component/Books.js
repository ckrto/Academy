import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Book from './Book';
import "./Book.css";
import qs from 'qs';

const Books = ( {location} ) => {
    const search = qs.parse(
        location.search,{ignoreQueryPrefix:true}
    );
    const query = search.title;

    const [books, setBooks] = useState();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState();

    const callAPI = async() => {
        try {
            setLoading(true);
            const url=`https://dapi.kakao.com/v3/search/book?target=title&size=5&query=${query}`
            const config={
                headers:{"Authorization": "KakaoAK 2469aaeb17ee099f8a89b6662cd32e66"}
            }

            const res = await axios.get(url, config);
            setBooks(res.data.documents);
            setLoading(false);
        } catch(e) {
            setLoading(false);
            setError(e.message);
        }
    }

    useEffect (() => {
        callAPI();
    }, [query]);

    if(loading) {
        return(<h1>데이터를 불러오는 중입니다.</h1>);
    }
    
    if(!books) {
        return(<h1>에러 : {error}</h1>);
    }

    return (
        <div className="books">
            {books.map(book => <Book key = {book.isbn} book = {book}/>)}
            
        </div>
    )
}

export default Books