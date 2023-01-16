import './App.css';
import { Route, Switch } from 'react-router-dom';
import AccountPage from './components/AccountPage';
import HomePage from './components/HomePage';
import 'bootstrap/dist/css/bootstrap.min.css';
import ShopPage from './components/ShopPage';
import UserPage from './components/UserPage';
import LoginPage from './components/LoginPage';
import { useEffect, useState } from 'react';
import { UserContext } from './context/UserContext';
import UserInsert from './components/UserInsert';
import UserRead from './components/UserRead';
import Header from './components/Header'
import { ThemeContext } from './context/ThemeContext'

function App() {
    const [loginUser, setLoginUser] = useState({
        uid: '',
        upass: '',
        uname: '',
        photo: ''
    });

    const [background, setBackground] = useState('primary');

    return (
        <ThemeContext.Provider value={{ background, setBackground }}>
            <UserContext.Provider value={{ loginUser, setLoginUser }}>
                <div className="App">
                    <Header />
                    <Switch>
                        <Route path="/" component={HomePage} exact />
                        <Route path="/account" component={AccountPage} />
                        <Route path="/shop" component={ShopPage} />
                        <Route path="/user" component={UserPage} exact />
                        <Route path="/login" component={LoginPage} />
                        <Route path="/user/insert" component={UserInsert} />
                        <Route path="/user/read/:uid" component={UserRead} />
                    </Switch>
                </div>
            </UserContext.Provider>
        </ThemeContext.Provider>
    );
}

export default App;
