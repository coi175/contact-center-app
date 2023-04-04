import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import api from "../../../../auth/service/api";
import {Container} from "reactstrap";

const ManagerContactInfo = (contactId) => {
    const [contactInfo, setContactInfo] = useState({});
    let navigate = useNavigate();

    useEffect(() => {
        updateContactInfo();
    }, [])

    const updateContactInfo = () => {
        const fetchData = async () => {
            const result = await getContactInfo(contactId);
            if(result !== undefined) {
                setContactInfo(result);
            }
        };
        fetchData();
    }

    const getContactInfo = (id) => {
        return api.get("http://localhost:8080/api/contact/" + id.contactId, {})
            .then((response) => {
                return response.data;
            }, (error) => {
                alert("Не удалось получить информацию о контакте");
            });
    }

    return (
        <Container className={"pl-4 mt-2 pt-2"}>
            <div className="row info-line">
                <div className="col-4 info-label">
                    ID контакта
                </div>
                <div className="col info-value">
                    {contactInfo.contactId}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Номер телефона
                </div>
                <div className="col info-value">
                    {contactInfo.phoneNumber}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    ФИО
                </div>
                <div className="col info-value">
                    {contactInfo.fullName}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Заметка
                </div>
                <div className="col info-value">
                    {contactInfo.contactNote}
                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Тип:
                </div>
                <div className="col info-value">
                    {contactInfo.contactType}

                </div>
            </div>
            <div className="row info-line">
                <div className="col-4  info-label">
                    Cтатус:
                </div>
                <div className="col info-value">
                    {contactInfo.contactStatus}
                </div>
            </div>
        </Container>
    )
};

export default ManagerContactInfo;