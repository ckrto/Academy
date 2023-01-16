import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import AccountItem from './AccountItem';
import Table from 'react-bootstrap/Table';
import { AccountContext } from '../context/AccountContext';
const AccountList = () => {
    const {accounts}=useContext(AccountContext);

    return (
            <Table className='my-2'>
                <thead>
                    <th>계좌번호</th>
                    <th>계좌주명</th>
                    <th>계좌개설일</th>
                    <th>잔액</th>
                    <th>내역확인</th>
                </thead>
                <tbody>
                    {accounts.map(account =>
                        <AccountItem key={account.ano} account={account} />
                    )}
                </tbody>
            </Table>
    )
}

export default AccountList
