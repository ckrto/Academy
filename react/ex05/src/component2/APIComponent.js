import React from 'react'
import { NavLink, Route } from 'react-router-dom';
import BlogPage from './BlogPage';
import BookPage from './BookPage';
import LocalPage from './LocalPage';

const APIComponent = () => {

    const style = {
        padding : '10px 20px'
    }

    return (
        <div className="App">
            <NavLink to="/book"><span style={style}>도서검색</span></NavLink>
            <NavLink to="/blog"><span style={style}>블로그검색</span></NavLink>
            <NavLink to="/local?query=버거킹&page=1"><span style={style}>지역검색</span></NavLink>
            <hr />
            <Route path="/book" component={BookPage} />
            <Route path="/blog" component={BlogPage} />
            <Route path="/local" component={LocalPage} />
        </div>
    );
}

export default APIComponent