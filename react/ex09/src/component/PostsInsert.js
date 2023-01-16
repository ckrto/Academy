import axios from 'axios';
import React, { useState } from 'react'
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography } from "@material-ui/core";

const PostsInsert = ({ history }) => {
    const [open, setOpen] = useState(false);

    const onClickOpen = () => {
        setOpen(true);
    }
    const onClickClose = () => {
        setOpen(false);
    }

    const [form, setForm] = useState({
        title: '',
        body: '',
        userid: ''
    });

    const { userid, title, body } = form;

    const onChange = (e) => {
        const newForm = {
            ...form,
            [e.target.name]: e.target.value
        }
        setForm(newForm);
    }

    const onSubmit = async (e) => {
        e.preventDefault();

        await axios.post("/posts/insert", form);
        history.push('/posts');
    }

    const onClickList = () => {
        history.push('/posts');
    }

    return (
        <div className='postForm'>
            <h1>Posts Insert</h1>
            <form className='postForm' onSubmit={onSubmit}>
                <input onChange={onChange} name="userid" placeholder='작성자' value={userid} />
                <input onChange={onChange} name="title" placeholder='제목' value={title} />
                <textarea onChange={onChange} name="body" placeholder='내용' value={body} />
                <div className='buttons'>
                    <Button variant='contained' color='secondary' onClick={onClickOpen}>등록</Button>
                    <Button variant="outlined" onClick={onClickList}>취소</Button>
                </div>
            </form>
            <>
                <Dialog open={open}>
                    <DialogTitle>질의</DialogTitle>
                    <DialogContent gutterBottom>
                        <Typography>게시글을 등록하시겠습니까?</Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button variant='contained' color='secondary' onClick={onSubmit}>예</Button>
                        <Button variant="outlined" onClick={onClickClose}>아니요</Button>
                    </DialogActions>
                </Dialog>
            </>
        </div>
    )
}

export default PostsInsert

