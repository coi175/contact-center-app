import {useEffect, useState} from "react";
import managerService from "../../manager.service";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import Select from "react-select";
import {useNavigate} from "react-router-dom";


const ManagerOperatorList = () => {
    const [operatorList, setOperatorList] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        fetchOperators();
    }, [])

    const fetchOperators = async () => {
        const result = await managerService.getOperators();
        setOperatorList(result);
    };

    const openOperatorPage = (event) => {
        let item = event.currentTarget;
        let operatorId = item.children.item(0).getElementsByClassName("operatorId").item(0).textContent;
        navigate("/manager/operator/" + operatorId, { state: { operatorId: operatorId } });
    }

    const operators = operatorList.map(operator => {
        return (
            <div className="row" onClick={openOperatorPage}>
                <div className="col">
                    <div className="row list-line bg-dark justify-content-start">
                        <div className="col operatorId list-line-item d-none">
                            {operator.operatorId}
                        </div>
                        <div className="col-4 list-line-item">
                            {operator.firstName}
                        </div>
                        <div className="col-4 list-line-item">
                            {operator.lastName}
                        </div>
                        <div className="col-4 list-line-item">
                            {operator.email}
                        </div>
                    </div>
                </div>
            </div>)
    });


    return (
        <Container className={"pl-4 pb-4"}>
            <div className="row mt-4">
                <div className="col">
                    <div className="row list-line-header bg-dark justify-content-start">
                        <div className="col list-line-item d-none">
                            ID
                        </div>
                        <div className="col-4 list-line-item">
                            Имя
                        </div>
                        <div className="col-4 list-line-item">
                            Фамилия
                        </div>
                        <div className="col-4 list-line-item">
                            Email
                        </div>
                    </div>
                </div>
            </div>
            {operators}
        </Container>
    )

}
export default ManagerOperatorList;