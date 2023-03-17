import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import AuthService from "../auth/service/auth.service";
import TokenService from "../auth/service/token.service";
import api from "../auth/service/api";

const AppNavbar = () => {
    const [currentUser, setCurrentUser] = useState(undefined);
    const [currentTime, setCurrentTime] = useState("");

    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
        }
        fetchTime();
    }, []);

    const fetchTime = async () => {
        const result = await api.get('http://localhost:8080/api/time', {})
            .then(response => response.data);
        setCurrentTime(result);
    }

    const logOut = () => {
        AuthService.logout();
    };

    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/"} className="navbar-brand">
                Контакт-центр
            </Link>
            <div className={"time pt-1 ml-1 text-white"}>
                |-->   Время: {currentTime}
            </div>

            {currentUser ? (
                <div className="navbar-nav ml-auto">
                    <li className="nav-item">
                        {TokenService.getRoleFromToken() === "ADMIN" ?
                            <Link to={"/director"} className="nav-link">
                                Панель директора
                            </Link> : ""
                        }
                        {TokenService.getRoleFromToken() === "MODERATOR" ?
                            <Link to={"/manager"} className="nav-link">
                                Панель менеджера
                            </Link> : ""
                        }
                        {TokenService.getRoleFromToken() === "USER" ?
                            <Link to={"/operator"} className="nav-link">
                                Панель оператора
                            </Link> : ""
                        }
                    </li>
                    <li className="nav-item">
                        {TokenService.getRoleFromToken() === "ADMIN" || TokenService.getRoleFromToken() === "MODERATOR" ?
                            <Link to={"/logs"} className="nav-link">
                                Логи
                            </Link> : ""
                        }
                    </li>
                    <li className="nav-item">
                        {TokenService.getRoleFromToken() === "ADMIN" || TokenService.getRoleFromToken() === "MODERATOR" ?
                            <Link to={"/register"} className="nav-link">
                                Регистрация
                            </Link> : ""
                        }
                    </li>
                    <li className="nav-item">
                        <a href="/login" className="nav-link" onClick={logOut}>
                            Выход
                        </a>
                    </li>
                </div>
            ) : (
                <div className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link to={"/login"} className="nav-link">
                            Вход
                        </Link>
                    </li>
                </div>
            )}
        </nav>
    );
};

export default AppNavbar;