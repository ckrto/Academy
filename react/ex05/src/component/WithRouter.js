import React, { useEffect } from 'react'
import { withRouter } from 'react-router-dom'

const WithRouter = ( {match, location, history} ) => {
    useEffect (() => {
        const unblock = history.block('떠날거니...?');
        return () => {unblock();}
    }, [history]);
    return (
        <div>
            <h4>location</h4>
            <textarea rows={7} cols={50} value = {JSON.stringify(location, null, 4)} />
            <h4>match</h4>
            <textarea rows={7} cols={50} value = {JSON.stringify(match, null, 4)} />
            <br/>
            <button onClick={() => history.goBack()}>뒤로가기</button>
        </div>
    )
}

export default withRouter(WithRouter)