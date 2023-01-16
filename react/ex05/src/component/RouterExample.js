import React from 'react'
import { Link, Route, Switch } from 'react-router-dom'
import About from './About'
import History from './History'
import Home from './Home'
import Profiles from './Profiles'

const RouterExample = () => {
    return (
        <div>
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/about?page=1&detail=true&key=info">소개</Link></li>
                <li><Link to="/info">Infomation</Link></li>
                <li><Link to="/profiles">프로파일목록</Link></li>
                <li><Link to="/history">History</Link></li>
            </ul>
            <hr />
            <Switch>
                <Route path="/" component={Home} exact />
                <Route path={["/about", "/info"]} component={About} />
                <Route path="/profiles" component={Profiles} />
                <Route path="/history" component={History} />
                <Route render={( {location} ) => (<div><h1>페이지가 존재하지 않습니다.</h1><p>주소 : {location.pathname}</p></div>)} />
            </Switch>

        </div>
    )
}

export default RouterExample