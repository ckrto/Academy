import React from 'react'
import "./Book.css";

const BookItem = ( {book} ) => {
    const { thumbnail, title, price, authors, contents, url } = book;
    return (
        <div className='books'>
            <a href = {url}>
                <h4>{title}</h4>
                <div className='image'>
                    <a href={url}><img src={thumbnail} /></a>
                </div>
                <div className='content'>
                    <div className='title ellipsis'>제목 : {title}</div>
                    <div className='price'>가격 : {price}</div>
                    <div className='authors'>저자 : {authors}</div>
                    <div className='contents ellipsis'>줄거리 : {contents}</div>
                </div>
            </a>
        </div>
    )
}

export default BookItem