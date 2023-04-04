import TokenService from "./token.service";
import api from "./api";

const API_URL = "http://localhost:8080/api/auth/";

const register = (username, email, password, firstName, lastName) => {
    return api.post(API_URL + "register", {
        username,
        email,
        password,
        firstName,
        lastName
    });
};

const login = (username, password) => {
    return api
        .post(API_URL + "login", {
            username,
            password,
        })
        .then((response) => {
            if (response.data.accessToken) {
                TokenService.setUser(response.data);
            }

            return response.data;
        });
};

const logout = () => {
    TokenService.removeUser();
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
    register,
    login,
    logout,
    getCurrentUser,
};

export default AuthService;
