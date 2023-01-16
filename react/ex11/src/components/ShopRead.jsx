import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Card from 'react-bootstrap/Card';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const ShopRead = ({ match, history }) => {
    const code = match.params.code;
    const [shop, setShop] = useState('');
    const [images, setImages] = useState([]);
    const [attfiles, setAttFiles] = useState([]);
    const [attImages, setAttImages] = useState([]);

    const onChangeFiles = (e) => {
        //이미지들 미리보기
        const files = e.target.files;
        const arrFiles = [];
        for (let i = 0; i < files.length; i++) {
            arrFiles.push(URL.createObjectURL(files[i]));
        }
        setImages(arrFiles);
        setAttFiles(files);
    }
    const callGetAttach = async () => {
        const result = await axios.get(`/api/shop/getAttach?code=${code}`);
        setAttImages(result.data);
    }
    const onClickAttach = async () => {
        const formData = new FormData();
        formData.append("code", code);
        for (let i = 0; i < attfiles.length; i++) {
            formData.append("files", attfiles[i]);
        }

        await axios.post(`/api/shop/addAttach`, formData);
        alert("첨부파일 업로드 성공");
        callGetAttach();
        setImages([]);
    }

    const onClickDel=async(id,image)=>{
        if(!window.confirm(`${id}:${image}이미지를 삭제하시겠습니까?`))return;
        await axios.post(`/api/shop/delAttach?id=${id}&image=${image}`);
        alert("삭제성공");
        callGetAttach();

    }

    useEffect(() => {
        const callShop = async () => {
            const result = await axios.get(`/api/shop/read/${code}`);
            setShop(result.data);
        }
        callShop();
        callGetAttach();
    }, []);


    if (!shop || !attImages) return <h1>Loading...</h1>

    return (
        <div>
            <Card className='p-3 my-3'>
                <Form.Control value={shop.code} className="my-2" disabled={true} />
                <Form.Control value={shop.title} className="my-2" disabled={true} />
                <Form.Control value={shop.price} className="my-2" disabled={true} />
                <Card.Img src={`/api/display?fileName=${shop.image}`} style={{ width: '200px' }} />
                <hr />
                <div className='images'>
                    {attImages.map(img =>
                        <>
                            <span className='img'>
                                <img key={img.id} src={img.image} width={80} />
                                <span className='del' onClick={()=>onClickDel(img.id,img.image)}>X</span>
                            </span>
                        </>
                    )}
                </div>
                <hr />
                <Form.Group>
                    <Form.Label>첨부파일선택</Form.Label>
                    <Form.Control type='file' multiple onChange={onChangeFiles} />

                </Form.Group>
                <hr />
                <div className='images'>
                    {images.map(img =>
                        <img className='image' key={img} src={img} width={80} />
                    )}
                    <Button onClick={onClickAttach}>파일등록</Button>
                </div>
            </Card>
            <Button onClick={() => history.go(-1)}>상품목록</Button>
        </div>
    )
}

export default ShopRead