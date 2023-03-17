import {useNavigate} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {Container} from "reactstrap";
import ManagerContactList from "../manager/tab/contact/ManagerContactList";
import ManagerTaskList from "../manager/tab/task/ManagerTaskList";
import ManagerOperatorList from "../manager/tab/operator/ManagerOperatorList";
import api from "../../auth/service/api";

const Log = () => {
    const [logList, setLogList] = useState([]);

    useEffect(() => {
        getLogs();
    }, [])

    const getLogs = async () => {
        const result = await api.get('http://localhost:8080/api/logs', {})
            .then(response => response.data);
        setLogList(result);
    }

    const logs = logList.map(log => {
        return (
            <div className="row log-line">
                    <div className="col log-line-item">
                        {log.actionLogId}
                    </div>
                    <div className="col-5 log-line-item">
                        {log.logMessage}
                    </div>
                    <div className="col-2 log-line-item">
                        {log.dateTime}
                    </div>
                    <div className="col-1 log-line-item">
                        {log.actionType}
                    </div>
                    <div className="col-1 log-line-item">
                        {log.employeeId}
                    </div>
                    <div className="col-2 log-line-item">
                        {log.employeeLastName + " " + log.employeeFirstName}
                    </div>
            </div>)
    });

    return (
        <Container className={"log-container"} fluid>
            <div className="row log-header">
                <div className="col log-line-item-header">
                    ID
                </div>
                <div className="col-5 log-line-item-header">
                    Сообщение
                </div>
                <div className="col-2 log-line-item-header">
                    Время
                </div>
                <div className="col-1 log-line-item-header">
                    Тип
                </div>
                <div className="col-1 log-line-item-header">
                    ID сотрудника
                </div>
                <div className="col-2 log-line-item-header">
                    ФИО сотрудника
                </div>
            </div>
            {logs}
        </Container>
    );
};

export default Log;