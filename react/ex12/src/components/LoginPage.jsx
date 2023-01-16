import axios from 'axios';
import React, { useState } from 'react'
import { Button, Card, Form, Row } from 'react-bootstrap'
import { Link } from 'react-router-dom'

const LoginPage = ({ history }) => {

    const [form, setForm] = useState({
        uid: '',
        upass: ''
    });

    const onChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (form.uid === '' || form.upass === '') {
            alert("빈칸없이 작성해주세요");
            return;
        }
        const result = await axios.post('/api/user/login', form);
        if (result.data === 0) {
            alert('아이디가 없습니다.')
        } else if (result.data === 2) {
            alert('비밀번호가 다릅니다.');
        } else {
            alert('로그인 성공');
            sessionStorage.setItem("uid", form.uid);
            history.push('/');
        }
    }

    return (
        <Row className='d-flex justify-content-center my-5'>
            <Card style={{ width: '30rem' }} className="p-3">
                <Form onSubmit={onSubmit}>
                    <Form.Control placeholder='아이디' className='my-3' name="uid" value={form.uid} onChange={onChange} />
                    <Form.Control placeholder='비밀번호' className='my-3' name="upass" type='password' value={form.upass} onChange={onChange} />
                    <Button type="submit" style={{ width: '100%' }}>로그인</Button>
                </Form>
                <div className='my-3'>
                    <Link to="/user/insert">회원가입</Link>
                </div>
            </Card>
        </Row>
    )
}

export default LoginPage