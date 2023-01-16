import React from 'react'

const StyleExample = () => {
    const name = null;
    const style = {
        backgroundColor : 'yellow',
        color : 'red'
    }

    return (
        <div>  
            <h1 style = {style}>{name === '리액트' ? '리액트' : name} 안녕!</h1> {/* jsx의 주석입니다. */}
            <h1>{name === '리액트' && '리액트'} 안녕!</h1>
            <h1 style = {{color : 'green', background : 'red'}}>{name || '리액트'} 안녕! </h1>
            <h2>잘 작동하나요?</h2>
        </div>
    )
}

export default StyleExample