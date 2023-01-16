import React from 'react'
import Parser from 'html-react-parser'
import './Product.css';

const MovieItem = ({ movie }) => {
    const { title, link, image } = movie;
    return (
        <div className='box'>
            <div className='image'>
            <a href={link}><img src={image} /></a> 
            </div>
            <div className='content'>
                <div className='title ellipsis'>{Parser(title)}</div>
                <a href={link}><button className='btn'>예매하기</button></a> 
            </div>
        </div>
    )
}

export default MovieItem