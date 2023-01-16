import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import BlogItem from './BlogItem';
import qs from 'qs';

const BlogList = ( {match, location} ) => {
    const [blogs, setBlogs] = useState();
    const [is_end, setIs_end] = useState(false);
    const title = match.params.title;
    const search = qs.parse (location.search, {ignoreQueryPrefix:true});
    const page = parseInt(search.page);

    const callAPI = async() => {
        const url = `https://dapi.kakao.com/v2/search/blog?target=title&query=${title}&page=${page}`;
        const config = {headers : {"Authorization" : "KakaoAK a875616508d005928e0c2b275934ef8a"}};
        const result = await axios.get(url, config);
        const documents = result.data.documents;
        const newBlog = page === 1 ? documents : blogs.concat(documents); 
        setBlogs(newBlog);
        setIs_end(result.data.meta.is_end);
    }
    
    useEffect(() => {
        callAPI();
    }, [title, page]);

    if(!blogs) {
        return(<h2>데이터를 불러오는 중</h2>);
    }

    return (
        <div>
            {blogs.map (b => <BlogItem key={b.isbn} blog = {b} />)}
            {!is_end && <Link to={`/blog/${title}?page=${page+1}`}>더보기</Link>}
        </div>
    )
}

export default BlogList