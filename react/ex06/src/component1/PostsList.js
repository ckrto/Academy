import React, { useEffect, useState } from 'react'
import { Link, withRouter } from 'react-router-dom';
import qs from 'qs'
import axios from 'axios';
import ErrorPage from './ErrorPage';
import PostsItem from './PostsItem';

const PostsList = ({ location }) => {
    const search = qs.parse(location.search, { ignoreQueryPrefix: true });
    const page = parseInt(search.page);
    const [total, setTotal] = useState(0);
    const [posts, setPosts] = useState();
    const [error, setError] = useState();
    const [lastPage, setLastPage] = useState(1);
    const [word, setWord] = useState('');
    const [checkItems, setCheckItems] = useState([]);


    const callAPI = async () => {
        try {
            const result = await axios.get(`/posts?page=${page}&word=${word}`);
            setPosts(result.data.list);
            setLastPage(Math.ceil(result.data.total / 10));
            setTotal(result.data.total);
        } catch (e) {
            setError(e.message);
        }
    }

    const onChange = (e) => {
        setWord(e.target.value);
    }

    const onClickOut = () => {
        alert(checkItems);
    }

    const onSingleCheck = (checked, id) => {
        if (checked) {
            setCheckItems(checkItems.concat(id));
        } else {
            setCheckItems(checkItems.filter(item => id !== item.id));
        }
    }

    const onAllCheck = (checked) => {
        if (checked) {
            const all = [];
            posts.forEach(post => all.push(post.id));
            setCheckItems(all);
        } else {
            setCheckItems([]);
        }
    }

    useEffect(() => {
        callAPI();
    }, [page, word]);

    if (error) return (
        <ErrorPage error={error} />
    )

    if (!posts) {
        return (<h1>데이터를 불러오는 중입니다.</h1>);
    }

    return (
        <div>
            <h1>Posts List</h1>
            <div>
                <input style={{ marginLeft: '10px' }} type="checkbox" onChange={(e) => onAllCheck(e.target.checked)} checked={checkItems.length === posts.length ? true : false} />
                <button onClick={onClickOut}>선택출력</button>
                <span style={{ marginRight: '20px' }}><b>검색 수 : {total}건</b></span>
                <input onChange={onChange} value={word} placeholder='검색어' />
            </div>
            {posts.map(post => <div key={post.id}><PostsItem post={post} checkItems={checkItems} onSingleCheck={onSingleCheck} /></div>)}
            <div className='buttons'>
                <Link to={`/posts?page=${page - 1}`} ><button disabled={page === 1 && true}>이전</button></Link>
                <span>{page} / {lastPage}</span>
                <Link to={`/posts?page=${page + 1}`} ><button disabled={page === lastPage && true}>다음</button></Link>
            </div>
        </div>
    )
}

export default withRouter(PostsList)