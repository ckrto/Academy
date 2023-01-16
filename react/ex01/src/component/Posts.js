import React, { useEffect, useState } from 'react'

const Posts = () => {
    const [posts, setPosts] = useState(null);
    const [page, setPage] = useState(1);
    const [last, setLast] = useState(1);

    useEffect(() => {
        callAPI();
    }, [page]);

    const callAPI = () => {
        fetch('https://jsonplaceholder.typicode.com/posts')
        .then(response => response.json())
        .then(json => {
            console.log(json);
            let start = (page -1) * 10 + 1;
            let end = (page * 10);
            let newPosts = json.filter(post => post.id >= start && post.id <= end);
            setPosts(newPosts);
            setLast(Math.ceil(json.length/10));
        });
    } 
    return (
        <div>
            <h1>Post List</h1>
            {posts ? 
                posts.map(p => <h3>[{p.id}] {p.title}</h3>) : '<h2>데이터를 불러오는 중</h2>'
            }
            <button disabled = {page === 1 && true} onClick={() => setPage(page-1)}>이전</button>
            <span> {page}/{last} </span>
            <button disabled = {page === last && true} onClick={() => setPage(page+1)}>다음</button>
        </div>
    )
}

export default Posts