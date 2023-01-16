import React, { useRef, useState } from 'react'
import Student from './Student';
import StudentInsert from './StudentInsert';

const Students = () => {
    const nextId = useRef(4);

    const [students, setStudent] = useState([
        {id : 1, name : '홍길동', tel : '010-0000-0000', address : '인천 미추홀구 학익동'},
        {id : 2, name : '심청이', tel : '010-1010-1010', address : '인천 미추홀구 주안동'},
        {id : 3, name : '강감찬', tel : '010-2020-2020', address : '인천 미추홀구 숭의동'}
    ]);
    
    const onInsert = (s) => {
        if(!window.confirm(`${nextId.current}번 학생을 등록하시겠습니까?`)) {
            return;
        } else {
            const newStudents = students.concat({
                id : nextId.current,
                name : s.name,
                tel : s.tel,
                address : s.address
            });
            setStudent(newStudents);
            nextId.current = nextId.current + 1;    
        }
    }

    const onDelete = (id) => {
        if(!window.confirm(`${id}번 학생을 삭제하시겠습니까?`)) {
            return;
        } else {
            const newStudents = students.filter(s => s.id !== id);
            setStudent(newStudents);
        }
    }

    return (
        <div>
            <StudentInsert onInsert = {onInsert}/>
            <h1>학생 목록</h1>
            <table>
                <tr className='title'>
                    <td width={150}>학번</td>
                    <td width={150}>이름</td>
                    <td width={300}>전화번호</td>
                    <td width={300}>주소</td>
                    <td width={100}>삭제</td>
                </tr>
                {students.map(s => <Student key = {s.id} s = {s} onDelete = {onDelete}/>)}
            </table>
            
        </div>
    )
}

export default Students