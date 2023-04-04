import {useLocation, useNavigate} from "react-router-dom";
import {Container} from "reactstrap";
import ManagerContactList from "../tab/contact/ManagerContactList";
import ManagerTaskList from "../tab/task/ManagerTaskList";
import ManagerOperatorList from "../tab/operator/ManagerOperatorList";
import {useEffect, useState} from "react";
import ManagerOperatorInfo from "./tab/ManagerOperatorInfo";
import ManagerOperatorCallList from "./tab/ManagerOperatorCallList";
import ManagerOperatorTaskList from "./tab/ManagerOperatorTaskList";
import CommonService from "../../common/common.service";
import ManagerOperatorReport from "./tab/ManagerOperatorReport";

const ManagerOperator = () => {
    let navigate = useNavigate();
    const {state} = useLocation();
    const { operatorId } = state;
    const [showingPanel, setShowingPanel] = useState("operator-info-tab-id");
    const [manager, setManager] = useState(null);

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
                        <button onClick={setActive} id = "operator-info-tab-id" className="btn tab-button">
                            <span>Об операторе</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "operator-tasks-tab-id" className="btn tab-button">
                            <span>Задачи</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "operator-calls-tab-id" className="btn tab-button">
                            <span>Звонки</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "operator-report-tab-id" className="btn tab-button">
                            <span>Отчёт</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    {showingPanel === "operator-info-tab-id" ?
                        <ManagerOperatorInfo operatorId = {operatorId}/> : null
                    }
                    {showingPanel === "operator-tasks-tab-id" ?
                        <ManagerOperatorTaskList operatorId = {operatorId} manager = {manager}/> : null
                    }
                    {showingPanel === "operator-calls-tab-id" ?
                        <ManagerOperatorCallList operatorId = {operatorId}/> : null
                    }
                    {showingPanel === "operator-report-tab-id" ?
                         <ManagerOperatorReport operatorId = {operatorId}/> : null
                    }
                </div>
            </div>
        </Container>
    );
};

export default ManagerOperator;