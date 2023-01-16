import React from 'react'
import { Link, NavLink, Route } from 'react-router-dom'
import Profile from './Profile'

const Profiles = () => {
    const style = {
        backgroundColor : 'aqua',
        color : 'white'
    }
    return (
        <div>
            <h1>프로파일 목록</h1>
            <ul>
                <li><NavLink to = "/profiles/hong" activeStyle={style}>홍길동</NavLink></li>
                <li><Link to = "/profiles/shim">심청이</Link></li>
                <li><Link to = "/profiles/lee">이순신</Link></li>
            </ul>
            <Route path="/profiles/:id" component={Profile} />
            <Route path="/profiles" exact render={() => <h3>사용자를 선택하세요</h3>} />
        </div>
    )
}

export default Profiles