import React, { useContext, useEffect } from 'react'
import { ThemeContext } from '../context/ThemeContext'
import SaleShop from './SaleShop'

const HomePage = () => {
    const {setBackground} = useContext(ThemeContext);

    useEffect(() => {
        setBackground('primary');
    }, []);

    return (
        <div>
            <SaleShop/>
        </div>
    )
}

export default HomePage