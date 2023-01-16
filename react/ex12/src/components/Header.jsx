import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { withRouter } from 'react-router-dom';
import { UserContext } from '../context/UserContext';
import { Badge } from 'react-bootstrap';
import { app } from '../firebase';
import { getDatabase, ref, onChildChanged } from "firebase/database";

const Header = ({ history, location }) => {
    const path = location.pathname;
    const [count, setCount] = useState(
        !sessionStorage.getItem('count') ? 0 :
            parseInt(sessionStorage.getItem('count'))
    );
    const { loginUser, setLoginUser } = useContext(UserContext);
    const db = getDatabase(app);

    const onClick = (e) => {
        e.preventDefault();
        const href = e.target.getAttribute('href');
        history.push(href);
    }

    const onClickLogOut = (e) => {
        e.preventDefault();
        sessionStorage.removeItem('uid');
        history.push('/');
    }

    const getLoginUser = async () => {
        const result = await axios.get(`/api/user/read/${sessionStorage.getItem('uid')}`);
        setLoginUser(result.data);
    }

    useEffect(() => {
        if (sessionStorage.getItem("uid")) {
            getLoginUser();
        }
    }, [sessionStorage.getItem("uid")]);

    useEffect(() => {
        if (path !== '/chat') {
            onChildChanged(ref(db, '/'), (data) => {
                setCount(count + 1);
            });
        } else {
            setCount(0);
        }
        sessionStorage.setItem('count', count);
    }, [count, path]);

    return (
        <>
            <Navbar bg="primary" variant='dark'>
                <Container>
                    <Navbar.Brand href="/" onClick={onClick}>Home</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/movie" onClick={onClick}>영화예약</Nav.Link>
                        <Nav.Link href="/chat" onClick={onClick}>
                            채팅
                            {count > 0 && <Badge bg="danger">{count}</Badge>}
                        </Nav.Link>
                        <Nav.Link href="/shop" onClick={onClick}>식품관리</Nav.Link>
                    </Nav>
                    <Nav>
                        {sessionStorage.getItem('uid') ?
                            <>
                                <Nav.Link href={`/user/read/${loginUser.uid}`} onClick={onClick}>{loginUser.uname}</Nav.Link>
                                <Nav.Link href="#" onClick={onClickLogOut}>로그아웃</Nav.Link>
                            </>
                            :
                            <Nav.Link href="/login" onClick={onClick}>로그인</Nav.Link>
                        }
                    </Nav>
                </Container>
            </Navbar>
        </>
    );
}

export default withRouter(Header)