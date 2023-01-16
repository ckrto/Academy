import React from 'react'

const Student = ({s, onDelete}) => {
    const {id, name, tel, address} = s;

    return (
        <tr className='row'>
            <td>{id}</td>
            <td>{name}</td>
            <td>{tel}</td>
            <td>{address}</td>
            <td><button onClick={() => {onDelete(id)}}>삭제</button></td>
        </tr>
    )
}

export default Student