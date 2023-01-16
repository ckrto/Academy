import React from 'react'
import { Link } from 'react-router-dom'

const MenuBar = () => {
    const style = {
        background: '#4050B5',
        color: '#FFFFFF',
        padding: '20px',
    }
    return (
        <div style={style} className="menuBar">
            <Link to="/">Home</Link>
            <Link to="/customers">CustomerList</Link>
            <Link to="/chart?type=1">CustomerChart</Link>
            <Link to="/posts">PostsList</Link>
        </div>
    )
}

export default MenuBar