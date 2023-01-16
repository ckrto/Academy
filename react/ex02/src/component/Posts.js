import React, { useEffect, useRef, useState } from 'react'
import Post from './Post';

const Posts = () => {
    const [posts, setPosts] = useState();
    const [page, setPage] = useState(1);
    const last = useRef(1);

    const callAPI = () => {
        fetch('https://jsonplaceholder.typicode.com/posts')
        .then(response => response.json())
        .then(json => {
            // console.log(json);
            let start = (page - 1) * 3 + 1;
            let end = (page * 3);
            last.current = Math.ceil(json.length/10);

            const newPosts = json.filter(post => post.id >= start && post.id <= end);
            setPosts(newPosts);
        });
    }

    useEffect (() => {
        callAPI();
    }, [page]);
    
    if(!posts) {
        return(<h1>데이터를 불러오는 중</h1>);
    }

    return (
        <div>
            <h1>Post List</h1>
            {posts.map(post => 
                <Post key = {post.id} post = {post}/>)
        }
        <div style={{marginTop : '20px'}}>
            <button onClick = {() => setPage(1)}>처음으로</button> 
            <button disabled = {page === 1 && true} onClick={() => setPage(page - 1)}>이전</button>
            <span> {page} / {last.current} </span>
            <button disabled = {page === last.current && true} onClick={() => setPage(page + 1)}>다음</button>
            <button onClick = {() => setPage(last.current)}>마지막으로</button>
        </div>
        </div>
    )
}

export default Posts