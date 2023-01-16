import React from 'react'
import { NavLink, withRouter } from 'react-router-dom'

const categories = [
    {id : 1, title : 'JAVA'},
    {id : 2, title : 'HTML5'},
    {id : 3, title : 'Node.js'},
    {id : 4, title : 'JSP&Servlet'},
    {id : 5, title : 'Android'},
    {id : 6, title : 'React'},
    {id : 7, title : 'Spring'},
    {id : 8, title : 'DataBase'}
]
const Categories = ( {match} ) => {
    const style = {
        padding : '10px 20px'
    }
    const astyle = {
        backgroundColor : 'aqua', 
        color : 'white'
    }

    const path = match.path;

    return (
        <div>
            {categories.map(c => <NavLink to={`${path}/${c.title}?page=1`} key={c.id} activeStyle={astyle}><span style={style}>{c.title}</span></NavLink>)}
        </div>
    )
}

export default withRouter(Categories)