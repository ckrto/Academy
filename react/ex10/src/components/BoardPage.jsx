import React from 'react'
import { Link, Route } from 'react-router-dom'
import BoardList from './BoardList'
import BoardRead from './BoardRead'

const BoardPage = () => {
  return (
    <div>
      <div className='sub_menu'>
        <Link to="/board/list">게시판목록</Link>
      </div>
      <hr/>
      <Route path="/board/list" component={BoardList}/>
      <Route path="/board/read/:bno" component={BoardRead}/>
    </div>
  )
}

export default BoardPage