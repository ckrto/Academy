import React, { useState } from 'react'

const Address = () => {
    let tel = '010-1010-1010';
    
    const chageTel = () => {
        tel = '010-2020-2020';
    }

    const [form, setForm] = useState({
        name : '홍길동',
        age : '20',
        job : '대학생',
        address : '인천 남구 학익동'
    });
    
    const {name, age, job, address} = form;

    const onChangeForm = (e) => {
        const nextForm = {
            ...form, //원래 폼값을 복사
            [e.target.name] : e.target.value,
            
        }
        setForm(nextForm);
    }
    const onClickConfirm = () => {
        setForm({
            name : '',
            age : 0,
            job : ''
        });
    }

    return (
        <div>
            <h1>주소등록</h1>
            <div className='address'>
                <input type="text" onChange={onChangeForm} name="name" value={name} placeholder='이름'/>
                <input type="text" onChange={onChangeForm} name="age" value={age} placeholder='나이'/>
                <input type="text" onChange={onChangeForm} name="job" value={job} placeholder='직업'/>
                <input type="text" onChange={onChangeForm} name="address" value={address} placeholder='주소'/>
                <input type="text" value={tel} onChange={chageTel}/>
                <button onClick={onClickConfirm}>확인</button>
            </div>
            <div>
                <h2>입력결과</h2>
                <h5>이름은 {name} 입니다.</h5>
                <h5>나이는 {age}살입니다.</h5>
                <h5>직업은 {job} 입니다.</h5>
                <h5>주소는 {address} 입니다.</h5>
                <h5>전화번호는 {tel} 입니다.</h5>
            </div>
        </div>
    )
}

export default Address