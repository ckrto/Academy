import React, { useState } from 'react'

const StudentInsert = ({onInsert}) => {
    const [form, setForm] =useState({
        name : '',
        tel : '010',
        address : '인천 미추홀구'
    });

    const {name, tel, address} = form;
    const onChangeForm = (e) => {
        const newForm = {
            ...form,
            [e.target.name] : e.target.value
        }
        setForm(newForm);
    }
    
    const onInsertStudent = () => {
        onInsert(form);
        setForm ({
            name : '',
            tel : '010',
            address : '인천 미추홀구'
        });
    }

    return (
        <div>
            <h1>학생 등록</h1>
            <input type = "text" onChange={onChangeForm} value = {name} name = "name" placeholder='이름' /><br/>
            <input type = "text" onChange={onChangeForm} value = {tel} name = "tel" placeholder='전화번호' /><br/>
            <input type = "text" onChange={onChangeForm} value = {address} name = "address" placeholder='주소' /><br/>
            <button style={{marginTop : 10}} onClick = {onInsertStudent}>등록</button>
        </div>
    )
}

export default StudentInsert