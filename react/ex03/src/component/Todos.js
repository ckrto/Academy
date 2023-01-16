import React, { useEffect, useState } from 'react'

const Todos = ({id, name}) => {
    const [todos, setTodos] = useState([]);
    
    const callAPI = () => {
        fetch('https://jsonplaceholder.typicode.com/todos')
        .then(response => response.json())
        .then(json => { 
            // console.log(json);
            const newTodos = json.filter(todo => todo.userId === id);
            setTodos(newTodos);
            
        });
    }

    useEffect (() => {
        callAPI();
    }, [id]);

    if(!todos) {
        return(<h2>데이터를 불러오는중</h2>);
    }

    return (
        <div>
            <h3>{name} Todo List</h3>
            {todos.map(todo => <h5 key = {todo.id}>{todo.title}</h5>)}
        </div>
    )
}

export default Todos