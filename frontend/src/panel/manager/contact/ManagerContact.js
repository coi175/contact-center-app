import {useLocation, useNavigate} from "react-router-dom";
import {Container} from "reactstrap";
import {useEffect, useState} from "react";
import ManagerContactInfo from "./tab/ManagerContactInfo";
import ManagerContactPhoneCallList from "./tab/ManagerContactPhoneCallList";
import ManagerContactTaskList from "./tab/ManagerContactTaskList";
import CommonService from "../../common/common.service";

const ManagerContact = () => {
    let navigate = useNavigate();
    const {state} = useLocation();
    const { contactId } = state;
    const [showingPanel, setShowingPanel] = useState("manager-contact-info-tab-id");
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
                        <button onClick={setActive} id = "manager-contact-info-tab-id" className="btn tab-button">
                            <span>О контакте</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "manager-contact-tasks-tab-id" className="btn tab-button">
                            <span>Задачи</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "manager-contact-calls-tab-id" className="btn tab-button">
                            <span>Звонки</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    {showingPanel === "manager-contact-info-tab-id" ?
                        <ManagerContactInfo contactId = {contactId}/> : null
                    }
                    {showingPanel === "manager-contact-tasks-tab-id" ?
                        <ManagerContactTaskList contactId = {contactId} manager = {manager}/> : null
                    }
                    {showingPanel === "manager-contact-calls-tab-id" ?
                        <ManagerContactPhoneCallList contactId = {contactId}/> : null
                    }
                </div>
            </div>
        </Container>
    );
};

export default ManagerContact;