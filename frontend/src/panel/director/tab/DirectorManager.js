import {useLocation, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import CommonService from "../../common/common.service";
import {Container} from "reactstrap";
import ManagerReport from "../../manager/tab/report/ManagerReport";


const DirectorManager = () => {
    let navigate = useNavigate();
    const {state} = useLocation();
    const { managerId } = state;
    const [showingPanel, setShowingPanel] = useState("operator-info-tab-id");
    const [director, setDirector] = useState(null);

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
                        <button onClick={setActive} id = "operator-info-tab-id" className="btn tab-button">
                            <span>Отчёт</span>
                        </button>
                    </div>
                </div>
                <div className="col-md-9 right-manager-contact-panel">
                    <ManagerReport managerId = {managerId}/>
                </div>
            </div>
        </Container>
    );
};

export default DirectorManager;