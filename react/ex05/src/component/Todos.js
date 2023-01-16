import axios from 'axios';
import React, { useEffect, useState } from 'react'

const Todos = ({match}) => {
    const [id, setId] = useState();
    const [todos,setTodos] =useState();

    const callAPI=async()=>{
        setId(match.params.id);
        const res = await axios.get('https://jsonplaceholder.typicode.com/todos');
        const newTodos=res.data.filter(todo=>todo.userId=id);
        setTodos(newTodos);    
    }
    useEffect(()=>{
        callAPI();
    }, [id]);
    
    if(!todos) return(
        <h1>데이터를 불러오는 중입니다.</h1>
    )

    return (
        <div>
            <h2>{id} Todo List</h2>
            {todos.map(todo=><h4 key={todo.id}>{todo.title}</h4>)}
        </div>
    )
}

export default Todos