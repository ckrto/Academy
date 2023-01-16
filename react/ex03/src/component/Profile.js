import React, { useEffect } from 'react'

const data = {
    shim : {
        name : '심청이',
        description : '리액트를 ㅈ아하는 개발자'
    },
    hong : {
        name : '홍길동',
        description : '고전 소설 홍길동전의 주인공'
    },
    lee : {
        name : '이순신',
        description : '역대 최고의 장군'
    },
}

const Profile = ({match}) => {

    useEffect(() => {
        console.log(match);
    }, []);

    const {id} = match.params;
    const profile = data[id];

    if(!profile) {
        return(<h3>해당 사용자가 없습니다.</h3>);
    }

    return (
        <div>
            <h3>이름 : {profile.name}</h3>
            <h3>소개 : {profile.description}</h3>
        </div>
    )
}

export default Profile