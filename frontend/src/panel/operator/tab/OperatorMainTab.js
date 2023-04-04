import api from "../../../auth/service/api";
import {Container} from "reactstrap";
import {useEffect, useState} from "react";
import Select from "react-select";

const OperatorMainTab = (operatorId) => {
    const [taskInfo, setTaskInfo] = useState({});
    const [contactInfo, setContactInfo] = useState({});
    const [phoneCallList, setPhoneCallList] = useState([]);
    const [theTask, setTheTask] = useState(false);
    const [addCall, setAddCall] = useState(false);
    const [completeTask, setCompleteTask] = useState(false);
    const [selectedCallStatus, setSelectedCallStatus] = useState(null);
    const [selectedTaskStatus, setSelectedTaskStatus] = useState(null);
    const [duration, setDuration] = useState(null);
    const [taskNote, setTaskNote] = useState(null);

    const statusList = [{value: 'SUCCESS', label: 'Успешно'},
       {value: 'RECALL', label: 'На перезвон'},
       {value: 'NO_ANSWER', label: 'Нет ответа'},
       {value: 'FAULT', label: 'Неудачно'}];

    const statusTaskList = [{value: 'SUCCESS', label: 'Успешно'},
        {value: 'FAULT', label: 'Неудачно'}];

    useEffect(() => {
        updateTaskInfo();
        //updateContactInfo();
        //updatePhoneCallList();
    }, [])

    const updateTaskInfo = () => {
        const fetchData = async () => {
            const result = await getTaskInfo();
            if(result !== undefined) {
                setTaskInfo(result);
            }
        };
        fetchData();
    }
    const updateContactInfo = () => {
        const fetchData = async () => {
            const result = await getContactInfo();
            if(result !== undefined) {
                setContactInfo(result);
            }
        };
        fetchData();
    }
    const updatePhoneCallList = () => {
        const fetchData = async () => {
            const result = await getPhoneCallList(taskInfo.taskId);
            if(result !== undefined) {
                setPhoneCallList(result);
            }
        };
        fetchData();
    }

    const getTaskInfo = () => {
        return api.post("http://localhost:8080/api/operator/currentTask", {operatorId: operatorId.operatorId})
            .then((response) => {
                setTheTask(true);
                setTaskInfo(response.data);
                console.log(response.data);
                getContactInfo(response.data.contactId);
                getPhoneCallList(response.data.taskId);
                return response.data;
            }, (error) => {
                setTheTask(false);
            });
    }
    const getContactInfo = (contactId) => {
        return api.get("http://localhost:8080/api/contact/" + contactId, {})
            .then((response) => {
                setContactInfo(response.data);
                console.log(response.data);
                return response.data;
            }, (error) => {
                console.log("Не удалось получить контакт");
            });
    }
    const getPhoneCallList = (taskId) => {
        console.log(taskId);
        return api.post("http://localhost:8080/api/calls", {taskId: taskId})
            .then((response) => {
                setPhoneCallList(response.data);
                console.log(response.data);
                return response.data;
            }, (error) => {
                alert("Не удалось получить звонки");
            });
    }

    const handleSelectCallStatus = (data) => {
        setSelectedCallStatus(data);
    }

    const handleSelectTaskStatus = (data) => {
        setSelectedTaskStatus(data);
    }

    const handleDurationChange = (event) => {
        setDuration(event.target.value);
    };

    const handleChangeTaskNote = (event) => {
        setTaskNote(event.target.value);
    }

    const openCallPanel = () => {
        setAddCall(true);
    }

    const closeCallPanel = () => {
        setAddCall(false);
    }

    const openTaskPanel = () => {
        setCompleteTask(true);
    }

    const closeTaskPanel = () => {
        setCompleteTask(false);
    }

    const addCallAction = () => {
        if (duration !== null && duration > 0 && selectedCallStatus !== undefined && selectedCallStatus !== null) {
            api.post("http://localhost:8080/api/call/add", {taskId: taskInfo.taskId, contactId: contactInfo.contactId,
            operatorId: operatorId.operatorId, callDuration: duration, callStatus: selectedCallStatus.value})
                .then((response) => {
                    setAddCall(false);
                    updatePhoneCallList(taskInfo.taskId);
                }, (error) => {
                    alert("Не удалось получить звонки");
                });
        } else {
            alert("Укажите правильные значения статуса и длительности звонка");
        }
    }

    const addTaskAction = () => {
        if (selectedTaskStatus !== undefined) {
            let note = taskNote;
            if(note === null || note === undefined) {
                note = "";
            }
            api.post("http://localhost:8080/api/operator/task/close", {taskId: taskInfo.taskId, taskDescription: taskInfo.taskDescription,
                operatorId: operatorId.operatorId, taskStatus: selectedTaskStatus.value})
                .then((response) => {
                    setCompleteTask(false);
                    updateTaskInfo(taskInfo.taskId);
                }, (error) => {
                    alert("Не удалось завершить задачу");
                });
        } else {
            alert("Укажите правильные значения статуса");
        }
    }

    const calls = phoneCallList.map(call => {
        return (
            <div className="row contact-line">
                <div className="col">
                    <div className="row list-line bg-dark justify-content-start">
                        <div className="col contactId list-line-item d-none">
                            {call.phoneCallId}
                        </div>
                        <div className="col-4 list-line-item">
                            {call.callDate}
                        </div>
                        <div className="col-2 list-line-item">
                            {call.callDuration} с.
                        </div>
                        <div className="col-3 list-line-item">
                            {call.callStatus}
                        </div>
                        <div className="col-3 list-line-item">
                            {call.phoneNumber}
                        </div>
                    </div>
                </div>
            </div>)
    });

    return (
        <Container className={"pt-4"}>
            {theTask === true ?
                <Container>
                    <div className="row justify-content-around mb-4">
                        <div className="col-5 container-item-my">
                            <div className="row">
                                <div className="col-6 flex-column d-flex justify-content-center">
                                    <div className="my-label">Номер телефона:</div>
                                    <div className="phone-num">
                                        {contactInfo?.phoneNumber}
                                    </div>
                                    <div className="my-label mt-4">Статус задачи:</div>
                                    <div className="other">
                                        {taskInfo?.taskStatus}
                                    </div>

                                    <div className="my-label mt-4">Дата начала задачи:</div>
                                    <div className="other">
                                        {taskInfo?.startDate}
                                    </div>
                                </div>
                                <div className="col-6 d-flex flex-column justify-content-center">
                                    <div className="my-label">ФИО:</div>
                                    <div className="fio">
                                        {contactInfo?.fullName}
                                    </div>
                                    <div className="my-label mt-4">Тип контакта:</div>
                                    <div className="other">
                                        {contactInfo?.contactType}
                                    </div>

                                    <div className="my-label mt-4">Статус контакта:</div>
                                    <div className="other">
                                        {contactInfo?.contactStatus}
                                    </div>
                                </div>
                            </div>
                            <div className="row"></div>
                        </div>
                        <div className="col-6 container-item-my">
                            <div className="my-label">Описание задачи:</div>
                            <div className="descr-cont">
                                {taskInfo?.taskDescription}
                            </div>
                            <div className="my-label mt-4">Примечание к контакту:</div>
                            <div className="descr-cont-2">
                                {contactInfo?.contactNote}
                            </div>
                        </div>
                    </div>
                    <div className="row d-flex justify-content-around">
                        <div className="col-7 phone-call-container">
                            <h5 className="mt-2 mb-4 text-white">Звонки</h5>
                            <div className="row">
                                <div className="col">
                                    <div className="row  bg-dark justify-content-start list-line-header">
                                        <div className="col list-line-item d-none">
                                            ID
                                        </div>
                                        <div className="col-4 list-line-item">
                                            Дата
                                        </div>
                                        <div className="col-2 list-line-item">
                                            Длит.
                                        </div>
                                        <div className="col-3 list-line-item">
                                            Статус
                                        </div>
                                        <div className="col-3 list-line-item">
                                            Телефон
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {calls}
                        </div>
                        <div className="col-4 buttons-container">
                            <div className="row">
                                <div className="col">
                                    <button onClick={openCallPanel} className="btn tab-button phone-button">Добавить звонок</button>
                                </div>
                                <div className="col">
                                    <button onClick={openTaskPanel} className="btn tab-button end-task-button">Закончить задачу</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </Container>
            : <div className="d-flex justify-content-center text-center text-white">
                    <h2>Ни одна задача не взята в работу</h2>
                </div>
            }
            {addCall === true ?
                <Container className="add-phone-call-window">
                    <h3 className={"text-center text-white mt-2"}>Добавить звонок</h3>
                    <div className="row add-phone-call-inner-container">
                        <div className="col">
                            <div className="my-label mb-4">Длительность в секундах:</div>
                            <input type="number" className={"phone-duration-input w-80"}
                                   value={duration}
                                   onChange={handleDurationChange}
                                    placeholder=""
                            />
                        </div>
                        <div className="col">
                            <div className="my-label mb-4">Статус звонка:</div>
                            <Select className={"phone-status-select w-90"}
                                    options={statusList}
                                    placeholder="Статус"
                                    value={selectedCallStatus}
                                    onChange={handleSelectCallStatus}
                            />
                        </div>
                        <div className="col">
                            <div className="my-label mb-3">Действие:</div>
                            <button onClick={addCallAction} className="btn tab-button phone-button2">Добавить звонок</button>
                        </div>
                        <button onClick={closeCallPanel} className="custom-close">X</button>
                    </div>
                </Container>
                : null
            }

            {completeTask === true ?
                <Container className="add-phone-call-window">
                    <h3 className={"text-center text-white mt-2"}>Закончить задачу</h3>
                    <div className="row add-phone-call-inner-container">
                        <div className="col">
                            <div className="my-label mb-4">Примечание к задаче:</div>
                            <input type="text" className={"phone-duration-input w-80"}
                                   value={taskNote}
                                   onChange={handleChangeTaskNote}
                                   placeholder=""
                            />
                        </div>
                        <div className="col">
                            <div className="my-label mb-4">Статус задачи:</div>
                            <Select className={"phone-status-select w-90"}
                                    options={statusTaskList}
                                    placeholder="Статус"
                                    value={selectedTaskStatus}
                                    onChange={handleSelectTaskStatus}
                            />
                        </div>
                        <div className="col">
                            <div className="my-label mb-3">Действие:</div>
                            <button onClick={addTaskAction} className="btn tab-button end-task-button2">Закончить задачу</button>
                        </div>
                        <button onClick={closeTaskPanel} className="custom-close">X</button>
                    </div>
                </Container>
                : null
            }

        </Container>
    )

}
export default OperatorMainTab;