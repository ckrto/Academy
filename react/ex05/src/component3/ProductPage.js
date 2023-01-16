import React, { useEffect, useRef, useState } from 'react'
import ProductList from './ProductList';

const ProductPage = ({ history }) => {
    const [query, setQuery] = useState('');
    const refQuery = useRef();

    const onChange = (e) => {
        setQuery(e.target.value);
        history.push(`/product?query=${query}`);
    }

    useEffect(() => {
        refQuery.current.focus();
    }, []);

    return (
        <div>
            <h3>상품검색</h3>
            <input ref = {refQuery} placeholder='검색어' onChange={onChange} value={query}/>
            <ProductList />
        </div>

    )
}

export default ProductPage