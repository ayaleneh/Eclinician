import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './LoginDashboard/LoginPage';
import Navbar from './Navbar';

function App() {
  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route path='/login' element={<Login/>} />
      </Routes>
    </Router>
  );
}

export default App;
