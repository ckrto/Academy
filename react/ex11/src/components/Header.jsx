import React, { useContext } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { withRouter } from 'react-router-dom';
import { ThemeContext } from '../context/ThemeContext';
import { UserContext } from '../context/UserContext';

const Header = ({history}) => {

  const {loginUser}=useContext(UserContext);
  const {background} = useContext(ThemeContext);

  const onClickLogout=(e)=>{
    e.preventDefault();
    sessionStorage.removeItem("uid");
    history.push("/");
  }

  const onClick = (e) => {
    e.preventDefault();
    const href = e.target.getAttribute('href');
    history.push(href);
  }

  return (
    <>
      <br />
      <Navbar bg={background} variant="dark">
        <Container>
          <Navbar.Brand href="/" onClick={onClick}>Home</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="/" onClick={onClick}>Home</Nav.Link>
            <Nav.Link href="/account" onClick={onClick}>계좌관리</Nav.Link>
            <Nav.Link href="/shop" onClick={onClick}>상품관리</Nav.Link>
            <Nav.Link href="/user" onClick={onClick}>사용자관리</Nav.Link>
          </Nav>
          <Nav>
            {sessionStorage.getItem("uid")?
            <>
              <Nav.Link href={`/user/read/${sessionStorage.getItem("uid")}`} onClick={onClick}>{loginUser.uname}</Nav.Link>
              <Nav.Link href="#" onClick={onClickLogout}> 로그아웃</Nav.Link>
            </>
            
            :
            <Nav.Link href="/login" onClick={onClick}>로그인</Nav.Link>
            }
          </Nav>
        </Container>
      </Navbar>
      <br />
    </>
  )
}

export default withRouter(Header)