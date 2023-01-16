import React from 'react'

const ErrorPage = ({error}) => {
    return (
        <div>
            <h1>에러가 발생했습니다.</h1>
            <span>{error}</span>
        </div>
    )
}

export default ErrorPage