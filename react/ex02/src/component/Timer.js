import React, { useEffect, useRef } from 'react'

const Timer = () => {
    const number = useRef(0);
    useEffect (() => {
        const timer = setInterval (() => {
            number.current = number.current + 1;
            console.log('째깍 째깍', number.current);
        }, 1000);

        return () => {
            clearInterval(timer);
            console.log('타임 스토푸');
        }
    }, []);

    return (
        <div>
            <span>타이머를 시작합니다. 콘솔을 확인해주세요.</span>
            <span>{number.current}초</span>
        </div>
    )
}

export default Timer