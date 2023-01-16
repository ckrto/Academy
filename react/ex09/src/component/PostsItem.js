import React from 'react'
import { TableCell, TableRow } from '@material-ui/core';
import { Link } from 'react-router-dom';
import PostsDelete from './PostsDelete';

const PostsItem = ({ posts, callAPI }) => {
    const { id, userid, title, fdate } = posts;

    return (
        <TableRow>
            <TableCell>{id}</TableCell>
            <TableCell>{userid}</TableCell>
            <TableCell className='menutitle'><Link to={`/posts/read/${id}`}>{title}</Link></TableCell>
            <TableCell>{fdate}</TableCell>
            <TableCell><PostsDelete id={id} callAPI={callAPI}/></TableCell>
        </TableRow>
    )
}

export default PostsItem