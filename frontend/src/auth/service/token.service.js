import jwt_decode from "jwt-decode";

const getLocalRefreshToken = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    return user?.refreshToken;
};
const getLocalAccessToken = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    return user?.accessToken;
};

const getRoleFromToken = () => {
    let user = JSON.parse(localStorage.getItem("user"));
    let decoded = jwt_decode(user?.accessToken);
    return decoded.role;
}

const updateLocalAccessToken = (token) => {
    let user = JSON.parse(localStorage.getItem("user"));
    user.accessToken = token;
    localStorage.setItem("user", JSON.stringify(user));
};

const getUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

const setUser = (user) => {
    console.log(JSON.stringify(user));
    localStorage.setItem("user", JSON.stringify(user));
};

const removeUser = () => {
    localStorage.removeItem("user");
};

const TokenService = {
    getLocalRefreshToken,
    getLocalAccessToken,
    updateLocalAccessToken,
    getRoleFromToken,
    getUser,
    setUser,
    removeUser,
};

export default TokenService;
