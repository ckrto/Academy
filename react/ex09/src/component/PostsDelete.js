import React, { useState } from 'react'
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography } from "@material-ui/core";
import axios from 'axios';

const PostsDelete = ({ id, callAPI }) => {
    const [open, setOpen] = useState(false);

    const onClickOpen = () => {
        setOpen(true);
    }

    const onClickDelete = async () => {
        await axios.post(`/posts/delete/${id}`);
        <>
            <DialogTitle>삭제</DialogTitle>
            <DialogContent gutterBottom>
                <Typography>삭제완료</Typography>
            </DialogContent>
        </>
        callAPI('');
        onClickClose();
    }

    const onClickClose = () => {
        setOpen(false);
    }

    return (
        <>
            <Button variant='contained' color='secondary' onClick={onClickOpen}>삭제</Button>
            <Dialog open={open}>
                <DialogTitle>경고</DialogTitle>
                <DialogContent gutterBottom>
                    <Typography>{`${id} 선택한 게시글을 삭제하시겠습니까?`}</Typography>
                </DialogContent>
                <DialogActions>
                    <Button variant='contained' color='secondary' onClick={onClickDelete}>삭제</Button>
                    <Button variant="outlined" onClick={onClickClose}>닫기</Button>
                </DialogActions>
            </Dialog>
        </>
    )
}

export default PostsDelete
