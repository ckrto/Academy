import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, Form, Modal } from 'react-bootstrap';
import { UserContext } from '../context/UserContext';

const UserRead = ({ match, history }) => {
    const {loginUser,setLoginUser}=useContext(UserContext);

    const uid = match.params.uid;

    const [image, setImage] = useState('/b1.jpg');
    const [form, setForm] = useState({
        uid: uid,
        uname: '',
        file: null,
        photo: ''
    });


    const onChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    const onChangeFile = (e) => {
        setForm({
            ...form,
            file: e.target.files[0]
        })
        setImage(URL.createObjectURL(e.target.files[0]));
    }

    const callUser = async () => {
        const result = await axios.get(`/api/user/read/${uid}`);
        setForm(result.data);
        if (result.data) setImage(result.data.photo);
        else setImage('/b1.jpg');

    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (!window.confirm("수정한 내용을 변경하시겠습니까?")) return;
        const formData = new FormData();
        formData.append("file", form.file);
        formData.append("uid", uid);
        formData.append("uname", form.uname);
        formData.append("photo", form.photo);
        await axios.post(`/api/user/update`, formData);
        alert("회원정보 수정완료.");
        setLoginUser({
            ...loginUser,
            uname:form.uname

        })
        history.push('/user');
    }

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const[pass1,setPass1]=useState('');
    const[pass2,setPass2]=useState('');

    const onClickChange=async()=>{
        if(pass1 ==="" || pass2==="" || pass1!==pass2){
            alert("비밀번호 및 확인을 입력하세요");
            return;
        }
        await axios.post(`/api/user/update/password`,{uid:uid,upass:pass1});
        alert("비밀번호 변경 완료");
        handleClose();
        sessionStorage.removeItem("uid");
        history.push("/login");
    }

    useEffect(() => {
        callUser();
    }, [uid]);

    if (!form) return <h1>Loading....</h1>

    return (
        <div>
            <Card className='p-3'>
                <Form onSubmit={onSubmit}>
                    <Form.Control value={uid} disabled={true} className="my-3" name="uid" />
                    <Button className='m-1' onClick={handleShow}>비밀번호변경</Button>
                    <Form.Control value={form.uname} className="my-3" name="uname" onChange={onChange} />
                    <img src={image} width={200} />
                    <Form.Control type='file' className="my-3" onChange={onChangeFile} />
                    <Button className='m-1' type='submit'>수정</Button>
                </Form>
            </Card>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>비밀번호 변경</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Control type='password' placeholder='변경할 비밀번호' className="my-3" value={pass1} onChange={(e)=>setPass1(e.target.value)}/>
                    <Form.Control type='password' placeholder='비밀번호 확인' className="my-3" value={pass2} onChange={(e)=>setPass2(e.target.value)}/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={onClickChange}>
                        비밀번호 변경
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    )
}

export default UserRead