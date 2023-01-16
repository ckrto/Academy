import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import { Alert, Button, Card, Form } from 'react-bootstrap'
import { UserContext } from '../context/UserContext';
import { Link } from 'react-router-dom';
import { ThemeContext } from '../context/ThemeContext';

const LoginPage = ({history,location}) => {
    const {setLoginUser}=useContext(UserContext);
    const {setBackground} = useContext(ThemeContext);

    const [message,setMessage]=useState('');
    const [form,setForm]=useState({
        uid:'',
        upass:''
    });


    const onChange=(e)=>{
        setForm({
            ...form,
            [e.target.name]:e.target.value
        });
    }

    useEffect(() => {
        setBackground('danger');
    },[]);

    const onSubmit=async(e)=>{
        e.preventDefault();
        if(form.uid==='' || form.upass===''){
            setMessage("아이디 및 비밀번호를 입력하세요.");
            return;
        }
        const result=await axios.post(`/api/user/login`,{uid:form.uid,upass:form.upass});
        
        const readData=result.data;
        if(readData===0){
            setMessage("존재하지 않는 사용자입니다.");
            return;
        }
        else if(readData===1){
            setMessage("비밀번호가 일치하지 않습니다.");
            return;
        }
        else{
            setMessage("");
            sessionStorage.setItem("uid",form.uid);
            const result1 = await axios.get(`/api/user/read/${form.uid}`);
            setLoginUser(result1.data);
            history.go(-1);
        }
    }

    return (
        <div>
            <Card className='p-3'>
                <Form onSubmit={onSubmit}>
                    <Form.Control placeholder='아이디' name="uid" className='my-2' value={form.uid} onChange={onChange}/>
                    <Form.Control placeholder='비밀번호' name="upass" type="password" className='my-2' value={form.upass} onChange={onChange}/>
                    <Button style={{width:"100%"}} type="submit">로그인</Button>
                </Form>
                {message &&
                    <Alert className='my-3' style={{textAlign:"center"}}>{message}</Alert>
                }
                <hr/>
                <Link to="/user/insert"><Button style={{width:"100%"}} >회원가입</Button></Link>
            </Card>
        </div>
    )
}

export default LoginPage
