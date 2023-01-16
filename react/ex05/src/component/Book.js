import React from 'react'

const Book = ({ book }) => {
    const { thumbnail, title, price, authors, contents, url } = book;
    return (
        <div className='books'>
            <div className='box'>
                <div className='image'>
                    <a href={url}><img src={thumbnail} /></a>
                </div>
                <div className='content'>
                    <div className='title ellipsis'>{title}</div>
                    <div className='price'>{price}</div>
                    <div className='authors'>{authors}</div>
                    <div className='contents ellipsis'>{contents}</div>
                </div>
            </div>
        </div>

    )
}

export default Book