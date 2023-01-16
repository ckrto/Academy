import React, { useEffect } from 'react'

const History = ( {history} ) => {
    useEffect (() => {
        const unblock = history.block('떠날거니...?');
        return () => {unblock();}
    }, [history]);
    return (
        <div>
            <button onClick={() => history.goBack()}>뒤로</button>
            <button onClick={() => history.push('/about')}>소개로</button>
        </div>
    )
}

export default History