import React from 'react'

const MyComponent = (props) => {
    const {name, age} = props;
    return (
        <div>
            {props.children}
        </div>
    )
}

export default MyComponent