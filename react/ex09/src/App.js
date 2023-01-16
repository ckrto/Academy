import './App.css';
import CustomerList from './component/CustomerList';
import { createTheme, ThemeProvider } from '@material-ui/core'
import MenuBar from './component/MenuBar';
import { Route } from 'react-router-dom';
import CustomerChart from './component/CustomerChart';
import PostsList from './component/PostsList';
import './component/Pagination.css'
import PostsRead from './component/PostsRead';
import PostsInsert from './component/PostsInsert';
import PostsUpdate from './component/PostsUpdate';

const theme = createTheme({
  typography: {
    fontFamily: 'PyeongChang',
    fontSize: '1.2rem'
  }
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <div className="App">
        <Route path="/" component={MenuBar} />
        <Route path="/customers" component={CustomerList} />
        <Route path="/chart" component={CustomerChart} />
        <Route path="/posts" component={PostsList} exact/>
        <Route path="/posts/read/:id" component={PostsRead} exact/>
        <Route path="/posts/insert" component={PostsInsert} />
        <Route path="/posts/update" component={PostsUpdate} />
      </div>
    </ThemeProvider>
  );
}

export default App;