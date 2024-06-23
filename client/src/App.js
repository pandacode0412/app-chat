import { Route, Router, Routes } from 'react-router-dom';
import './App.css';
import HomePage from './componets/HomePage';
import Status from './componets/Status/Status';
import StatusViewer from './componets/Status/StatusViewer';

function App() {
  return (
    <div className="">
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/status" element={<Status />}></Route>
        <Route path="/status/:userId" element={<StatusViewer />}></Route>

      </Routes>
    </div>
  );
}

export default App;
