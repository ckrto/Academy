import React, { useCallback, useEffect, useRef, useState } from 'react'
import LocalList from '../component3/LocalList'

const LocalPage = ({ history }) => {
    const [query, setQuery] = useState('인천 학익동');
    const refQuery = useRef();
    const onChange = useCallback((e) => {
        setQuery(e.target.value);
        history.push(`/local?query=${query}`);
    }, [query]);

    useEffect (() => {
        refQuery.current.focus();
    }, []);

    return (
        <div>
            <h3>지역검색</h3>
            <input ref={refQuery} onChange={onChange} value={query} placeholder='검색어' />
            <LocalList />
        </div>
    )
}

export default LocalPage