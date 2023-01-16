import React, { useEffect, useRef } from 'react'

const UseRefSample1 = () => {
    const refId = useRef();

    useEffect(() => {
        console.log('렌더링....');
        refId.current.focus();
    });

    const onClickLogin = () => {
        alert(`${refId.current.value}님이 로그인하셨습니다.`);
        refId.current.focus();
        refId.current.value = '';
    }
    
    const onKeyDownId = (e) => {
        if(e.key === 'Enter') {
            onClickLogin();
        }
    }

    return (
        <div>
            <input onKeyDown={onKeyDownId} ref = {refId} type = "text" placeholder='User Id' />
            <button onClick={onClickLogin}>로그인</button>
        </div>
    )
}

export default UseRefSample1