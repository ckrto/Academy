import './App.css';
import { Link, Route, Switch } from 'react-router-dom';
import BoardPage from './components/BoardPage';
import HomePage from './components/HomePage';

function App() {
  return (
    <div className="App">
      <div className='main_menu'>
        <Link to="/">홈</Link>
        <Link to="/board/list">게시판관리</Link>
      </div>
      <hr/>
      <Switch>
        <Route path="/" component={HomePage} exact/>
        <Route path="/board" component={BoardPage}/>
      </Switch>  
    </div>
  );
}

export default App;
