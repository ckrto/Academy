import React, { useState } from 'react'
import LocalList from './LocalList';

const LocalPage = ( {history} ) => {
    const [query, setQuery] = useState('인천 청라동');
    const onChange = (e) => {
        setQuery(e.target.value);
        history.push(`/local?query=${query}&page=1`);
    }
    
    return (
        <div>
            <input onChange = {onChange} placeholder='검색어'value={query}/>
            <hr/>
            <LocalList/>
        </div>
    )
}

export default LocalPage