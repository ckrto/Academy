import React from 'react'
import WithRouter from './WithRouter';

const data = {
    hong : {name : '홍길동', description : '홍길동전의 주인공'},
    lee : {name : '이순신', description : '최고의 장군'},
    shim : {name : '심청이', description : '심봉사 딸'}
};

const Profile = ( { match } ) => {
    console.log('match', match);
    const {id} = match.params;
    const profile = data[id];

    return (
        <div>
            <h3>아이디 : {id}</h3>
            <h3>이름 : {profile.name}</h3>
            <h3>소개 : {profile.description}</h3>
            <hr/>
            <WithRouter/>
        </div>
    )
}

export default Profile