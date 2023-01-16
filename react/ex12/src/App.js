import './App.css';
import Header from './components/Header';
import { Route, Switch } from 'react-router-dom';
import HomePage from './components/HomePage';
import MoviePage from './components/MoviePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import LoginPage from './components/LoginPage';
import UserInsert from './components/UserInsert';
import { useState } from 'react';
import { UserContext } from './context/UserContext';
import UserRead from './components/UserRead';
import ChatPage from './components/ChatPage';
import ShopPage from './components/ShopPage';

function App() {
    const [loginUser, setLoginUser] = useState('');

    return (
        <UserContext.Provider value={{ loginUser, setLoginUser }}>
            <div className="App">
                <Header />
                <Switch>
                    <Route path="/" component={HomePage} exact />
                    <Route path="/movie" component={MoviePage} />
                    <Route path="/chat" component={ChatPage} />
                    <Route path="/login" component={LoginPage} />
                    <Route path="/user/insert" component={UserInsert} />
                    <Route path="/user/read/:uid" component={UserRead} />
                    <Route path="/shop" component={ShopPage} />
                </Switch>
            </div>
        </UserContext.Provider>
    );
}

export default App;
