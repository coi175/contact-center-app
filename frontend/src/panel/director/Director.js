import {useNavigate} from "react-router-dom";
import CommonService from "../common/common.service";
import {Container} from "reactstrap";
import ManagerContactList from "../manager/tab/contact/ManagerContactList";
import ManagerTaskList from "../manager/tab/task/ManagerTaskList";
import ManagerOperatorList from "../manager/tab/operator/ManagerOperatorList";
import ManagerReport from "../manager/tab/report/ManagerReport";
import {useEffect, useRef, useState} from "react";
import DirectorManagerList from "./tab/DirectorManagersList";
import DirectorReport from "./tab/DirectorReport";

const Director = () => {
    let navigate = useNavigate();
    const form = useRef();
    const checkBtn = useRef();
    //const {state} = useLocation();
    //const { id, color } = state; // Read values passed on state
    const [showingPanel, setShowingPanel] = useState("managers-btn");
    const [director, setDirector] = useState({});

    useEffect(() => {
        getDirector();
    },[])

    const getDirector = async () => {
        let director = await CommonService.getEmployeeId();
        setDirector(director);
        console.log(director);
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
                        <button onClick={setActive} id = "managers-btn" className="btn tab-button">
                            <span>Менеджеры</span>
                        </button>
                    </div>
                    <div className="row tab-button-container justify-content-center">
                        <button onClick={setActive} id = "reports-btn" className="btn tab-button">
                            <span>Отчёты</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    {showingPanel === "managers-btn" ?
                        <DirectorManagerList/> : null
                    }
                    {showingPanel === "reports-btn" ?
                        <DirectorReport directorId = {director.directorId}/> : null
                    }
                </div>
            </div>
        </Container>
    );
};

export default Director;