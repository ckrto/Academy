import React from 'react'

const BlogItem = ( {blog} ) => {
    return (
        <div>
            <a href = {blog.url}>
                <h4>{blog.title}</h4>
            </a>
        </div>
    )
}

export default BlogItem