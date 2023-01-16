import React from 'react'
import { Link, Route } from 'react-router-dom'
import ShopList from './ShopList'

const ShopPage = () => {
    return (
        <div>
            <hr/>
            <div className='main_menu'>
                <Link to="/shop/list">상품목록</Link>
            </div>
            <Route path="/shop/list" component={ShopList} />
        </div>
    )
}

export default ShopPage