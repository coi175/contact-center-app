import api from "../../../../auth/service/api";
import {Container} from "reactstrap";
import DatePicker from "react-datepicker";
import {useEffect, useState} from "react";
import EffectChart from "../../../../charts/EffectChart";
import DurationChart from "../../../../charts/DurationChart";
import CallChart from "../../../../charts/CallChart";
import TaskChart from "../../../../charts/TaskChart";

const ManagerReport = (managerId) => {
    const [startDate, setStartDate] = useState();
    const [endDate, setEndDate] = useState();
    const [report, setReport] = useState([]);
    const [durations, setDurations] = useState([]);
    const [tasks, setTasks] = useState([]);
    const [ftasks, setFtasks] = useState([]);
    const [calls, setCalls] = useState([]);
    const [fcalls, setFcalls] = useState([]);
    const [dates, setDates] = useState([]);
    const [effRates, setEffRates] = useState([]);
    const [showReport, setShowReport] = useState(false);
    const [effective, setEffective] = useState(0);

    useEffect(() => {
    }, [])

    const getOperatorReport = () => {
        if (startDate !== undefined && endDate !== undefined && startDate <= endDate) {
            return api.post("http://localhost:8080/api/report/manager/get", {employeeId: managerId.managerId,
                startDate: startDate.toLocaleString(), endDate: endDate.toLocaleString()})
                .then((response) => {
                    setReport(response.data);
                    console.log(response.data);
                    processData(response.data);
                    setShowReport(true);
                    return response.data;
                }, (error) => {
                    alert("Не удалось получить отчёт");
                });
        } else {
            alert("Укажите начало и конец периода");
        }
    }

    const processData = (data) => {
        let taskList = [];
        let callList = [];
        let durationList = [];
        let dateList = [];
        let rateList = [];
        let fTaskList = [];
        let fCallList = [];
        let i = 0;
        let sum = 0;
        for (let report of data) {
            taskList.push(report.successTasks);
            callList.push(report.successCalls);
            durationList.push(report.averageDuration);
            dateList.push(report.reportDate);
            let d1 = report.successTasks + report.otherTasks;
            let d2 = report.successCalls + report.otherCalls;
            let d3 = report.averageDuration;
            console.log(report.reportDate + ", d1: " + d1);
            console.log(report.reportDate + ", d2: " + d2);
            console.log(report.reportDate + ", d3: " + d3);
            let rate = 0;
            if (d1 === 0 || d2 === 0 || d3 === 0) {
                rate = 0;
            } else {
                rate = (report.successTasks / d1 +
                    (report.successCalls / d2)) * (60 / d3);
            }
            if (rate > 0.01) {
                sum += rate;
                i++;
            }
            rateList.push(rate);
            fTaskList.push(report.otherTasks);
            fCallList.push(report.otherCalls);
        }
        setEffective(sum / i / data[0]?.operatorCount);
        setTasks(taskList);
        setCalls(callList);
        setDurations(durationList);
        setDates(dateList);
        setEffRates(rateList);
        setFtasks(fTaskList);
        setFcalls(fCallList);
    }

    return (
        <Container className={"pt-4"}>
            <div className="row d-flex justify-content-center align-items-center">
                <DatePicker
                    placeholderText="Начало периода"
                    isClerable
                    showIcon
                    dateFormat="dd/MM/yyyy"
                    selected={startDate}
                    onChange={(date) => setStartDate(date)} //only when value has changed
                />
                <DatePicker
                    placeholderText="Конец периода"
                    isClerable
                    showIcon
                    dateFormat="dd/MM/yyyy"
                    selected={endDate}
                    onChange={(date) => setEndDate(date)} //only when value has changed
                />
                <button onClick={getOperatorReport} className="btn tab-button report-button">Показать отчёт</button>
            </div>
            <div className="row d-flex justify-content-around mt-4 mb-4">
                <div className="col-3 rep-component-item">
                    Операторы:  {report[0]?.operatorCount}
                </div>
                <div className="col-3 rep-component-item">
                    Менеджеры:  {report[0]?.managerCount}
                </div>
                <div className="col-3 rep-component-item eff">
                    Эффективность:  {(effective * 10000 / 100).toFixed(2)}%
                </div>
            </div>
            {showReport ?
                <Container>
                    <EffectChart values = {effRates} dates = {dates}/>
                    <DurationChart durations = {durations} dates = {dates}/>
                    <CallChart calls = {calls} fcalls={fcalls} dates = {dates}/>
                    <TaskChart tasks = {tasks} ftasks={ftasks} dates = {dates}/>
                </Container>
                : null
            }

        </Container>
    )

}
export default ManagerReport;