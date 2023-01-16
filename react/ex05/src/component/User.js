import React from 'react'
import { NavLink, Route } from 'react-router-dom';
import Todos from './Todos';

const User = ( {user} ) => {
    const {id, name, username, email, address} = user;
    const style = {backgroundColor : 'aqua', color : 'white'};

    return (
        <div>
            <ul>
                <li><NavLink activeStyle={style} to = {`/users/${id}`}>{id} {name}({username})</NavLink></li>
            </ul>
        </div>
    )
}

export default User