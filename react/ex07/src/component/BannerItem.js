import React from 'react'
import { Link } from 'react-router-dom';

const BannerItem = ({ banner, onSingle, items, onDelete }) => {
    const { id, title, url, bshow } = banner;
    return (
        <div>

            <h3 style={{ textDecoration: bshow === 0 && 'line-through' }}>
                <input type="checkbox" onChange={(e) => onSingle(id, e.target.checked)} checked={items.includes(id) ? true : false} />
                {id} : <Link to = {`/banners/read/${id}`}>{title}</Link> ({url}) {bshow}
                <button onClick={() => {onDelete(id, url)}}>삭제</button>
            </h3>
        </div>
    )
}

export default BannerItem