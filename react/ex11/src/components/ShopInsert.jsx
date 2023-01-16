import React, { useRef, useState } from 'react'
import { Button, Card, Form } from 'react-bootstrap'
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import axios from 'axios';
const ShopInsert = ({history}) => {
    const [image,setImage]=useState('https://via.placeholder.com/150');
    const [form, setForm] = useState({
        title: '',
        price: '',
        file: null
    });

    const {title,price,file}=form;

    const onChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }
  
    const onSubmit = async(e) => {
        e.preventDefault();
        if(!window.confirm("상품을 등록하시겠습니까?")) return;
        const formData=new FormData();
        formData.append("title",title);
        formData.append("price",price);
        formData.append("file",file);
        await axios.post(`/api/shop/insert`,formData);
        alert("상품등록성공");
        history.push('/shop/list')
    }

    const onChangeFile=(e)=>{
        //미리보기
        setImage(URL.createObjectURL(e.target.files[0]));
        setForm({
            ...form,
            file:e.target.files[0]
        })
    }
    
    return (
        <div>
            <Card className='p-3'>
                <Form onSubmit={onSubmit}>
                    <Form.Control value={form.title} onChange={onChange} name="title" className='mt-3' placeholder='상품명' />
                    <Form.Control value={form.price} onChange={onChange} name="price" className='my-3' placeholder='상품가격' />
                    <img src={image} width={150} />
                    <Form.Control type='file' className='my-3' onChange={onChangeFile}/>
                    <Button type='submit'>상품등록</Button>
                </Form>
            </Card>
        </div>
    )
}

export default ShopInsert