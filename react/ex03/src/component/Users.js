import React, { useEffect, useState } from 'react'
import User from './User';

const Users = () => {
    const [users, setUsers] = useState();

    const callAPI = () => {
        fetch('https://jsonplaceholder.typicode.com/users')
        .then(response => response.json())
        .then(json => {
            const newUsers = json;
            setUsers(newUsers);
        }); 
    }

    useEffect(() => {
        callAPI();
    },[]);

    if(!users) {
        return('<h2>데이터를 불러오는중</h2>');
    }

    return (
        <div>
            <h1>사용자 목록</h1>
            {users.map(user => <User key = {user.id} user = {user}/>)}
        </div>
    )
}

export default Users