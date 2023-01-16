import React, { useRef, useState } from 'react'
import Student from './Student';
import StudentInsert from './StudentInsert';

const StudentList = () => {
    const [no, setNo] = useState(4);
    const refNo = useRef(4);
 
    const [data, setData] = useState([
        {id : 1, name : '홍길동', address : '인천 남구 학익동', tel : '010-1010-1010'},
        {id : 2, name : '심청이', address : '인천 서구 청라동', tel : '010-2020-2020'},
        {id : 3, name : '강감찬', address : '인천 부평구 계산동', tel : '010-3030-3030'},
    ]);

    const onDelete = (id) => {
        if(!window.confirm(`${id}번 학생을 삭제하시겠습니까?`)) {
            return;
        }
        else {
            const newData = data.filter(s => s.id !== id);
            setData(newData);
        }
    }

    const onInsert = (s) => {
        if(!window.confirm(`${refNo.current}번 학생을 등록하시겠습니까?`)) {
            return;
        }
        else {
            const newData = data.concat({
                id : refNo.current,
                name : s.name,
                address : s.address,
                tel : s.tel
            });
            setData(newData);
            setNo(refNo.current + 1);
        }
    }

    return (
        <div>
            <StudentInsert onInsert = {onInsert} no = {refNo.current}/>
            <hr/>
            <table width={600}>
                {data.map(s => 
                    <Student key = {s.id} 
                    id = {s.id} 
                    name = {s.name} 
                    address = {s.address} 
                    tel = {s.tel} 
                    onDelete = {onDelete}/>)}
            </table>
        </div>
    )
}

export default StudentList