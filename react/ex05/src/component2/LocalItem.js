import React from 'react'
import { withRouter } from 'react-router-dom';

/* global kakao */

const LocalItem = ({ local, history }) => {
    const { place_name, place_url, id, address_name, phone, x, y } = local;
    
    const onClick = () => {
        history.push(`/local/map?x=${x}&y=${y}`);
    }

    return (
        <div>
            <h4>
                <span>{place_name} : {address_name} : {phone}</span>
                <button style={{margin : '0px 20px'}} onClick={onClick} className="link">위치보기</button>
            </h4>
        </div>
    )
}

export default withRouter(LocalItem)