import React from 'react'
import { Route } from 'react-router-dom'
import BookList from './BookList'
import Categories from './Categories'

const BookPage = () => {
    return (
        <div>
            <Categories/>
            <hr/>
            <Route path="/book/:title" component = {BookList} exact/>
        </div>
    )
}

export default BookPage