import { Route, Router, Routes } from 'react-router-dom';
import './App.css';
import HomePage from './componets/HomePage';
import Status from './componets/Status/Status';
import StatusViewer from './componets/Status/StatusViewer';
import Signin from './componets/Register/Signin';
import Siginup from './componets/Register/Siginup';
import Profile from './componets/Profile/Profile';

function App() {
  return (
    <div className="">
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/status" element={<Status />}></Route>
        <Route path="/status/:userId" element={<StatusViewer />}></Route>
        <Route path="/signin" element={<Signin />}></Route>
        <Route path="/signup" element={<Siginup />}></Route>
        <Route path='/profile' element={<Profile />}></Route>
      </Routes>
    </div>
  );
}

export default App;
