import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Table from 'react-bootstrap/Table';
import ShopItem from './ShopItem';
import Pagination from 'react-js-pagination';
import './Pagination.css'
import qs from 'qs';
import { Button } from 'react-bootstrap';
const ShopList = ({ location, history }) => {
    const search = qs.parse(location.search, { ignoreQueryPrefix: true });
    const page = !search.page ? 1 : search.page;
    const [shops, setShops] = useState([]);
    const [total, setTotal] = useState(0);
    const num = 5;

    const [chkItems,setChkItems]=useState([]);


    const callShops = async () => {
        const result = await axios.get(`/api/shop/list?page=${page}&num=${num}`);
        setShops(result.data.list);
        setTotal(result.data.total);
    }

    const onPageChange = (e) => {
        history.push(`/shop/list?page=${e}`);
    }

    const onAllCheck=(checked)=>{
        if(checked){
            const all=[];
            shops.forEach(shop=>all.push(shop.code));
            setChkItems(all);
        }
        else{
            setChkItems([]);
        } 
    }

    const onSingleCheck=(code,checked)=>{
        if(checked){
            setChkItems(chkItems.concat(code));
        }

        else{
            setChkItems(chkItems.filter(item=>item!==code));
        }
    }

    const onClickSale=async()=>{
        for(let i=0; i<chkItems.length; i++){
            await axios.post(`/api/shop/addSale?code=${chkItems[i]}`);
        }
        alert("세일등록성공");
        setChkItems([]);
    }

    useEffect(() => {
        callShops();
    }, [location]);

    if (!shops) return <h1>Loading......</h1>

    return (
        <>
            <Button onClick={onClickSale}>세일상품등록</Button>
            <hr/>
            <Table>
                <thead>
                    <th><input type={'checkbox'} onChange={(e)=>onAllCheck(e.target.checked)} checked={chkItems.length===shops.length}/></th>
                    <th>Code</th>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Price</th>
                </thead>
                <tbody>
                    {shops.map(shop =>
                        <ShopItem key={shop.code} shop={shop} chkItems={chkItems} onSingleCheck={onSingleCheck}/>
                    )}
                </tbody>
            </Table>
            <Pagination
                activePage={page}
                itemsCountPerPage={num}
                totalItemsCount={total}
                pageRangeDisplayed={20}
                prevPageText={"‹"}
                nextPageText={"›"}
                onChange={onPageChange} />
        </>
    )
}

export default ShopList
