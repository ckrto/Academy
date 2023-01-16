import axios from 'axios';
import React, { useRef, useState } from 'react'

const BannerInsert = ({ history }) => {
    const [image, setImage] = useState('https://dummyimage.com/900x150');

    const onChangeFile = (e) => {
        const reader = new FileReader();
        reader.onload = (e) => {
            setImage(e.target.result);
        }
        reader.readAsDataURL(e.target.files[0]);
        const newForm = {
            ...form,
            file: e.target.files[0],
            fileName: e.target.value
        }
        setForm(newForm);
    }

    const btnFile = useRef();
    const txtTitle = useRef();

    const [form, setForm] = useState({
        file: null,
        fileName: '',
        title: ''
    });

    const { file, fileName, title } = form;

    const onChange = (e) => {
        const newForm = {
            ...form,
            [e.target.name]: e.target.value
        }
        setForm(newForm);
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        if (title === '' || fileName === '') {
            alert("베너 제목과 이미지를 선택하세요");
            txtTitle.current.focus();
            return;
        }

        if (!window.confirm('새로운 베너를 등록하시겠습니까?')) {
            return;
        } else {
            const formData = new FormData();
            formData.append('image', file);
            formData.append('title', title);
            const config = {
                headers: { 'content-type': 'multipart/form-data' }
            }
            await axios.post('/banners/insert', formData, config);
            
            history.push('/banners/list');
        }
    }

    return (
        <div>
            <h1>Banner Resister</h1>
            <form className='formBanner' onSubmit={onSubmit}>
                <input ref={txtTitle} onChange={onChange} name="title" value={title} placeholder='베너 제목' />
                <img src={image} onClick={() => btnFile.current.click()} />
                <input name='file' value={fileName} ref={btnFile} type="file" onChange={onChangeFile} style={{ display: 'none' }} />
                <div>{title} : {fileName}</div>
                <button>등록</button>
            </form>
        </div>
    )
}

export default BannerInsert