import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link, Route, Switch } from 'react-router-dom'
import AccountList from './AccountList'
import AccountRead from './AccountRead'
import Header from './Header'
import {AccountContext} from '../context/AccountContext'

const AccountPage = () => {
    const [accounts, setAccounts] = useState([]);

    const callAPIAccounts = async () => {
        const result = await axios.get(`/api/account/list`);
        setAccounts(result.data);
    }
    useEffect(() => {
        callAPIAccounts();
    }, []);
    if (!accounts) return <h1>Loading...</h1>

    return (
        <AccountContext.Provider value={{accounts}}>
        <div>
            <div>
                <Link to="/account/list">목록</Link>
            </div>
            <Switch>
                <Route path="/account/list" component={AccountList} />
                <Route path="/account/read/:ano" component={AccountRead}/>
            </Switch>
        </div>
        </AccountContext.Provider>
    )
}

export default AccountPage