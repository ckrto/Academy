import axios from 'axios';
import React, { useState } from 'react'
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';


const CustomerInsert = ({ callAPI }) => {
    const [form, setForm] = useState({
        name: '무기명',
        birthday: '2022-09-28',
        gender: '남',
        job: '의사',
        file: null,
        fileName: ''
    });

    const { name, birthday, gender, job, file, fileName } = form;

    const onChange = (e) => {
        const newForm = {
            ...form,
            [e.target.name]: e.target.value
        }
        setForm(newForm);
    }

    const onChangeFile = (e) => {
        const newForm = {
            ...form,
            file: e.target.files[0],
            fileName: e.target.value
        }
        setForm(newForm);
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        //alert(JSON.stringify(form,null,4));
        if (!window.confirm(`${name}고객을 등록하시겠습니까?`)) return;
        const formData = new FormData();
        formData.append("image", file);
        formData.append("name", name);
        formData.append("birthday", birthday);
        formData.append("gender", gender);
        formData.append("job", job);
        const config = {
            headers: { 'content-type': 'multipart/form-data' }
        };
        await axios.post("/customers/insert", formData, config);
        callAPI('');
        setForm({
            name: '아무개',
            birthday: '2022-09-28',
            gender: '여',
            job: '기타',
            file: null,
            fileName: ''
        });
        handleClose();
    }

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <>
            <Button variant="contained" color="primary" onClick={handleClickOpen}>
                고객등록
            </Button>
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description">
                <DialogTitle id="alert-dialog-title">고객추가</DialogTitle>
                <DialogContent>
                    <form onSubmit={onSubmit}>
                        <input type={'file'} name="file" value={fileName} onChange={onChangeFile} style={{display:'none'}} id="file-button"/>
                        <label htmlFor='file-button'>
                            <Button variant="contained"  name="file" component="span" color={fileName===''? "":"primary"}>
                                {fileName===''? '프로필이미지선택':fileName}
                            </Button>
                        </label>
                        <br /><br />
                        <TextField label="성명 : " type="text" name="name" value={name} onChange={onChange} /><br /><br />
                        <TextField label="생년월일 : " type="date" name="birthday" value={birthday} onChange={onChange} /><br /><br />
                        <TextField label="성별 : " type="text" name="gender" value={gender} onChange={onChange} /><br /><br />
                        <TextField label="직업 : " type="text" name="job" value={job} onChange={onChange} />
                        <br />
                    </form>
                </DialogContent>
                <DialogActions>
                    <Button variant='contained' onClick={onSubmit} color="primary">
                        추가
                    </Button>
                    <Button variant='outlined' onClick={handleClose} color="primary" autoFocus>
                        닫기
                    </Button>
                </DialogActions>
            </Dialog>

        </>
    )
}

export default CustomerInsert

