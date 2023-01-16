import { useState } from 'react';
import { Route } from 'react-router-dom';
import './App.css';
import Books from './component/Books';
import Categories from './component/Categories';

function App() {
    const [page, setPage] = useState(1);
    return (
        <div className="App">
            <Categories page = {page}/>
            <Route path="/" component={Books}/>
            <div className="more"><span onClick={() => setPage(page + 1)}>더보기</span></div>
        </div>
    );
}

export default App;
