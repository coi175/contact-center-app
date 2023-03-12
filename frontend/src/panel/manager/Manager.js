import {useNavigate} from "react-router-dom";
import React, {useRef, useState} from "react";


const Manager = () => {
    let navigate = useNavigate();

    const form = useRef();
    const checkBtn = useRef();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    return (
        <div className="">

        </div>
    );
};

export default Manager;
