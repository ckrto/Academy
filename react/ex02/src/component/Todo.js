import React from 'react'

const Todo = ({todo}) => {
    const {id, title, completed} = todo;

    return (
        <div style={{textAlign:'left', width:500, margin :'0px auto'}}>
            <span><input type = "checkbox" checked={completed}/></span>
            <span>[{id}] </span>
            <span>{title}</span>
        </div>
    )
}

export default Todo