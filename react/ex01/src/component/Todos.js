import React, { useEffect, useState } from 'react'

const Todos = () => {
    const [todos, setTodos] = useState(null);
    const [page, setPage] = useState(1);
    const [last, setLast] = useState(1);

    useEffect(() => {
        callAPI();
    }, [page]);

    const callAPI = () => {
        fetch('http://jsonplaceholder.typicode.com/todos')
        .then(response => response.json())
        .then(json => {
            console.log(json);
            let start = (page-1) * 10 + 1;
            let end = page * 10;
            const datas = json.filter(data => data.id >= start && data.id <= end);
            setTodos(datas);
            setLast(Math.ceil(json.length/10));
        });
    }

    return (
        <div>
            <h1>Todo List</h1>
            {todos ? todos.map(todo => 
                <h3>[{todo.id}] {todo.title}</h3>) : '<h2>데이터를 불러오는 중</h2>'
            }
            <button disabled = {page === 1 && true} onClick={() => setPage(page-1)}>이전</button>
            <span> {page}/{last} </span>
            <button disabled={page === last && true} onClick={() => setPage(page+1)}>다음</button>
        </div>
    )
}

export default Todos