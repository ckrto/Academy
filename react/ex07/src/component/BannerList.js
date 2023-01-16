import axios from 'axios';
import React, { useEffect, useState } from 'react'
import BannerItem from './BannerItem';

const BannerList = () => {
    const [items, setItems] = useState([]);
    const [banners, setBanners] = useState();

    const callAPI = async () => {
        const result = await axios.get('/banners/list');
        setBanners(result.data);
    }

    useEffect(() => {
        callAPI();
    }, []);

    const onSingle = (id, checked) => {
        if (checked) {
            setItems(items.concat(id));
        } else {
            setItems(items.filter(item => item !== id));
        }
    }

    const onAll = (checked) => {
        if (checked) {
            const all = [];
            banners.forEach(item => all.push(item.id));
            setItems(all);
        } else {
            setItems([]);
        }
    }

    const onClick = async (bshow) => {
        if (items.length === 0) {
            alert('아이템을 선택하세요');
            return;
        }

        if (!window.confirm(`${items.length}개의 상태를 변경하시겠습니까?`)) {
            return;
        }
        for (let i = 0; i < items.length; i++) {
            const data = { bshow: bshow, id: items[i] }
            await axios.post('/banners/change', data);
        }
        callAPI();
        setItems([]);
    }

    const onDelete = async(id, url) => {
        if(!window.confirm(`${id}번\n ${url} 베너를 삭제하시겠습니까?`)) {
            return;
        } else {
            await axios.post('/banners/delete', {id:id, url:url});
            callAPI();
        }
    }

    if (!banners) {
        return (<h1>로딩중</h1>);
    }

    return (
        <div>
            <h1>Banner List</h1>
            <div>
                <input type="checkbox" onChange={(e) => onAll(e.target.checked)} checked={items.length === banners.length && true} />
                <button onClick={() => onClick(1)} >보이기</button>
                <button onClick={() => onClick(0)} >숨기기</button>
            </div>
            {banners.map(banner => <BannerItem key={banner.id} banner={banner} onSingle={onSingle} items={items} onDelete={onDelete}/>)}
        </div>
    )
}

export default BannerList