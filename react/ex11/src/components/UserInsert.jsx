import axios from 'axios';
import React, { useState } from 'react'
import { Alert, Button, Card, Form } from 'react-bootstrap'

const UserInsert = ({history}) => {
    const [message, setMessage] = useState("");
    const [form, setForm] = useState({
        uid: '',
        upass: '',
        uname: '',
        file: null
    });

    const onChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        })
    }

    const onChangeFile = (e) => {
        setForm({
            ...form,
            file: e.target.files[0]
        })
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if(form.uid==="" || form.upass ==="" || form.uname===""){
            setMessage("아이디, 비밀번호 및 이름을 입력해주세요.");
            return;
        }

        if(!window.confirm("새로운 사용자를 등록하시겠습니까?")) return;

        const formData=new FormData();
        formData.append("file",form.file);
        formData.append("uid",form.uid);
        formData.append("upass",form.upass);
        formData.append("uname",form.uname);
        await axios.post(`/api/user/insert`,formData);
        
        alert("새로운 사용자가 등록되었습니다.");
        history.push("/login");
    }



    return (
        <div>
            <Card className='p-3'>
                <Form onSubmit={onSubmit}>
                    <Form.Control placeholder="아이디" name="uid" className='my-3' value={form.uid} onChange={onChange} />
                    <Form.Control placeholder="비밀번호" name="upass" className='my-3' type='password' value={form.upass} onChange={onChange} />
                    <Form.Control placeholder="이름" name="uname" className='my-3' value={form.uname} onChange={onChange} />
                    <Form.Control className='my-3' type="file" onChange={onChangeFile} />
                    {message &&
                        <Alert className='my-3' style={{ textAlign: "center" }}>{message}</Alert>
                    }
                    <Button style={{ width: "100%" }} type="submit">회원가입</Button>
                </Form>
            </Card>
        </div>
    )
}

export default UserInsert