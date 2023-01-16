import React, { useEffect, useRef, useState } from 'react'
import MovieList from './MovieList';

const MoviePage = ({ history }) => {
    const [query, setQuery] = useState('');
    const refQuery = useRef();

    const onChange = (e) => {
        setQuery(e.target.value);
        history.push(`/movie?query=${query}`);
    }

    useEffect(() => {
        refQuery.current.focus();
    }, []);

    return (
        <div>
            <h3>영화검색</h3>
            <input placeholder='검색어' ref={refQuery} onChange={onChange} value={query}></input>
            <MovieList />
        </div>
    )
}

export default MoviePage