import React, { useState } from 'react';
import helper from "../../helper";
import {Link} from "react-router-dom";

function ResetPassword() {
    const [password, setPassword] = useState("");

    const handleChange = (e) => {
        setPassword(e.target.value );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Add your registration logic here
        return helper.api("GET","/auth/reset-password",password)
            .then((response) => {
                if(response) {
                    debugger;
                }
            })
    };

    return (
        <div className="container-fluid page">
            <div>
                <h2 className={"mt-5 mb-5"}>Welcome to the period app</h2>
                <div className={"auth-form"}>
                    <h4 className={"mt-3"}>Reset your password</h4>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-1">
                            <label className="form-label">
                                Password:
                            </label>
                            <input className="form-control" type="password" name="password" value={password} onChange={handleChange}/>
                        </div>
                        <div className="justify-content-center">
                            <button className="mt-3 btn btn-primary" type="submit"><small>Reset Password</small></button>
                            <Link className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small>Success! Go to Login Page</small>
                            </Link>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    );
}

export default ResetPassword;