import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import './Category.css'

const categories = [
    {id:1, title : '자바'},
    {id:2, title : 'HTML5'},
    {id:3, title : 'Node.js'},
    {id:4, title : 'JSP&Servlet'},
    {id:5, title : 'Android'},
    {id:6, title : 'React'},
    {id:7, title : 'Spring'},
    {id:8, title : '데이터베이스'}
];

const Categories = ( {intPage} ) => {
    const [page, setPage] = useState(1);
    setPage(intPage);

    return (
        <div className='categories'>
            {categories.map(c =>
                <span className='category' key={c.id}>
                    <Link to = {`/?title=${c.title}&page=${page}`}>{c.title}</Link></span>
            )}
        </div>
    )
}

export default Categories