import React from 'react'
import { Link, NavLink, Route } from 'react-router-dom'
import LocalPage from './LocalPage'
import MoviePage from './MoviePage'
import ProductPage from './ProductPage'

const APIExample = () => {
    
    const activeStyle = {
        background : 'gray',
        color : 'white'
    }

    const style = {
        padding : '5px 20px',
        margin : '0px 30px'
    }

    return (
        <div>
            <NavLink to = "/local" style={style} activeStyle={activeStyle}><span>지역검색</span></NavLink>
            <NavLink to = "/product" style={style} activeStyle={activeStyle}><span>상품검색</span></NavLink>
            <NavLink to = "/movie" style={style} activeStyle={activeStyle}><span>영화검색</span></NavLink>
            <hr/>
            <Route path="/local" component={LocalPage}/>
            <Route path="/product" component={ProductPage}/>
            <Route path="/movie" component={MoviePage}/>
        </div>
    )
}

export default APIExample