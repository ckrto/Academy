import React, { useEffect, useRef, useState } from 'react'

const Info = () => {
    const [name, setName] = useState('');
    const [nickname, setNickname] = useState('');
    const refNumber = useRef(0);

    useEffect(() => {
        refNumber.current = refNumber.current + 1;
        console.log('render : ' + refNumber.current);

        return () => {
            
        }

    }, [name, nickname]);

    return (
        <div>
            <input onChange = {(e) => setName(e.target.value)} value = {name} type = "text" placeholder='이름'/>
            <input onChange = {(e) => setNickname(e.target.value)} value = {nickname} type = "text" placeholder='별명'/>
            <hr/>
            <h3>이름 : {name}</h3>
            <h3>별명 : {nickname}</h3>
        </div>
    )
}

export default Info