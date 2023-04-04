import {useNavigate} from "react-router-dom";
import managerService from "../../manager/manager.service";
import {Container} from "reactstrap";
import Select from "react-select";
import {useEffect, useState} from "react";
import api from "../../../auth/service/api";

const OperatorTaskList = ({operatorId: operatorId, managerId: managerId}) => {
    const [tasks, setTasks] = useState([]);
    let navigate = useNavigate();


    useEffect(() => {
        fetchTasks();
    }, [])

    const takeTaskRequest = (taskId, operatorId) => {
        api.post("http://localhost:8080/api/operator/takeTask", {operatorId: operatorId, taskId: taskId})
            .then((response => response.data))
            .then(() => {
                window.location.reload();
            }, (error) => {
                const resMessage = error.response.data;

                alert(resMessage);
            });
    }
    const takeTaskToOperator = async (event) => {
        let item = event.currentTarget;
        let taskId = item.children.item(0).getElementsByClassName("taskId").item(0).textContent;
        let res = window.confirm("Вы уверены, что хотите взять задачу " + taskId + "?");
        if (res) {
            await takeTaskRequest(taskId, operatorId);
        }
    }

    const fetchTasks = async () => {
        const result = await managerService.getTasksToOperator({
            operatorId: operatorId,
            managerId: managerId
        });
        setTasks(result);
    }

    const taskList = tasks.map(task => {
        return (
            <div className="row" onClick={takeTaskToOperator}>
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
        <Container className={"pl-4 mt-4"}>
            <div className="row">
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

}
export default OperatorTaskList;