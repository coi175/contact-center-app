
import {useEffect, useState} from "react";
import managerService from "../../manager.service";
import {Button, ButtonGroup, Container, Table} from "reactstrap";

const ManagerContactList = () => {
    const [contacts, setContacts] = useState([]);

    useEffect(() => {
        updateContacts();

    }, [])

    const updateContacts = () => {
        const fetchData = async () => {
            const result = await managerService.getContacts();
            setContacts(result);
        };
        fetchData();
    }

    const handleGetFromCRMClick = () => {
        managerService.getFromCRM();
        updateContacts();

    }

    const mark = (event) => {
        let item = event.currentTarget;
        item.classList.toggle("selected-list-item");
    }

    const createTasks = (event) => {
        let taskDescr = prompt("Введите описание для создаваемых задач");
        let contacts = document.getElementsByClassName("list-line");
        let contactsRequest = [];
        for (let contact of contacts) {
            if (contact.classList.contains("selected-list-item")) {
                contactsRequest.push({contactId: contact.children.item(0).textContent, taskDescription : taskDescr});
                contact.classList.toggle("selected-list-item");
            }
        }
        if (contactsRequest.length !== 0) {
            managerService.createTasksFromContacts(contactsRequest);
            updateContacts();
        }
    }

    const contactList = contacts.map(contact => {
        return (
            <div className="row">
                <div className="col-11">
                    <div onClick = {mark} className="row list-line bg-dark justify-content-start">
                        <div className="col contactId list-line-item d-none">
                            {contact.contactId}
                        </div>
                        <div className="col-2 list-line-item">
                            {contact.phoneNumber}
                        </div>
                        <div className="col-4 list-line-item">
                            {contact.fullName}
                        </div>
                        <div className="col list-line-item">
                            {contact.contactNote}
                        </div>
                        <div className="col-1 list-line-item">
                            {contact.contactStatus}
                        </div>
                        <div className="col-1 list-line-item">
                            {contact.contactType}
                        </div>
                    </div>
                </div>
                <div className="col-1 d-flex align-content-center">
                    <button className="btn list-line-item-button">
                        <span>-></span>
                    </button>
                </div>
            </div>)
    });


    return (
        <Container className={"pl-4"}>
            <div className="row mt-4 justify-content-between">
                <div className="col">
                    <div className="row">
                        <div className="col-2 tab-button-container justify-content-center">
                            <button onClick={createTasks} className="btn just-button create-task-button">
                                <span>Создать задачи</span>
                            </button>
                        </div>
                    </div>
                </div>
                <div className="col">
                    <div className="row">
                        <div className="col tab-button-container d-flex justify-content-end">
                            <button onClick={managerService.pushToCRM} className="btn just-button">
                                <span>Загрузить в CRM</span>
                            </button>
                        </div>
                        <div className="col tab-button-container justify-content-start">
                            <button onClick={handleGetFromCRMClick} className="btn just-button">
                                <span>Получить из CRM</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col-11">
                    <div className="row list-line-header bg-dark justify-content-start">
                        <div className="col list-line-item d-none">
                            ID
                        </div>
                        <div className="col-2 list-line-item">
                            Номер телефона
                        </div>
                        <div className="col-4 list-line-item">
                            ФИО
                        </div>
                        <div className="col list-line-item">
                            Заметка
                        </div>
                        <div className="col-1 list-line-item">
                            Статус
                        </div>
                        <div className="col-1 list-line-item">
                            Тип
                        </div>
                    </div>
                </div>
                <div className="col-1">

                </div>
            </div>

            {contactList}
        </Container>
    )

}
export default ManagerContactList;
