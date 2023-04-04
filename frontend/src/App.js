import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Login from "./auth/component/Login";
import Register from "./auth/component/Register";
import AppNavbar from "./navigation/AppNavbar";
import Unauthorized from "./auth/component/Unauthorized";
import Auth from "./auth/component/Auth";
import Manager from "./panel/manager/Manager";
import Log from "./panel/common/Log";
import ManagerOperator from "./panel/manager/operator/ManagerOperator";
import ManagerTask from "./panel/manager/task/ManagerTask";
import ManagerContact from "./panel/manager/contact/ManagerContact";
import Operator from "./panel/operator/Operator";
import Director from "./panel/director/Director";
import DirectorManager from "./panel/director/tab/DirectorManager";

const App = () => {
  return (
      <div className={"text-center"}>
        <AppNavbar/>
        <div className="container mt-3">
            <Routes>
                <Route path="/login" element={<Login/>} />
                <Route path="/unauthorized" element={<Unauthorized/>}/>
                <Route element={<Auth allowedRoles={["MODERATOR", "ADMIN"]} />}>
                    <Route path="/register" element={<Register/>} />
                </Route>
                <Route path="/logs" element={<Log/>} />
                <Route element={<Auth allowedRoles={["MODERATOR"]} />}>
                    <Route path="/manager" element={<Manager/>} />
                </Route>
                <Route element={<Auth allowedRoles={["MODERATOR"]} />}>
                    <Route path="/manager/contact/*" element={<ManagerContact/>} />
                </Route>
                <Route element={<Auth allowedRoles={["MODERATOR"]} />}>
                    <Route path="/manager/task/*" element={<ManagerTask/>} />
                </Route>
                <Route element={<Auth allowedRoles={["MODERATOR"]} />}>
                    <Route path="/manager/operator/*" element={<ManagerOperator/>} />
                </Route>
                <Route element={<Auth allowedRoles={["USER"]} />}>
                    <Route path="/operator" element={<Operator/>} />
                </Route>
                <Route element={<Auth allowedRoles={["ADMIN"]} />}>
                    <Route path="/director" element={<Director/>} />
                </Route>
                <Route element={<Auth allowedRoles={["ADMIN"]} />}>
                    <Route path="/director/manager/*" element={<DirectorManager/>} />
                </Route>
            </Routes>
        </div>
      </div>
  );
};

export default App;
