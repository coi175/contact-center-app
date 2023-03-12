import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Login from "./auth/component/Login";
import Register from "./auth/component/Register";
import AppNavbar from "./navigation/AppNavbar";
import Unauthorized from "./auth/component/Unauthorized";
import Auth from "./auth/component/Auth";

const App = () => {
  return (
      <div>
        <AppNavbar/>
        <div className="container mt-3">
            <Routes>
                <Route path="/login" element={<Login/>} />
                <Route path="/unauthorized" element={<Unauthorized/>}/>
                <Route element={<Auth allowedRoles={["MANAGER", "ADMIN"]} />}>
                    <Route path="/register" element={<Register/>} />
                </Route>
                <Route element={<Auth allowedRoles={["MANAGER"]} />}>
                    <Route path="/manager" element={<Manager/>} />
                </Route>
            </Routes>
        </div>
      </div>
  );
};

export default App;
