import React, { useEffect, useState } from 'react'
import Comment from './Comment';

const Post = ({post}) => {
    const {id, title,body} = post;
    const [comments, setComments] = useState();
    const [show, setShow] = useState(false);
    
    const callAPI = () => {
        fetch(`https://jsonplaceholder.typicode.com/comments?postId=${id}`)
        .then(response => response.json())
        .then(json => {
            setComments(json);
            console.log(id, json.length);
        });
    }

    useEffect(() => {
        callAPI();
    }, [id]);

    return (
        <div style={{textAlign:'left', width:500, margin :'0px auto'}}>
            <span>[{id}] </span>
            <span>{title}</span>
            <p style = {{color : 'gray', borderBottom: '1px dotted gray'}}>{body} <br/> 
            <button onClick={() => setShow(!show)}>{show ? '댓글숨기기' : '댓글보기'}</button>{show && comments.map(c => <Comment key = {c.id} comment = {c}/>)}</p>
        </div>
    )
}

export default Post