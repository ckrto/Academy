import React from 'react'

const Comment = (props) => {
    const {id, name, email, body} = props.comment;
    return (
        <div>
            <p>[{id}] {name}({email})</p>
            <p>{body}</p>
        </div>
    )
}

export default Comment