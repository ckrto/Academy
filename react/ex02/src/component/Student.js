import React from 'react'

const Student = (props) => {
    const {id, name, address, tel, onDelete} = props;

    return (
        <tr>
            <td>{name}</td>
            <td>{address}</td>
            <td>{tel}</td>
            <td><button onClick={() => onDelete(id)}>삭제</button></td>
        </tr>
    )
}

export default Student