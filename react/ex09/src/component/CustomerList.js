import React, { useEffect, useState } from 'react'
import CustomerItem from './CustomerItem';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import axios from 'axios';
import CustomerInsert from './CustomerInsert';
import CircularProgress from '@material-ui/core/CircularProgress';
import Pagination from 'react-js-pagination';
import SearchAppBar from './SearchAppBar';
import Banner from './Banner';

const useStyles = makeStyles({
    table: {
        minWidth: 800,
    },
});

const CustomerList = () => {
    const classes = useStyles();

    const [customers, setCustomers] = useState();
    const [page, setPage] = useState(1);
    const [total, setTotal] = useState(0);
    const [word,setWord]=useState('');

    const onPageChange = (page) => {
        setPage(page);
    }

    const callAPI = async (word) => {
        const response = await axios.get(`/customers/list?page=${page}&word=${word}`);
        setCustomers(response.data.list);
        setTotal(response.data.total);
    }

    useEffect(() => {
        console.log(`page:${page}`,`word : ${word}`)
        callAPI(word);
    }, [page]);

    if (!customers) return <CircularProgress color="secondary" />

    return (
        <div>
            <SearchAppBar callAPI={callAPI} setWord={setWord} word={word} setPage={setPage}/>
            <Banner/>
            <div style={{marginBottom:'10px'}}>
                <CustomerInsert callAPI={callAPI} />
                <span style={{marginLeft : '20px'}}>검색수 : {total}</span>
            </div>
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead style={{background:'#3f51b5'}}>
                        <TableRow>
                            <TableCell style={{color:"white"}}>번호</TableCell>
                            <TableCell style={{color:"white"}} align="left">이미지</TableCell>
                            <TableCell align="left" style={{color:"white"}}>이름</TableCell>
                            <TableCell align="left" style={{color:"white"}}>생년월일</TableCell>
                            <TableCell align="left" style={{color:"white"}}>성별</TableCell>
                            <TableCell align="left" style={{color:"white"}}>직업</TableCell>
                            <TableCell align="left" style={{color:"white"}}>삭제</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {customers.map(c =>
                            <CustomerItem customer={c} key={c.id} callAPI={callAPI}/>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
            {total> 0? 
                <Pagination
                    activePage={page}
                    itemsCountPerPage={5}
                    totalItemsCount={total}
                    pageRangeDisplayed={20}
                    prevPageText={"‹"}
                    nextPageText={"›"}
                    onChange={onPageChange} /> :
                    <TableRow>
                        <TableCell>검색 결과가 없습니다.</TableCell>
                    </TableRow>
            }
        </div>
    )
}

export default CustomerList