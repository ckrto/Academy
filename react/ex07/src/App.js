import { Link, Route } from 'react-router-dom';
import './App.css';
import BannerPage from './component/BannerPage';
import HomePage from './component/HomePage';

function App() {
    return (
        <div className="App">
            <div className='menu'>
                <Link to="/">Home</Link>
                <Link to="/banners">Banner</Link>
            </div>
            <hr />
            <Route path="/" component={HomePage} exact />
            <Route path="/banners" component={BannerPage}/>
        </div>
    );
}

export default App;
