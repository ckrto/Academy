import React, { useState } from 'react'

const Toggle = () => {
    const [toggle, setToggle] = useState(false);
    return (
    <div>
        <button onClick={() => setToggle(!toggle)}>{toggle ? 'ON' : 'OFF'}</button>
    </div>
  )
}

export default Toggle