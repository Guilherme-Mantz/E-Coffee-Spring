import { useState, useEffect } from "react";

import api from "../hook/api";
import history from '../history';

export default function useAuth() {
    const [ authenticated, setAuthenticated ] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('token');
    
        if (token) {
          api.defaults.headers.Authorization = `Bearer ${JSON.parse(token)}`;
          setAuthenticated(true);
        };
    
        setLoading(false);
    }, []);

    async function handleLogin(form){
        const { data: { token } } = await api.post("/auth", form);

        localStorage.setItem("token",JSON.stringify(token));
        api.defaults.headers.Authorization = `Bearer ${token}`;
        
        setAuthenticated(true);
        history.push('/');

        window.location.reload();
    };

    function handleLogout() {
        setAuthenticated(false);

        localStorage.removeItem('token');
        api.defaults.headers.Authorization = undefined;

        history.push('/iniciarsessao');
        window.location.reload();
    };

    return { authenticated, handleLogin, handleLogout, loading };
};