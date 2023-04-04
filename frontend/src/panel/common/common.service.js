import api from "../../auth/service/api";

const API_URL = "http://localhost:8080/api/";

const getEmployeeId = () => {
    return api.get(API_URL + "employee/entity", {}).then(response => response.data);
}

const CommonService = {
    getEmployeeId,
};

export default CommonService;