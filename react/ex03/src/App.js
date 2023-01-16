import './App.css';
import { Link, Route } from 'react-router-dom';
import Home from './component/Home';
import About from './component/About';
import Profiles from './component/Profiles';

const App = () => {
    return (
        <div className='App'>
            <div className='menu'>
                <span><Link to = "/">홈</Link></span>
                <span><Link to = "/about">소개</Link></span>
                <span><Link to = "/info">정보</Link></span>
                <span><Link to = "/profiles">정보</Link></span>
            </div>
            <hr/>

            <Route path="/" component = {Home} exact={true}/>
            <Route path={["/about", "/info"]} component = {About}/>
            <Route path="/profiles" component = {Profiles}/>
        </div>
    );
}

export default App