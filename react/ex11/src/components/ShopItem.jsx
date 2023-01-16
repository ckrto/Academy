import React from 'react'
import { Link } from 'react-router-dom';

const ShopItem = ({shop,chkItems,onSingleCheck}) => {
    const {code,title,image,price}=shop;



    return (
        <tr>
            <td><input type={'checkbox'} checked={chkItems.includes(code)} onChange={(e)=>onSingleCheck(code,e.target.checked)}/></td>
            <td>{code}</td>
            <td><img src={shop.image} width={100}/></td>
            <td><Link to={`/shop/read/${code}`}>{title}</Link></td>
            <td>{price}원</td>
        </tr>
    )
}

export default ShopItem