import React, { useEffect, useRef, useState } from 'react'
import User from './User';

const Users = () => {
    const [users, setUsers] = useState();
    const [page, setPage] = useState(1);
    const last = useRef(1);

    const callAPI = () => {
        fetch('https://jsonplaceholder.typicode.com/users')
        .then(response => response.json())
        .then(json => {
            // console.log(json);
            let start = (page - 1) * 10 + 1;
            let end = (page * 10);
            last.current = Math.ceil(json.length/10);

            const newUsers = json.filter(user => user.id >= start && user.id <= end);
            setUsers(newUsers);
        });
    }
    
    useEffect(() => {
        callAPI();
    }, []);

    if(!users) {
        return(<h1>데이터를 불러오는 중</h1>);
    }

    return (
        <div>
            <h1>User List</h1>
            {users.map(user => <User key = {user.id} user = {user}/>)}
        <div style={{marginTop : '20px'}}>
            <button onClick = {() => setPage(1)}>처음으로</button> 
            <button disabled = {page === 1 && true} onClick={() => setPage(page - 1)}>이전</button>
            <span> {page} / {last.current} </span>
            <button disabled = {page === last.current && true} onClick={() => setPage(page + 1)}>다음</button>
            <button onClick = {() => setPage(last.current)}>마지막으로</button>
        </div>
        </div>
    )
}

export default Users