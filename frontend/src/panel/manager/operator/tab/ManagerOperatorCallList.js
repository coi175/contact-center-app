
import {useEffect, useState} from "react";
import managerService from "../../manager.service";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {useNavigate} from "react-router-dom";
import api from "../../../../auth/service/api";

const ManagerOperatorCallList = (operatorId) => {
    const [callList, setCallList] = useState([]);

    useEffect(() => {
        updateCalls();

    }, [])

    const updateCalls = () => {
        const fetchData = async () => {
            const result = await getCalls(operatorId);
            setCallList(result);
        };
        fetchData();
    }

    const getCalls = (operatorId) => {
        return api.post("http://localhost:8080/api/calls", {operatorId: operatorId.operatorId})
            .then((response => response.data));
    }


    const calls = callList.map(call => {
        return (
            <div className="row contact-line">
                <div className="col">
                    <div className="row list-line bg-dark justify-content-start">
                        <div className="col contactId list-line-item d-none">
                            {call.phoneCallId}
                        </div>
                        <div className="col-3 list-line-item">
                            {call.callDate}
                        </div>
                        <div className="col-2 list-line-item">
                            {call.callDuration} с.
                        </div>
                        <div className="col-2 list-line-item">
                            {call.callStatus}
                        </div>
                        <div className="col-3 list-line-item">
                            {call.phoneNumber}
                        </div>
                        <div className="col-2 list-line-item">
                            {call.taskId}
                        </div>
                    </div>
                </div>
            </div>)
    });


    return (
        <Container className={"pl-4 mb-4"}>
            <div className="row mt-4">
                <div className="col">
                    <div className="row list-line-header bg-dark justify-content-start">
                        <div className="col list-line-item d-none">
                            ID
                        </div>
                        <div className="col-3 list-line-item">
                            Дата
                        </div>
                        <div className="col-2 list-line-item">
                            Длительность
                        </div>
                        <div className="col-2 list-line-item">
                            Статус
                        </div>
                        <div className="col-3 list-line-item">
                            Номер телефона
                        </div>
                        <div className="col-2 list-line-item">
                            ID задачи
                        </div>
                    </div>
                </div>
            </div>
            {calls}
        </Container>
    )

}
export default ManagerOperatorCallList;