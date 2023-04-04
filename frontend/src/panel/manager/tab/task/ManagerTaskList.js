import {useEffect, useState} from "react";
import managerService from "../../manager.service";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import Select from "react-select";
import {useNavigate} from "react-router-dom";


const ManagerTaskList = ({manager}) => {
    const [tasks, setTasks] = useState([]);
    const [statusList, setStatusList] = useState([]);
    const [operatorList, setOperatorList] = useState([]);
    const [selectedStatus, setSelectedStatus] = useState([]);
    const [selectedOperator, setSelectedOperator] = useState([]);
    let navigate = useNavigate();


    useEffect(() => {
        fetchStatus();
        fetchOperators();
        fetchTasks();
    }, [])


    const openTaskPage = (event) => {
        let item = event.currentTarget;
        let taskId = item.children.item(0).getElementsByClassName("taskId").item(0).textContent;
        navigate("/manager/task/" + taskId, { state: { taskId: taskId } });
    }

    const fetchTasks = async (operatorStatus, taskStatus) => {
        const result = await managerService.getTasks({operatorId: operatorStatus !== undefined ? operatorStatus : selectedOperator?.value,
            taskStatus: taskStatus !== undefined ? taskStatus : selectedStatus?.value, managerId: manager.managerId});
        setTasks(result);
    };
    const fetchOperators = async () => {
        const result = await managerService.getOperators();
        let map = [];
        for (let item of result) {
            map.push({value: item.operatorId, label: item.firstName + " " + item.lastName});
        }
        map.push({value: null, label: "без фильтра"});
        setOperatorList(map);
    };
    const fetchStatus = async () => {
        const result = await managerService.getTaskStatus();
        let map = [];
        for (let item of result) {
            map.push({value: item, label: item});
        }
        map.push({value: null, label: "без фильтра"});
        setStatusList(map);
    };




    const handleSelectStatus = (data) => {
        setSelectedStatus(data);
        fetchTasks(undefined, data?.value);
    }

    const handleSelectOperator = (data) => {
        setSelectedOperator(data);
        fetchTasks(data?.value, undefined);
    }


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
            <div className="row mt-4 mb-4">
                <div className="col d-flex justify-content-start">
                    <div className="row w-100">
                        <Select className={"mr-4 w-25"}
                            options={statusList}
                            placeholder="Статус"
                            value={selectedStatus}
                            onChange={handleSelectStatus}
                        />
                        <Select className={"ml-4 w-25"}
                            options={operatorList}
                            placeholder="Оператор"
                            value={selectedOperator}
                            onChange={handleSelectOperator}
                        />
                    </div>
                </div>
            </div>
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
export default ManagerTaskList;
