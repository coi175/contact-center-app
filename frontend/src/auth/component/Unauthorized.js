import React, { useState, useEffect } from "react";

const Unauthorized = () => {
    return (
        <div className="col-md-12 d-flex justify-content-center">
            <h2 className="mt-4">Вы не авторизированы для доступа к этой странице</h2>
        </div>
    );
};

export default Unauthorized;