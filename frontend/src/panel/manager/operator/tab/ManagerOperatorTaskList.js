import api from "../../../../auth/service/api";
import {Container} from "reactstrap";
import {useNavigate} from "react-router-dom";
import managerService from "../../manager.service";
import Select from "react-select";
import {useEffect, useState} from "react";

const ManagerOperatorTaskList = (operatorId, manager) => {
    const [tasks, setTasks] = useState([]);
    let navigate = useNavigate();


    useEffect(() => {
        fetchTasks();
    }, [])


    const openTaskPage = (event) => {
        let item = event.currentTarget;
        let taskId = item.children.item(0).getElementsByClassName("taskId").item(0).textContent;
        navigate("/manager/task/" + taskId, {state: {taskId: taskId}});
    }

    const fetchTasks = async (operatorStatus, taskStatus) => {
        const result = await managerService.getTasks({
            operatorId: operatorId.operatorId, managerId: manager.managerId
        });
        setTasks(result);
    };

    const taskList = tasks.map(task => {
        return (
            <div className="row" onClick={openTaskPage}>
                <div className="col">
                    <div className="row list-line bg-dark justify-content-start">
                        <div className="col taskId list-line-item d-none">
                            {task.taskId}
                        </div>
                        <div className="col-2 list-line-item">
                            {task.phoneNumber}
                        </div>
                        <div className="col-3 scrollable list-line-item">
                            {task.taskDescription}
                        </div>
                        <div className="col-1 list-line-item">
                            {task.taskStatus}
                        </div>
                        <div className="col-2 list-line-item">
                            {task.startDate}
                        </div>
                        <div className="col-2 list-line-item">
                            {task.endDate}
                        </div>
                        <div className="col-2 list-line-item">
                            {task.operatorFirstName !== null ? task.operatorFirstName + "  " : ""}

                            {task.operatorLastName !== null ? task.operatorLastName : ""}
                        </div>
                    </div>
                </div>
            </div>)
    });


    return (
        <Container className={"pl-4"}>
            <div className="row mt-4">
                <div className="col">
                    <div className="row list-line-header bg-dark justify-content-start">
                        <div className="col list-line-item d-none">
                            ID
                        </div>
                        <div className="col-2 list-line-item">
                            Номер телефона
                        </div>
                        <div className="col-3 list-line-item">
                            Описание
                        </div>
                        <div className="col-1 list-line-item">
                            Статус
                        </div>
                        <div className="col-2 list-line-item">
                            Дата начала
                        </div>
                        <div className="col-2 list-line-item">
                            Дата конца
                        </div>
                        <div className="col-2 list-line-item">
                            ФИО оператора
                        </div>
                    </div>
                </div>
            </div>
            {taskList}
        </Container>
    )
};
export default ManagerOperatorTaskList;