import React, { useEffect, useState } from 'react'
import { Link, withRouter } from 'react-router-dom'
import qs from 'qs'
import axios from 'axios';
import MovieItem from './MovieItem';

const MovieList = ({ location }) => {
    const search = qs.parse(location.search, {ignoreQueryPrefix:true});
    const query = search.query;
    const page = !search.page ? 1 : parseInt(search.page);

    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(false);
    const [lastpage, setLastPage] = useState();
    const [is_end, setIs_end] = useState(false);

    const callAPI = async() => {
        const url = `/v1/search/movie.json`;
        const config = {
            params : {
                query: query,
                display : 5,
                start : (page - 1) * 5 + 1
            },
            headers : {
                'Content-Type': 'application/json',
                'X-Naver-Client-Id': 'DC8ojH4p4RE2lMhourmV',
                'X-Naver-Client-Secret': '1KTqGCjPFg'
            }
        }
        setLoading(true);
        const result = await axios.get(url, config);
        setLoading(false);
        const items = page === 1 ? result.data.items : movies.concat(result.data.items); 
        setMovies(items);
        
        const lastPage = Math.ceil(result.data.total/5);
        setLastPage(lastPage);
        if(page === 3 || page === lastPage) {
            setIs_end(true);
        }
        else {
            setIs_end(false);
        }
    }

    useEffect(() => {
        callAPI();
    }, [query, page]);

    if (loading) {
        return (<h1>로딩중</h1>);
    }

    return (
        <div>
            <h3>검색결과 : {query} ({page}/{lastpage})</h3>
            {movies.map(m =>
                <MovieItem key={m.link} movie={m} />
            )}
            {!is_end &&
                <Link to={`/movie?query=${query}&page=${page+1}`}><span>더보기</span></Link>
            }
        </div>
    )
}

export default withRouter(MovieList)