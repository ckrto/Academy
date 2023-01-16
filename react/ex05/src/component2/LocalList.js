import React, { useEffect, useState } from 'react'
import axios from 'axios'
import LocalItem from './LocalItem'
import qs from 'qs'
import { Link, Route, withRouter } from 'react-router-dom'
import Maps from './Maps'

const LocalList = ( {location} ) => {
    const [locals, setLocals] = useState();
    const [is_end, setIs_end] = useState(false);
    const search = qs.parse(location.search, {ignoreQueryPrefix:true});
    const page = parseInt(search.page);
    const query = search.query;

    const callAPI = async() => {
        const url = `https://dapi.kakao.com/v2/local/search/keyword.json?size=5&query=${query}&page=${page}`;
        const config = {headers : {"Authorization" : "KakaoAK a875616508d005928e0c2b275934ef8a"}};
        const result = await axios.get(url, config);
        const documents = result.data.documents;
        const newLocals = page === 1 ? documents : locals.concat(documents);
        setLocals(newLocals);
        setIs_end(result.data.meta.is_end);
    }

    useEffect(() => {
        callAPI();
    }, [query, page]);
    
    if(!locals) {
        return(<h1>데이터를 불러오는 중</h1>);
    }

    return (
        <div>
            <Route path="/local/map" component={Maps}/>
            {locals.map(local => <LocalItem key = {local.id} local = {local} />)}
            {!is_end && <Link to={`/local/?query=${query}&page=${page+1}`}>더보기</Link>}
        </div>
    )
}

export default withRouter(LocalList)