import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap';

const ShopList = () => {
    const [shops, setShops] = useState([]);

    const callShops = async () => {
        const result = await axios.get('/api/shop/list');
        setShops(result.data);
    }

    useEffect(() => {
        callShops();
    }, []);

    if (!shops) {
        return <h1>Loading.......</h1>
    }

    return (
        <Table>
            <thead>
                <tr>
                    <td>No.</td>
                    <td>이미지</td>
                    <td>상품명</td>
                    <td>가격</td>
                </tr>
            </thead>
            <tbody>
                {shops.map(shop =>
                    <tr key={shop.code}>
                        <td>{shop.code}</td>
                        <td><img src={shop.image} width={100} /></td>
                        <td>{shop.title}</td>
                        <td>{shop.price}</td>
                    </tr>
                )}
            </tbody>
        </Table>
    )
}

export default ShopList