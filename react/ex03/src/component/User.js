import React, { useState } from 'react'
import Todos from './Todos';
import Posts from './Posts';

const User = ({user}) => {
    const {id, name, username, email, address, phone} = user;
    const [showTodo, setShowTodo] = useState(false);
    const [showPost, setShowPost] = useState(false);

    return (
        <div style={{margin : '0px auto', padding : 20}}>
            <div>
                <span>[{id}] </span>
                <span>{name}({username})</span>
            </div>
            <div>
                <span>{email}</span>
                <span>{phone}</span>
            </div>
            <div>
                <span>{address.city}</span>
                <span>{address.suite}</span>
                <span>{address.street}</span>
            </div>
            <div>
                <button onClick = {() => setShowTodo(!showTodo)}>Todo List</button>&nbsp;&nbsp;
                <button onClick = {() => setShowPost(!showPost)}>Post List</button>
            </div>
            <hr/>
            {showTodo && <Todos id = {id} name = {name}/>}
            {showPost && <Posts id = {id} name = {name}/>}

        </div>  
    )
}

export default User