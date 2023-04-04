import {useLocation, useNavigate} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import {Container} from "reactstrap";
import "../../Custom.css";
import CommonService from "../common/common.service";
import ManagerOperatorCallList from "../manager/operator/tab/ManagerOperatorCallList";
import OperatorTaskList from "./tab/OperatorTaskList";
import OperatorMainTab from "./tab/OperatorMainTab";

const Operator = () => {
    let navigate = useNavigate();
    const [operator, setOperator] = useState({});
    //const {state} = useLocation();
    //const { id, color } = state; // Read values passed on state
    const [showingPanel, setShowingPanel] = useState(null);

    useEffect(() => {
        getOperator();
    },[])

    const getOperator = async () => {
        let operator = await CommonService.getEmployeeId();
        setOperator(operator);
        console.log(operator);
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
                        <button onClick={setActive} id = "operator-main-btn" className="btn tab-button">
                            <span>Главная</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "operator-tasks-btn" className="btn tab-button">
                            <span>Задачи</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "operator-call-btn" className="btn tab-button">
                            <span>Звонки</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    {showingPanel === "operator-main-btn" ?
                       <OperatorMainTab operatorId = {operator.operatorId}/> : null
                    }
                    {showingPanel === "operator-tasks-btn" ?
                        <OperatorTaskList operatorId = {operator.operatorId} managerId = {operator.managerId}/> : null
                    }
                    {showingPanel === "operator-call-btn" ?
                        <ManagerOperatorCallList operatorId = {operator.operatorId}/> : null
                    }
                </div>
            </div>
        </Container>
    );
};

export default Operator;