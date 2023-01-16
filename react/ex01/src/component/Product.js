import React, { useState } from 'react'

const Product = () => {
    const [products, setProducts] = useState([
        {code : 'P101', title : '냉장고', price : '3000000'},
        {code : 'P102', title : '세탁기', price : '1500000'},
        {code : 'P103', title : '에어컨', price : '1200000'}
    ]);

    const [form, setForm] = useState ({
        code : 'P104',
        title : '',
        price : 0
    });

    const {code, title, price} = form;

    const onChangeForm = (e) => {
        const newForm = {
            ...form,
            [e.target.name] : e.target.value
        }
        setForm(newForm);
    }

    const onClickInsert = () => {
        const newproducts = products.concat({
            code : code,
            title : title,
            price : price
        });
        setProducts(newproducts);
        setForm ({
            code : 'P105',
            title : '',
            price : 0
        });
    }

    const onKeyDownPrice = (e) => {
        if(e.key == 'Enter') {
            onClickInsert();
        }
    }

    const onDelete = (title) => {
        if(!window.confirm(`상품 ${title}를 삭제하시겠습니까?`)) {
            return;
        }
        else {
            const newproducts = products.filter(p => title !== p.title);
            setProducts(newproducts);
        }
    }

    return (
        <div>
            <h1>상품 등록</h1>
            <div>
                <table width={600}>
                    <tr>
                        <td>상품 코드 : <input type = "text" onChange={onChangeForm} value = {code} name = "code"/></td>
                    </tr>
                    <tr>
                        <td>상품 이름 : <input type = "text" onChange={onChangeForm} value = {title} name = "title"/></td>
                    </tr>
                    <tr>
                        <td>상품 가격 : <input type = "text" onKeyDown={onKeyDownPrice} onChange={onChangeForm} value = {price} name = "price"/>원</td>
                    </tr>
                    <tr>
                        <td><button onClick={onClickInsert}>상품 등록</button></td>
                    </tr>
                </table>
            </div>
            
            <br/>

            <h1>상품 목록</h1>
            <table>
                <tr className='title'>
                    <td width={100}>상품코드</td>
                    <td width={300}>상품명</td>
                    <td width={100}>상품가격</td>
                    <td width={100}>상품삭제</td>
                </tr>
                {products.map(p => 
                    <tr className='row' key = {p.code}>
                        <td>{p.code}</td>
                        <td>{p.title}</td>
                        <td>{p.price}</td>
                        <td><button  onClick={()=>{onDelete(p.title)}}>상품삭제</button></td>
                    </tr>
                )}
            </table>
        </div>
    )
}

export default Product