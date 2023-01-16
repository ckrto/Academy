import React from 'react'

const User = ({user}) => {
    const {id, name, username, email, address, phone} = user;

    return (
        <div style={{textAlign:'left', width:500, margin :'0px auto'}}>
            <div><b>UserInfo : </b>[{id}] {name}({email}-{username})</div>
            <div><b>Phone number : </b>{phone}</div>
            <div style={{borderBottom : '1px dotted gray', margin : '20px 0px'}}>
                <b>Address : </b>{address.street}
                {address.suite}
                {address.city}
                <br/>
                <button>Todo List</button>
                <button>Post List</button>
            </div>
        </div>
    )
}

export default User