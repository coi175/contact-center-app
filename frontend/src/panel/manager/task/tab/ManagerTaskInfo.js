import {useEffect, useState} from "react";
import api from "../../../../auth/service/api";
import {Container} from "reactstrap";
import {useNavigate} from "react-router-dom";
const ManagerTaskInfo = (taskId) => {
    const [taskInfo, setTaskInfo] = useState({});
    let navigate = useNavigate();

    useEffect(() => {
        updateTaskInfo();
    }, [])

    const updateTaskInfo = () => {
        const fetchData = async () => {
            const result = await getTaskInfo(taskId);
            if(result !== undefined) {
                setTaskInfo(result);
            }
        };
        fetchData();
    }

    const openContactPage = () => {
        navigate("/manager/contact/" + taskInfo.contactId, {state: {contactId: taskInfo.contactId}});
    }

    const openOperatorPage = () => {
        navigate("/manager/operator/" + taskInfo.operatorId, {state: {operatorId: taskInfo.operatorId}});
    }

    const getTaskInfo = (id) => {
        return api.get("http://localhost:8080/api/task/" + id.taskId, {})
            .then((response) => {
                return response.data;
            }, (error) => {
                alert("Не удалось получить информацию об операторе");
            });
    }

    return (
        <Container className={"pl-4 mt-2 pt-2"}>
            <div className="row info-line">
                <div className="col-4 info-label">
                    ID задачи
                </div>
                <div className="col info-value">
                    {taskInfo.taskId}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Описание задачи
                </div>
                <div className="col info-value">
                    {taskInfo.taskDescription}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Статус
                </div>
                <div className="col info-value">
                    {taskInfo.taskStatus}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Дата начала
                </div>
                <div className="col info-value">
                    {taskInfo.startDate}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Дата конца
                </div>
                <div className="col info-value">
                    {taskInfo.endDate}
                </div>
            </div>



            <div className="row task-info-button-line">
                <div className="col task-info-button-col justify-content-center">
                    Контакт:
                    <div onClick={openContactPage} className="open-button">
                        {taskInfo.phoneNumber}
                    </div>
                </div>
                <div className="col task-info-button-col">
                    Оператор:
                    <div onClick={openOperatorPage} className="open-button">
                        {taskInfo.operatorFirstName !== null ? taskInfo.operatorFirstName + "  " : ""}

                        {taskInfo.operatorLastName !== null ? taskInfo.operatorLastName : ""}
                    </div>
                </div>
            </div>
        </Container>
    )
};

export default ManagerTaskInfo;