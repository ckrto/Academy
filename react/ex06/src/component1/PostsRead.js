import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, withRouter } from 'react-router-dom';

const PostsRead = ({ match, history }) => {
    const id = match.params.id;

    const [form, setForm] = useState({
        title: '',
        body: '',
        fdate : '',
        userid : ''
    });

    const { title, body, fdate, userid } = form;

    const callAPI = async () => {
        const result = await axios.get(`/posts/read/${id}`);
        setForm(result.data);
    }

    useEffect(() => {
        callAPI();
    }, []);

    return (
        <div>
            <h1>Posts Read</h1>
            <form className='postForm'>
                <div style={{textAlign : 'right', marginBottom : '20px'}}>
                    <span>{userid} ({fdate})</span>
                </div>
                <input name="title" value={title} readOnly />
                <textarea name="body" value={body} readOnly />
                <div className='buttons'>
                    <Link to={`/posts/update/${id}`}><button>수정</button></Link>
                    <button type='button' onClick={() => history.goBack()}>목록</button>
                </div>
            </form>
        </div>
    )
}

export default PostsRead