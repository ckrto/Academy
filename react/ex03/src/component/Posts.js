import React, { useEffect, useState } from 'react'

const Posts = ({id, name}) => {
    const [posts, setPosts] = useState([]);
    const callAPI = () => {
        fetch('https://jsonplaceholder.typicode.com/posts')
        .then(response => response.json())
        .then(json => { 
            // console.log(json);
            const newPosts = json.filter(post => post.userId === id);
            setPosts(newPosts);
            
        });
    }

    useEffect(() => {
        callAPI();
    }, [id]);

    if(!posts) {
        return(<h2>데이터를 불러오는중</h2>);
    }

    return (
        <div>
            <h3>{name} Post List</h3>
            {posts.map(post => <h5 key = {post.id}>{post.title}</h5>)}
        </div>
    )
}

export default Posts