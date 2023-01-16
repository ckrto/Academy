import axios from 'axios';
import React, { useRef, useState } from 'react'
import { withRouter } from 'react-router-dom';

const PostsInsert = ({history}) => {
    const [form, setForm] = useState({
        title: '',
        body: '',
        userid: 'red',
    });

    const refTitle = useRef();

    const { title, body, userid } = form;
    const onChange = (e) => {
        const newForm = {
            ...form,
            [e.target.name]: e.target.value
        }
        setForm(newForm);
    }

    const onSubmit = async(e) => {
        e.preventDefault();
        if (title === '') {
            alert('제목을 입력해주세요');
            refTitle.current.focus();
            return;
        }
        // alert(JSON.stringify(form, null, 4));
        await axios.post('/posts/insert', form);
        history.push('/posts?page=1');
    }

    return (
        <div className='postInsert'>
            <h1>Posts Insert</h1>
            <form className='postForm' onSubmit={onSubmit}>
                <input ref={refTitle} onChange={onChange} name="title" placeholder='제목' value={title} />
                <textarea onChange={onChange} name="body" placeholder='내용' value={body} />
                <div className='buttons'>
                    <button type='submit'>등록</button>
                    <button type='reset'>취소</button>
                </div>
            </form>
        </div>
    )
}

export default withRouter(PostsInsert)