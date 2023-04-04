import {useLocation, useNavigate} from "react-router-dom";
import {Container} from "reactstrap";
import {useState} from "react";
import ManagerTaskPhoneCallList from "./tab/ManagerTaskPhoneCallList";
import ManagerTaskInfo from "./tab/ManagerTaskInfo";

const ManagerTask = () => {
    let navigate = useNavigate();
    const {state} = useLocation();
    const { taskId } = state;
    const [showingPanel, setShowingPanel] = useState("manager-info-tab-id");

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
                        <button onClick={setActive} id = "manager-info-tab-id" className="btn tab-button">
                            <span>О задаче</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "manager-calls-tab-id" className="btn tab-button">
                            <span>Звонки</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    {showingPanel === "manager-info-tab-id" ?
                       <ManagerTaskInfo taskId = {taskId}/> : null
                    }
                    {showingPanel === "manager-calls-tab-id" ?
                        <ManagerTaskPhoneCallList taskId = {taskId}/> : null
                    }
                </div>
            </div>
        </Container>
    );
};

export default ManagerTask;