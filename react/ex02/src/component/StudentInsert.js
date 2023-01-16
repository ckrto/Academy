import React, { useRef, useState } from 'react'

const StudentInsert = ({onInsert,no}) => {

    const refName = useRef();

    const [form, setForm] = useState ({
        id : no,
        name : '무기명',
        address : '인천 남구 학익동',
        tel : '010-0000-0000'
    });

    const {name, address, tel} = form;
    
    const onChangeForm = (e) => {
        const newForm = {
            ...form,
            [e.target.name] : e.target.value
        }
        setForm(newForm);
    }

    const onInsertForm = () => {
        onInsert(form);
        refName.current.focus();
    }

    return (
        <div>
            <input ref={refName} type="text" onChange = {onChangeForm} name = "name" placeholder='이름' value = {name} size = {8}></input>
            <input type="text" onChange = {onChangeForm} name = "address" placeholder='주소' value = {address} size = {35}></input>
            <input type="text" onChange = {onChangeForm} name = "tel" placeholder='전화번호' value = {tel} size = {10}></input>
            <button onClick={onInsertForm}>등록</button>
        </div>
    )
}

export default StudentInsert