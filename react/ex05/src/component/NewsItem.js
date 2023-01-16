import React from 'react'

const NewsItem = ( {article} ) => {
    const {title, description, url, urlToImage} = article;
    return (
        <div>
            <div><a href={url}><img src = {urlToImage} alt = "thumbnail" /></a></div>
            <div>
                <a href={url}><h2>{title}</h2></a>
                <p>{description}</p>
            </div>
        </div>
    )
}

export default NewsItem