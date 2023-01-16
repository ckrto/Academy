import React from 'react'
import { Link } from 'react-router-dom';

const PostsItem = ({ post, checkItems, onSingleCheck }) => {
    const { id, title, body, userid, fdate } = post;
    return (
        <div className='row'>
            <span className='chk'><input type='checkbox' onChange={(e) => {onSingleCheck(e.target.checked, id)}} checked={checkItems.includes(id) ? true : false}/></span>
            <sapn className='pid'>{id}</sapn>
            <Link to={`/posts/read/${id}`}><sapn className='title ellipsis'>{title}</sapn></Link>
            <sapn className='userid'>{userid}</sapn>
            <sapn className='fdate'>{fdate}</sapn>
        </div>
    )
}

export default PostsItem