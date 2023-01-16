import './TodoTemplate.css';
import TodoTemplate from './component/TodoTemplate';
import TodoInsert from './component/TodoInsert';
import TodoList from './component/TodoList';
import { useCallback, useRef, useState } from 'react';

const App2 = () => {
    const [todos, setTodos] = useState([
        { id: 1, text: '리액트의 기초 알아보기', checked: true },
        { id: 2, text: '컴포넌트 스타일링해 보기', checked: true },
        { id: 3, text: '일정 관리 앱 만들어 보기', checked: false },
        { id: 4, text: '리액트 Hook 알아보기', checked: false }
    ]);

    const nextId = useRef(5);

    const onInsert = (text) => {
        if (!window.confirm(`${nextId.current}번째 할일을 등록하시겠습니까?`)) {
            return;
        } else {
            const todo = {
                id: nextId.current,
                text,
                checked: false
            }
            setTodos(todos.concat(todo));
            nextId.current = nextId.current + 1;
        }
    }

    const onDelete = (id) => {
        if (!window.confirm(`${id}번째 할일을 삭제하시겠습니까?`)) {
            return;
        } else {
            setTodos(todos.filter(todo => todo.id !== id));
        }
    }

    const onToggle = (id) => {
        const newTodos = todos.map(todo => todo.id === id ? {...todo, checked : !todo.checked } : todo);
        setTodos(newTodos);
    }

    return (
        <TodoTemplate>
            <TodoInsert onInsert={onInsert} />
            <TodoList todos={todos} onDelete={onDelete} onToggle={onToggle}/>
        </TodoTemplate>
    )
}

export default App2
