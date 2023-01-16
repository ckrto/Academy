import React, { useState } from 'react'

const Counter = () => {
    const [number, setNumber] = useState(100);
    const onClickIncrese = () => {
        setNumber(number + 1);
        alert(`현재값 : ${number} 입니다.`);
    }
    return (
        <div>
            <h1>값 : {number}</h1>
            <button onClick={onClickIncrese}>1씩증가</button>
            <button onClick={() => setNumber(number - 1)}>1씩감소</button>
        </div>
    )
}

export default Counter
