import React, { useState } from 'react'

const Say = () => {
    const [message, setMessage] = useState('');
    const [textColor, setTextColor] = useState('black');
    return (
        <div>
            <button onClick = {() => setMessage('입장합니다.')}>입장</button>
            <button onClick={() => setMessage('퇴장합니다.') }>퇴장</button>
            <h1 style={{color : textColor}}>{message}</h1>
            <button style={{color : 'red'}} onClick = {() => setTextColor('red')}>빨강색</button>
            <button style={{color : 'blue'}} onClick = {() => setTextColor('blue')}>파란색</button>
            <button style={{color : 'green'}} onClick = {() => setTextColor('green')}>초록색</button>
        </div>
    )
}


export default Say