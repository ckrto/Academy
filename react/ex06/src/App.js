import { Link, Route } from 'react-router-dom';
import './App.css';
import PostsInsert from './component1/PostsInsert';
import PostsPage from './component1/PostsPage';
import PostsRead from './component1/PostsRead';
import PostsUpdate from './component1/PostsUpdate';

function App() {
    return (
        <div className="App">
            <Link to="/posts?page=1">Post List</Link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/posts/insert">Post Insert</Link>
            <hr />
            <Route path="/posts" component={PostsPage} exact />
            <Route path="/posts/insert" component={PostsInsert} />
            <Route path="/posts/read/:id" component={PostsRead} exact/>
            <Route path="/posts/update/:id" component={PostsUpdate} exact/>
        </div>
    );
}

export default App;
