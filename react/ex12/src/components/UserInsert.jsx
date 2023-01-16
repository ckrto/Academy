import axios from 'axios';
import React, { useState } from 'react'
import { Button, Card, Form, Row } from 'react-bootstrap'

const UserInsert = ({ history }) => {
    const [form, setForm] = useState({
        uid: '',
        upass: '',
        uname: '',
        file: null
    });

    const { uid, upass, uname, file } = form;

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
        });
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (uid === "" || upass === "" || uname === "") {
            alert("아이디, 비밀번호 및 이름을 입력해주세요.");
            return;
        }
        if (!window.confirm("새로운 회원을 등록하시겠습니까?")) return;
        const formData = new FormData();
        formData.append("uid", uid);
        formData.append("upass", upass);
        formData.append("uname", uname);
        formData.append("file", file);

        await axios.post(`/api/user/insert`, formData);
        alert("새로운 회원이 등록되었습니다.");
        history.push("/login");
    }

    return (
        <Row className='d-flex justify-content-center my-5'>
            <Card className='p-3' style={{ width: "30rem" }}>
                <Form onSubmit={onSubmit}>
                    <Form.Control placeholder='아이디' className='my-3' name="uid" value={uid} onChange={onChange} />
                    <Form.Control placeholder='비밀번호' className='my-3' name="upass" type='password' value={upass} onChange={onChange} />
                    <Form.Control placeholder='이름' className='my-3' name="uname" value={uname} onChange={onChange} />
                    <Form.Control className='mb-2' type='file' onChange={onChangeFile} />
                    <Button type='submit' className='my-2' style={{ width: "100%" }}>회원가입</Button>
                </Form>
            </Card>
        </Row>
    )
}

export default UserInsert