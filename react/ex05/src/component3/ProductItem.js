import React from 'react'
import Parser from 'html-react-parser'
import './Product.css';

const ProductItem = ({ product }) => {
    const { title, link, image, lprice } = product;
    return (
        <div className='box'>
            <div className='image'>
            <a href={link}><img src={image} /></a> 
            </div>
            <div className='content'>
                <div className='title ellipsis'><a href={link}>{Parser(title)}</a></div>
                <div className='price'><b>최저가 : {lprice}원</b></div>
                <a href={link}><button className='btn'>구매하기</button></a> 
            </div>
        </div>
    )
}

export default ProductItem