import React from 'react'
import {
    MdCheckBoxOutlineBlank, MdRemoveCircleOutline, MdCheckBox
} from "react-icons/md"

const TodoListItem = ({todo, onDelete, onToggle}) => {
    const { id, text, checked } = todo;

    return (
        <div className="TodoListItem">
            <div className={checked ? 'checkbox_checked ' : 'checkbox'} onClick={() => onToggle(id)}>
                {checked ? <MdCheckBox/> : <MdCheckBoxOutlineBlank/> }
                <div className='text'>{text}</div>
            </div>
            <div className='remove' onClick = {() => onDelete(id)}>
                <MdRemoveCircleOutline />
            </div>
        </div>
    );
};
export default TodoListItem;