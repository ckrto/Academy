import React from 'react'
import Categories from './Categories'
import { Route } from 'react-router-dom'
import BlogList from './BlogList'
import Parser from 'html-react-parser'

const BlogPage = () => {
    return (
        <div>
            <Categories/>
            <hr/>
            <Route path="/blog/:title" component = {BlogList} exact/>
        </div>
    )
}

export default BlogPage