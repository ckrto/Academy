import React, { useContext, useEffect } from 'react'
import { Link, Route } from 'react-router-dom'
import { ThemeContext } from '../context/ThemeContext'
import ShopInsert from './ShopInsert'
import ShopList from './ShopList'
import ShopRead from './ShopRead'

const ShopPage = () => {
    const {setBackground} = useContext(ThemeContext);

    useEffect(() => {
        setBackground('warning');
    }, []);

    return (
        <div>
            <div className='sub_menu'>
                <Link to="/shop/insert">상품등록</Link>
                <Link to="/shop/list">상품목록</Link>
            </div>
            <hr/>
            <Route path="/shop/insert" component={ShopInsert}/>
            <Route path="/shop/list" component={ShopList}/>
            <Route path="/shop/read/:code" component={ShopRead}/>
        </div>
    )
}

export default ShopPage