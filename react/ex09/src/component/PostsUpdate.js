import { Button } from '@material-ui/core';
import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const PostsUpdate = ({ match, history }) => {
    const id = match.params.id;

    const [form, setForm] = useState({
        userid: '',
        title: '',
        body: '',
        fdate: ''
    });

    const onChange = (e) => {
        const newForm = {
            ...form,
            [e.target.name]: e.target.value
        }
        setForm(newForm);
    }

    const { userid, title, body, fdate } = form;

    const callAPI = async () => {
        const result = await axios.get(`/posts/update`, form);
        setForm(result.data);
    }

    const onClickList = () => {
        history.push('/posts');
    }

    useEffect(() => {
        callAPI();
    });

    return (
        <div>
            <h1>Post Read</h1>
            <form>
                <div>
                    <span>{userid} ({fdate})</span>
                </div>
                <input value={title} />
                <textarea value={body} />
                <Button variant='contained' color='secondary' className='menutitle2'><Link to={`/posts/update/${id}`}>수정</Link></Button>
                <Button variant="outlined" onClick={onClickList}>취소</Button>
            </form>
        </div>
    )
}

export default PostsUpdate