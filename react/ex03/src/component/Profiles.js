import React from 'react'
import { Link ,Route } from 'react-router-dom';
import Profile from './Profile';

const Profiles = () => {
    return (
        <div>
            <div className='menu'>
                <span><Link to = "/profiles/shim">심청이</Link></span>
                <span><Link to = "/profiles/hong">홍길동</Link></span>
                <span><Link to = "/profiles/lee">이순신</Link></span>
            </div>
            <Route path="/profiles/:id" component={Profile}/>
            <Route path="/profiles"exact = {true} render = {() => <h3>사용자를 선택해주세요</h3>}/>
        </div>
    )
}

export default Profiles