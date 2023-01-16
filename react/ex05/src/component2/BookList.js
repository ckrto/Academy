import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import BookItem from './BookItem';
import qs from 'qs';
import "./Book.css";

const BookList = ( {match, location} ) => {
    const [books, setBooks] = useState();
    const [is_end, setIs_end] = useState(false);
    const title = match.params.title;
    const search = qs.parse (location.search, {ignoreQueryPrefix:true});
    const page = parseInt(search.page);

    const callAPI = async() => {
        const url = `https://dapi.kakao.com/v3/search/book?target=title&size=5&query=${title}&page=${page}`;
        const config = {headers : {"Authorization" : "KakaoAK a875616508d005928e0c2b275934ef8a"}};
        const result = await axios.get(url, config);
        const documents = result.data.documents;
        const newBooks = page === 1 ? documents : books.concat(documents); 
        setBooks(newBooks);
        setIs_end(result.data.meta.is_end);
    }
    
    useEffect(() => {
        callAPI();
    }, [title, page]);

    if(!books) {
        return(<h2>데이터를 불러오는 중</h2>);
    }

    return (
        <div>
            {books.map (b => <BookItem key={b.isbn} book = {b} />)}
            {!is_end && <Link to={`/book/${title}?page=${page+1}`}>더보기</Link>}
        </div>
    )
}

export default BookList