import { useCallback, useState } from 'react';
import { MdAdd } from 'react-icons/md';

const TodoInsert = ({onInsert}) => {
    const [text, setText] = useState('');

    const onChange = (e) => {
        setText(e.target.value);
    }

    const onSubmit = (e) => {
        e.preventDefault();
        onInsert(text);
        setText('');
    }

    return (
        <form className="TodoInsert"  onSubmit={onSubmit}>
            <input onChange={onChange}value = {text} placeholder="할 일을 입력 하세요" />
            <button type="submit"><MdAdd /></button>
        </form>
    )
}
export default TodoInsert;