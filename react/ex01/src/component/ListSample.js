import React, { useState, useEffect } from 'react'

const ListSample = () => {
    useEffect(() => {
        console.log('effect....' + fruit);    
    }, []);

    const [no, setNo] = useState(5);

    const [names, setNames] = useState([
        {id : 1, name : '딸기'},
        {id : 2, name : '포도'},
        {id : 3, name : '수박'},
        {id : 4, name : '오렌지'}
    ]);

    const onDoubleClickFruit = (id) => {
        if(!window.confirm(`${id}번 아이템을 삭제하시겠습니까?`)) {
            return;
        } else {
            const newNamesList = names.filter(fruit => fruit.id != id);
            setNames(newNamesList);
        }
    }

    const [fruit, setFruit] = useState('복숭아');

    const namesList = names.map(fruit => <li key = {fruit.id} onDoubleClick = {() => onDoubleClickFruit(fruit.id)}>{fruit.id} : {fruit.name}</li>);
    
    const onClickInsert = () => {
        const newNamesList = names.concat({
            id : no,
            name : fruit
        });
        setNames(newNamesList);
        setNo(no + 1);
        setFruit('');
    }
    const onKeyDownFruit = (e) => {
        if(e.key == 'Enter') {
            onClickInsert();
        }
    }

    return (
        <div>
            <input type="text" value={fruit} onChange={(e) => setFruit(e.target.value)} onKeyDown={onKeyDownFruit}/>
            <button onClick={onClickInsert}>등록</button>
            <ul>
                {namesList}
            </ul>
        </div>
    )
}

export default ListSample