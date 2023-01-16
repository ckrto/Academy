import React, { useRef, useState } from 'react'

const UseRefSample = () => {
    const [count, setCount] = useState(0);
    const refCount = useRef(0);
    let varCount = 0;

    console.log('렌더링...');

    const onClickRef = () => {
        refCount.current = refCount.current + 1;
        console.log('refCount : ' + refCount.current);
    }

    const onClickVar = () => {
        varCount = varCount + 1;
        console.log('varCount : ' + varCount);
    }

    return (
        <div>
            <p>State : {count}</p>
            <p>Ref : {refCount.current}</p>
            <p>Var : {varCount}</p>
            <button onClick = {() => setCount(count + 1)}>State++</button>
            <button onClick = {onClickRef}>Ref++</button>
            <button onClick = {onClickVar}>Var++</button>
        </div>
    )
}

export default UseRefSample