import './App.css';
import Posts from './component/Posts';
import Todos from './component/Todos';

const App = () => {
  return (
    <div className="App">
        <Posts/>
        <hr/>
        <Todos/>
    </div>
  );
}

export default App;
