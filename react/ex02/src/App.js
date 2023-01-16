import React, { useState } from 'react'
import './App.css';
import Todos from './component/Todos';
import Posts from './component/Posts';
import Users from './component/Users';

const App = () => {
    const [show, setShow] = useState(false);
    const [item, setItem] = useState(2);

    return (
      <div className="App">
        <div style={{marginTop : '30px'}}>
            <span className = 'item' onClick = {() => setItem(1)}>Todo List</span>
            <span className = 'item' onClick = {() => setItem(2)}>Post List</span>
            <span className = 'item' onClick = {() => setItem(3)}>User List</span>
        </div>
        {item === 1 ? <Todos/> : item === 2 ? <Posts/> : <Users/>}
      </div>
    );
}

export default App;
