import { Routes, Route } from 'react-router-dom';

import Index from '../pages/index/Index';
import LoginPage from '../pages/login/LoginPage';
import HomeUser from '../pages/userPages/homeUser/homeUser';

export default function Router() {
    
    return(
        <Routes>
            <Route path='/index' element={<Index/>}/>
            <Route path='/iniciarsessao' element={<LoginPage/>}/>
            <Route path='/user/home' element={<HomeUser/>}/>
        </Routes>
    );
};