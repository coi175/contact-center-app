import {Link} from "react-router-dom";
import React, {useEffect, useState} from "react";
import AuthService from "../auth/service/auth.service";
import TokenService from "../auth/service/token.service";

const AppNavbar = () => {
    const [currentUser, setCurrentUser] = useState(undefined);


    useEffect(() => {
        const user = AuthService.getCurrentUser();

        if (user) {
            setCurrentUser(user);
        }
    }, []);

    const logOut = () => {
        AuthService.logout();
    };

    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/"} className="navbar-brand">
                Contact-center
            </Link>
            <div className="navbar-nav mr-auto">

            </div>

            {currentUser ? (
                <div className="navbar-nav ml-auto">
                    <li className="nav-item">
                        {TokenService.getRoleFromToken() === "ADMIN" ?
                            <Link to={"/admin"} className="nav-link">
                                Панель администратора
                            </Link> : ""
                        }
                        {TokenService.getRoleFromToken() === "MODERATOR" ?
                            <Link to={"/moderator"} className="nav-link">
                                Панель модератора
                            </Link> : ""
                        }
                        {TokenService.getRoleFromToken() === "USER" ?
                            <Link to={"/user"} className="nav-link">
                                Панель пользователя
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