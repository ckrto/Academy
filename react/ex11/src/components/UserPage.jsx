import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Header from './Header'
import Pagination from 'react-js-pagination';
import './Pagination.css'
import Table from 'react-bootstrap/Table';

const UserPage = () => {
    const [users,setUsers]=useState([]);
    const [page,setPage]=useState(1);

    const callUsers=async()=>{
        const result=await axios.get(`/api/user/list?page=${page}`);
        setUsers(result.data);
    }
    
    useEffect(()=>{
        callUsers();
    },[page]);


    if(!users) return <h1>Loading.....</h1>

    return (
        <div>
            <Table>
                <thead>
                    <tr>
                        <td>아이디</td>
                        <td>이미지</td>
                        <td>이름</td>
                        <td>가입일</td>
                    </tr>
                </thead>
                <tbody>
                    {users.map(user=>
                        <tr key={user.uid}>
                            <td>{user.uid}</td>
                            <td>
                                {user.photo?
                                <img src={user.photo}  width={100}/>:
                                <img src="/b1.jpg" width={100}/>
                                }
                            </td>
                            <td>{user.uname}</td>
                            <td>{user.joinDate}</td>
                        </tr>
                    )}
                </tbody>
            </Table>
            <Pagination
                activePage={page}
                itemsCountPerPage={5}
                totalItemsCount={18}
                pageRangeDisplayed={20}
                prevPageText={"‹"}
                nextPageText={"›"}
                onChange={(e)=>setPage(e)} />
        </div>
    )
}

export default UserPage