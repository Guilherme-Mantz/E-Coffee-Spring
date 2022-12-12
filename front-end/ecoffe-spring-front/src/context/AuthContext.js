import { createContext } from "react";

import useAuth from "../hook/useAuth";

const Context = createContext();

function AuthProvider({children}){

    const  { authenticated, handleLogin, handleLogout, loading } = useAuth();

    return (
        <Context.Provider value={{ authenticated, handleLogin, loading,  handleLogout }}>
            {children}
        </Context.Provider>
    );
};

export { Context, AuthProvider };