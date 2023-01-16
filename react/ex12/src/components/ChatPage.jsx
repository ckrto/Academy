import React, { useContext, useEffect, useRef, useState } from 'react'
import { Card, Row } from 'react-bootstrap';
import './Chat.css';
import { getDatabase, ref, set, push, onValue, remove } from "firebase/database";
import { app } from '../firebase'
import moment from 'moment/moment';
import { UserContext } from '../context/UserContext';

const ChatPage = () => {

    const db = getDatabase(app);
    const { loginUser } = useContext(UserContext);
    const [msg, setMsg] = useState("");
    const [messages, setMessages] = useState([]);
    const wrapRef = useRef(null);

    const sendMessage = async (e) => {
        if (e.keyCode === 13) {
            e.preventDefault();
            if (msg === '') {
                alert('보낼 내용을 입력해주세요.');
                return;
            }
            const key = push(ref(db, 'chats/')).key;
            const date = moment(new Date()).format('YYYY-MM-DD HH:mm:ss');
            const uid = loginUser.uid;
            const photo = loginUser.photo;
            await set(ref(db, `chats/${key}`), {
                key: key,
                date: date,
                uid: uid,
                photo: photo,
                text: msg
            });
            setMsg('');
            wrapRef.current.scrollTo(0, wrapRef.current.scrollHeight);
        }
    }

    const getMessage = () => {
        onValue(ref(db, 'chats/'), (snapshot) => {
            let rows = [];
            snapshot.forEach(row => {
                rows.push(row.val());
            });
            setMessages(rows);
        });
        wrapRef.current.scrollTo(0, wrapRef.current.scrollHeight);
    }

    const onClickDelete = async (e) => {
        e.preventDefault();
        if (!window.confirm("채팅데이터를 초기화 하시겠습니까?")) {
            return;
        }
        await remove(ref(db, 'chats/'));
        getMessage();
    }

    useEffect(() => {
        getMessage();
    }, []);

    if (!messages) {
        return <h1>Loading....</h1>
    }

    return (
        <Row className="d-flex justify-content-center">
            <Card style={{ width: "60%", margin: 10, padding: 5 }}>
                <Card.Title className="my-4 text-center">
                    채팅방
                    <a href = "#" onClick={onClickDelete}>초기화</a>
                </Card.Title>
                <div className="wrap" ref={wrapRef} >
                    {messages.map(message =>
                        <div
                            key={message.key}
                            className={message.uid === loginUser.uid ?
                                'chat ch2' :
                                'chat ch1'}>
                            <div><img className="icon" src={message.photo} /></div>
                            <div className="textbox">
                                {message.text}
                            </div>
                        </div>
                    )}
                </div>
                <div>
                    <textarea
                        onKeyDown={sendMessage}
                        value={msg}
                        onChange={(e) => setMsg(e.target.value)}
                        placeholder='Enter for ....' />
                </div>
            </Card>
        </Row>
    )
}

export default ChatPage