import React, { useEffect, useState } from 'react'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import axios from 'axios';
import CircularProgress from '@material-ui/core/CircularProgress';
import Pagination from 'react-js-pagination';
import SearchAppBar from './SearchAppBar';
import Banner from './Banner';
import PostsItem from './PostsItem';
import { Link, withRouter } from 'react-router-dom';
import CustomerInsert from './CustomerInsert';
import PostsInsert from './PostsInsert';
import { Button } from '@material-ui/core';

const useStyles = makeStyles({
    table: {
        minWidth: 800,
    },
});

const PostsList = () => {
    const [posts, setPosts] = useState();
    const [page, setPage] = useState(1);
    const [word, setWord] = useState('');
    const [total, setTotal] = useState(0);
    const classes = useStyles();

    const callAPI = async (word) => {
        const result = await axios.get(`/posts?page=${page}&word=${word}`);
        setTotal(result.data.total);
        setPosts(result.data.list);
    }

    useEffect(() => {
        callAPI(word);
    }, [page]);


    if (!posts) return <CircularProgress color="secondary" />

    return (
        <div>
            <SearchAppBar callAPI={callAPI} word={word} setWord={setWord} setPage={setPage} />
            <Banner />
            <div style={{ marginBottom: '10px' }}>
                <Link to="/posts/insert" ><Button variant="contained" color="primary">
                    등록
                </Button></Link>
                <span style={{ marginLeft: '20px' }}>검색 수 : {total}</span>
            </div>
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead style={{ background: '#3f51b5' }}>
                        <TableRow>
                            <TableCell style={{ color: "white" }}>번호</TableCell>
                            <TableCell style={{ color: "white" }} align="left">아이디</TableCell>
                            <TableCell align="left" style={{ color: "white" }}>제목</TableCell>
                            <TableCell align="left" style={{ color: "white" }}>작성일</TableCell>
                            <TableCell align="left" style={{color:"white"}}>삭제</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {posts.map(p =>
                            <PostsItem posts={p} key={p.id} callAPI={callAPI} />
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
            {total > 0 ?
                <Pagination
                    activePage={page}
                    itemsCountPerPage={5}
                    totalItemsCount={total}
                    pageRangeDisplayed={10}
                    prevPageText={"‹"}
                    nextPageText={"›"}
                    onChange={(e) => setPage(e)} /> :
                <TableRow>
                    <TableCell>검색 결과가 없습니다.</TableCell>
                </TableRow>
            }
        </div>
    )
}

export default PostsList