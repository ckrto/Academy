import React from 'react'

const LocalItem = ({local, callMap}) => {
    const {place_name, phone,address_name,x,y}=local;

    return (
        <div>
            <span>{place_name} {address_name}({phone})</span>
            <button style={{marginLeft : '10px'}} onClick={() => callMap(x, y)}>위치보기</button>
        </div>
    )
}

export default LocalItem