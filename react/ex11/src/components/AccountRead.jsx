import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import TradeItem from './TradeItem';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import { AccountContext } from '../context/AccountContext';

const AccountRead = ({ match }) => {
    const {accounts}=useContext(AccountContext);

    const ano = match.params.ano;

    const [show, setShow] = useState(false);
    const [account, setAccount] = useState('');
    const [trades, setTrades] = useState([]);
    const [trade, setTrade] = useState({
        ano: ano,
        tno: "",
        amount: ""
    });

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const { aname, openDate, fbalance, balance } = account;

    const callAPIAccount = async () => {
        const result = await axios.get(`/api/account/read/${ano}`);
        setAccount(result.data);
    }

    const callTrades = async () => {
        const result = await axios.get(`/api/trade/list/${ano}`);
        setTrades(result.data);
    }

    const onChange = (e) => {
        setTrade({
            ...trade,
            [e.target.name]: e.target.value
        })
    }


    const onSubmit = async () => {
        if (trade.amount > balance) {
            alert("잔액이 부족합니다.");
            return;
        }
        if (!window.confirm(`${trade.ano}가 ${trade.tno}에게 ${trade.amount}를 이체하시겠습니까?`)) return;

        await axios.post(`/api/trade/`, trade);
        handleClose();
        setTrade({
            ano: ano,
            tno: null,
            amount: ''
        });
        callTrades();
        callAPIAccount();
    }

    useEffect(() => {
        callAPIAccount();
        callTrades();
    }, []);




    if (!account || !trades || !accounts) return <h1>Loading....</h1>

    return (
        <>
            <Card className='my-2 p-3'>
                <Row>
                    <Col>계좌번호 : {ano}</Col>
                    <Col>계좌주 : {aname}</Col>
                    <Col xs={3}>개설일 : {openDate}</Col>
                    <Col>잔액 : {fbalance}원</Col>
                </Row>
            </Card>
            <Button variant="primary" onClick={handleShow}>
                계좌이체
            </Button>
            <Table className='my-3'>
                <thead>
                    <th>수신 계좌번호</th>
                    <th>날짜</th>
                    <th>입금/출금</th>
                    <th>입출금액</th>
                </thead>
                <tbody>
                    {trades.map(trade =>
                        <TradeItem key={trade.id} trade={trade} />
                    )}
                </tbody>
            </Table>

            {/*이체 modal*/}
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>계좌이체</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form.Select className='my-3' onChange={onChange} value={trade.tno} name="tno">
                        <option>계좌를 선택하세요.</option>
                        {accounts.map(account =>
                            <option value={account.ano} key={account.ano}>{account.ano} ({account.aname})</option>
                        )}
                    </Form.Select>
                    <Form.Control name="amount" placeholder='이체금액' type="number" min={1} step={100} value={trade.amount} onChange={onChange} />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={onSubmit}>
                        이체
                    </Button>
                    <Button variant="secondary" onClick={handleClose}>
                        취소
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default AccountRead