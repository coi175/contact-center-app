import Manager from "./Manager";
import api from "../../auth/service/api";

const API_URL = "http://localhost:8080/api/";


const pushToCRM = () => {
    return api
        .post(API_URL + "contacts/manager/crm/push", {})
        .then(() => {
            alert("Контакты успешно переданы в CRM");
        }, (error) => {
            console.log("Произошла непредвиденная ошибка при передаче контактов в CRM");
    });
};

const getFromCRM = () => {
    return api
        .post(API_URL + "contacts/manager/crm/receive", {})
        .then(() => {
            alert("Контакты успешно загружены из CRM");
        }, (error) => {
            console.log("Произошла непредвиденная ошибка при загрузке контактов из CRM");
        });
};

const getContacts = () => {
    return api
        .get(API_URL + "manager/contacts", {})
        .then((response => response.data));
};

const getTasks = (request) => {
    return api
        .post(API_URL + "tasks", request)
        .then((response => response.data));
};

const getTasksToOperator = (request) => {
    return api
        .post(API_URL + "operator/tasks", request)
        .then((response => response.data));
};

const getOperators = () => {
    return api
        .get(API_URL + "manager/operators", {})
        .then((response => response.data));
};

const getManagersByDirector = () => {
    return api
        .get(API_URL + "director/managers", {})
        .then((response => response.data));
};

const getTaskStatus = () => {
    return api
        .get(API_URL + "tasks/status", {})
        .then((response => response.data));
};

const createTasksFromContacts = (contacts) => {
    return api
        .post(API_URL + "tasks/manager/create/auto", contacts)
        .then(() => {
            alert("Задачи успешно созданы");
        }, (error) => {
            console.log("Произошла непредвиденная ошибка при создании задач");
        });
};


const ManagerService = {
    pushToCRM,
    getFromCRM,
    getContacts,
    createTasksFromContacts,
    getTaskStatus,
    getTasks,
    getOperators,
    getManagersByDirector,
    getTasksToOperator
};

export default ManagerService;