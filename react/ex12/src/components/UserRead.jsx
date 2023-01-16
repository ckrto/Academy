import React, { useContext } from 'react'
import { Card, Row } from 'react-bootstrap'
import { UserContext } from '../context/UserContext'

const UserRead = () => {
    const { loginUser } = useContext(UserContext);

    return (
        <Row className='d-flex justify-content-center my-5'>
            <Card style={{ width: "30rem", margin: 10, padding: 5 }}>
                <Card.Img src={loginUser.photo} />
                <Card.Body>
                    <Card.Title>{loginUser.uname}({loginUser.uid})</Card.Title>
                </Card.Body>
            </Card>
        </Row>
    )
}

export default UserRead