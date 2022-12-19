import { createContext } from "react";

import useAuth from "../hook/useAuth";

const Context = createContext();

function AuthProvider({children}){

    const  { authenticated, handleLogin, dataCliente, handleLogout, loading } = useAuth();

    return (
        <Context.Provider value={{ authenticated, handleLogin,dataCliente, handleLogout, loading }}>
            {children}
        </Context.Provider>
    );
};

export { Context, AuthProvider };