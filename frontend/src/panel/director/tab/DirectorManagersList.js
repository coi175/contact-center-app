import {useNavigate} from "react-router-dom";
import managerService from "../../manager/manager.service";
import {Container} from "reactstrap";
import {useEffect, useState} from "react";

const DirectorManagerList = () => {
    const [managerList, setManagerList] = useState([]);
    let navigate = useNavigate();

    useEffect(() => {
        fetchManagers();
    }, [])

    const fetchManagers = async () => {
        const result = await managerService.getManagersByDirector();
        setManagerList(result);
    };

    const openOperatorPage = (event) => {
        let item = event.currentTarget;
        let managerId = item.children.item(0).getElementsByClassName("managerId").item(0).textContent;
        navigate("/director/manager/" + managerId, { state: { managerId: managerId } });
    }

    const managers = managerList.map(manager => {
        return (
            <div className="row" onClick={openOperatorPage}>
                <div className="col">
                    <div className="row list-line bg-dark justify-content-start">
                        <div className="col managerId list-line-item d-none">
                            {manager.managerId}
                        </div>
                        <div className="col-4 list-line-item">
                            {manager.firstName}
                        </div>
                        <div className="col-4 list-line-item">
                            {manager.lastName}
                        </div>
                        <div className="col-4 list-line-item">
                            {manager.email}
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
            {managers}
        </Container>
    )

}
export default DirectorManagerList;