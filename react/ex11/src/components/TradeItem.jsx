import React from 'react'

const TradeItem = ({ trade }) => {
    const{id,ano,tno,famount,tradeDate,type,aname} =trade;
    return (
        <tr style={{color:type==="입금"? "black":"red"}}>
            <td>{tno} ({aname})</td>
            <td>{tradeDate}</td>
            <td>{type}</td>
            <td>{type==="입금"? famount:"-"+famount}</td>
        </tr>
    )
}

export default TradeItem