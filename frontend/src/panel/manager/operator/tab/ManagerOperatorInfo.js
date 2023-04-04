import {Container} from "reactstrap";
import {useEffect, useState} from "react";
import api from "../../../../auth/service/api";

const ManagerOperatorInfo = (operatorId) => {
    const [operatorInfo, setOperatorInfo] = useState({});

    useEffect(() => {
        updateOperatorInfo();
    }, [])

    const updateOperatorInfo = () => {
        const fetchData = async () => {
            const result = await getOperatorInfo(operatorId);
            if(result !== undefined) {
                setOperatorInfo(result);
            }
        };
        fetchData();
    }

    const getOperatorInfo = (id) => {
        return api.get("http://localhost:8080/api/manager/operator/" + id.operatorId, {})
            .then((response) => {
               return response.data;
            }, (error) => {
                alert("Не удалось получить информацию об операторе");
            });
    }

    return (
        <Container className={"pl-4 mt-4 pt-4"}>
            <div className="row info-line">
                <div className="col info-label">
                    ID оператора
                </div>
                <div className="col info-value">
                    {operatorInfo.operatorId}
                </div>
            </div>
            <div className="row info-line">
                <div className="col info-label">
                    Имя оператора
                </div>
                <div className="col info-value">
                    {operatorInfo.firstName}
                </div>
            </div>
            <div className="row info-line">
                <div className="col info-label">
                    Фамилия оператора
                </div>
                <div className="col info-value">
                    {operatorInfo.lastName}
                </div>
            </div>
            <div className="row info-line">
                <div className="col info-label">
                    Почта оператора
                </div>
                <div className="col info-value">
                    {operatorInfo.email}
                </div>
            </div>
        </Container>
    )

}
export default ManagerOperatorInfo;