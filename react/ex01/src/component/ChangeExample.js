import React, { useState } from 'react'

const ChangeExample = () => {
    const [name, setName] = useState('홍길동');
    const [age, setAge] = useState(26);
    const onClickConfirm = ()=>{
        alert(`이름은 ${name}이고 나이는 ${age}입니다.`);
        setName('');
        setAge(0);
    }
    
    const onKeyPress = (e) => {
        if(e.key == 'Enter') {
            onClickConfirm();
        }
    }

    return (
        <div>
            <input type = "text" placeholder='이름을 입력해주세요' value={name} onChange={(e) => setName(e.target.value)}/>
            <br/>
            <input type= "text" placeholder='나이를 입력해주세요' value={age} onChange={(e) => setAge(e.target.value)} onKeyPress={onKeyPress}/>
            <br/>
            <button onClick={onClickConfirm}>확인</button>
            <div>
                <h1>이름은 {name} 입니다.</h1>
                <h1>나이는 {age} 입니다.</h1>
            </div>
        </div>
    )
}

export default ChangeExample