import React, { useState } from 'react'
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography } from "@material-ui/core";
import axios from 'axios';

const CustomerDelete = ({ state, id ,callAPI}) => {
    const [open, setOpen] = useState(false);

    const onClickOpen = () => {
        setOpen(true);
    }

    const onClickClose = () => {
        setOpen(false);
    }

    const onChange=async()=>{
        const change=state==='1'?'0':'1';
        await axios.post('/customers/change',{id:id,state:change});
        callAPI('');
        onClickClose();
    }

    return (
        <>
            {state === '1' ?
                <Button variant='contained' color='secondary' onClick={onClickOpen}>
                    삭제
                </Button> :
                <Button variant='contained' color='primary' onClick={onClickOpen}>
                    복원
                </Button>
            }
            <Dialog open={open}>
                <DialogTitle>경고</DialogTitle>
                <DialogContent gutterBottom>
                    <Typography>
                        {state === '1' ? '선택한 고객의 정보를 삭제하시겠습니까?.' : '선택한 고객의 정보를 복원하시겠습니까?.'}
                    </Typography>
                </DialogContent>
                <DialogActions>
                    <Button variant="contained" color={state === '1' ? 'secondary' : 'primary'} onClick={onChange}>
                        {state === '1' ? '삭제' : '복원'}
                    </Button>
                    <Button variant="outlined" onClick={onClickClose}>닫기</Button>
                </DialogActions>
            </Dialog>
        </>
    )
}

export default CustomerDelete
