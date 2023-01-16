import axios from 'axios';
import React, { useEffect, useState } from 'react'

const PostsList = () => {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [page, setPage] = useState(1);
    const [lastpage, setLastPage] = useState(1);

    const callAPI = async () => {
        setLoading(true);
        const result = await axios.get('/posts/list.json?page=' + page);
        setPosts(result.data.list);
        const total = result.data.total;
        setLastPage(Math.ceil(total/10));
        setLoading(false);
    }

    useEffect(() => {
        callAPI();
    }, [page]);

    if (loading) {
        return (<h1>로딩중</h1>);
    }

    return (
        <div>
            {posts.map(post => <div key={post.id}><h5>{post.title} ({post.userid})</h5></div>)}
            <button onClick={() => setPage(page - 1)} disabled = {page === 1 && true}>이전</button>
            <span>  {page}  </span>
            <button onClick={() => setPage(page + 1)} disabled = {page === lastpage && true}>다음</button>
        </div>
    )
}

export default PostsList