import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Route } from 'react-router-dom';
import Todos from './Todos';
import User from './User';

const AxiosExample = () => {
    const [users, setUsers] = useState();
    const onClick = async() => {
        const res = await axios.get('https://jsonplaceholder.typicode.com/users');
        setUsers(res.data);
    }

    useEffect (() => {
        onClick();
    }, []);

    if(!users) {
        return(<h1>데이터를 불러오는 중</h1>);
    }

    return (
        <div>
            <h1>User List</h1>
            {users.map(user => <User key = {user.id} user = {user}/>)}
            <hr/>
            <Route path="/users/:id" component={Todos} />
            <Route path="/users" exact render = {()=> <h3>사용자를 선택해주세요.</h3>} />
        </div>
    )
}

export default AxiosExample