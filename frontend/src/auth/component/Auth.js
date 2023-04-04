import { useLocation, Navigate, Outlet } from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import React from "react";
import AuthService from "../service/auth.service";
import TokenService from "../service/token.service";

const Auth = ({ allowedRoles }) => {
    const [currentUser, setCurrentUser] = useState(AuthService.getCurrentUser());
    const location = useLocation();
    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
        }
    }, []);

    if (currentUser === null || currentUser === undefined) {
        return <Navigate to="/login" state={{ from: location }} replace />
    } else if (allowedRoles.find((role) => role === TokenService.getRoleFromToken())) {
        return <Outlet />
    } else {
        return <Navigate to="/unauthorized" state={{ from: location }} replace />
    }
};

export default Auth;