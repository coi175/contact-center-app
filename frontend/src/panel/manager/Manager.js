import {useLocation, useNavigate} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {Container} from "reactstrap";
import "../../Custom.css";
import ManagerContactList from "./tab/contact/ManagerContactList";
import ManagerTaskList from "./tab/task/ManagerTaskList";
import ManagerOperatorList from "./tab/operator/ManagerOperatorList";
import CommonService from "../common/common.service";
import ManagerReport from "./tab/report/ManagerReport";

const Manager = () => {
    let navigate = useNavigate();
    const form = useRef();
    const checkBtn = useRef();
    //const {state} = useLocation();
    //const { id, color } = state; // Read values passed on state
    const [showingPanel, setShowingPanel] = useState("contacts-btn");
        const [manager, setManager] = useState({});

    useEffect(() => {
        getManager();
    },[])

    const getManager = async () => {
        let manager = await CommonService.getEmployeeId();
        setManager(manager);
        console.log(manager);
    }

    const setActive = (event) => {
        let button = event.currentTarget;
        let buttons = button.ownerDocument.getElementsByClassName("tab-button");
        for (let btn of buttons) {
            btn.classList.remove("tab-button-active");
        }
        button.classList.add("tab-button-active");
        setShowingPanel(button.id);
    }



    return (
        <Container fluid>
            <div className="row justify-content-around">
                <div className="col-md-2 left-manager-panel bg-dark">
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "contacts-btn" className="btn tab-button">
                            <span>Контакты</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "tasks-btn" className="btn tab-button">
                            <span>Задачи</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "operators-btn" className="btn tab-button">
                            <span>Операторы</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "report-btn" className="btn tab-button">
                            <span>Отчёт</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    {showingPanel === "contacts-btn" ?
                        <ManagerContactList/> : null
                    }
                    {showingPanel === "tasks-btn" ?
                       <ManagerTaskList manager = {manager}/> : null
                    }
                    {showingPanel === "operators-btn" ?
                        <ManagerOperatorList/> : null
                    }
                    {showingPanel === "report-btn" ?
                        <ManagerReport managerId = {manager.managerId}/> : null
                    }
                </div>
            </div>
        </Container>
    );
};

export default Manager;
