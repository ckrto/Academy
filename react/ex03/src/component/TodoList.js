import React from 'react'
import TodoListItem from './TodoListItem'

const TodoList = ({todos, onDelete, onToggle}) => {
    return (
        <div className='TodoList'>
            {todos.map(todo => (
                <TodoListItem todo = {todo} key = {todo.id} onDelete = {onDelete} onToggle = {onToggle}/>
            ))}
        </div>
    )
}

export default TodoList