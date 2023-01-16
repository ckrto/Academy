import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react'

const PostsUpdate = ({ match, history }) => {
    const id = match.params.id;

    const [form, setForm] = useState({
        id : id,
        title: '',
        body: ''
    });

    const refTitle = useRef();

    const { title, body } = form;

    const onChange = (e) => {
        const newForm = {
            ...form,
            [e.target.name]: e.target.value
        }
        setForm(newForm);
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (title === '') {
            alert('제목을 입력해주세요');
            refTitle.current.focus();
            return;
        }
        if(!window.confirm(`${id}번 게시글을 수정하시겠습니까?`)) {
            return;
        } else {
            await axios.post('/posts/update', form);
            alert('수정되었습니다');
            history.go(-2);
        }
    }

    const callAPI = async () => {
        const result = await axios.get(`/posts/${id}`);
        setForm(result.data);
    }

    const onClickDelete = async() => {
        if(!window.confirm('삭제하시겠습니까?')) {
            return;
        } else {
            await axios.post(`/posts/delete/${id}`);
            alert('삭제되었습니다.');
            history.go(-2);   
        }
    }

    useEffect(() => {
        callAPI();
        const block = history.block('정말로 뒤로 가시겠습니까?');
        return () => {
            block();
        }
    }, []);

    if (title === '') {
        return(<h1>로딩중</h1>);
    }

    return (
        <div className='postInsert'>
            <h1>Posts Insert</h1>
            <form className='postForm' onSubmit={onSubmit}>
                <input ref={refTitle} onChange={onChange} name="title" placeholder='제목' value={title} />
                <textarea onChange={onChange} name="body" placeholder='내용' value={body} />
                <div className='buttons'>
                    <button type='submit'>수정</button>
                    <button type='button' onClick={onClickDelete}>삭제</button>
                    <button type='reset'>취소</button>
                </div>
            </form>
        </div>
    )
}

export default PostsUpdate