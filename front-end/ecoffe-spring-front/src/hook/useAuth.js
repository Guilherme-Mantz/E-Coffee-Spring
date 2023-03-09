import { useState, useEffect } from "react";

import api from "../hook/api";
import history from '../history';

export default function useAuth() {
    const [ authenticated, setAuthenticated ] = useState(false);
    const [ loading, setLoading ] = useState(true);
    const [ dataCliente, setDataCliente ] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
    
        if (token) {
          api.defaults.headers.Authorization = `Bearer ${JSON.parse(token)}`;
          setAuthenticated(true);
        };
    
        setLoading(false);
    }, []);

    useEffect(() => {

        const token = localStorage.getItem('token');

        if (token) {
            setAuthenticated(true);
        }
        else {
            localStorage.removeItem('dataCliente');
        }

        if (authenticated) {

            const storedDataCliente = localStorage.getItem('dataCliente');

            if (storedDataCliente) {
                setDataCliente(JSON.parse(storedDataCliente));
            } 
            else {

                api.get('/cliente/get/data')
                    .then(res => {
                        setDataCliente(res.data);
                        localStorage.setItem('dataCliente', JSON.stringify(res.data));
                    })
                    .catch(err => {
                        if (err.response.status === 403) {

                            localStorage.removeItem('dataCliente');
                            localStorage.removeItem('token');                            

                            setAuthenticated(false);
                        }
                    });
            }
        }
    }, [authenticated]);

    async function handleLogin(form){
        const { data: { token } } = await api.post("/auth", form);

        localStorage.setItem("token",JSON.stringify(token));
        api.defaults.headers.Authorization = `Bearer ${token}`;
        
        setAuthenticated(true);
    };

    function handleLogout() {
        setAuthenticated(false);

        localStorage.removeItem('dataCliente');
        localStorage.removeItem('token');
        api.defaults.headers.Authorization = undefined;

        history.push('/iniciarsessao');
        window.location.reload();
    };

    return { authenticated, handleLogin, dataCliente ,handleLogout, loading };
};