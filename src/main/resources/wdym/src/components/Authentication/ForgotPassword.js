import React, { useState } from 'react';
import helper from "../../helper";
import {Link} from "react-router-dom";

function ForgotPassword() {
    const [email, setEmail] = useState("");

    const handleChange = (e) => {
        setEmail(e.target.value );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        return helper.api("GET","/auth/forgot-password",email)
            .then((response) => {
                if(response) {
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
                                Email:
                            </label>
                            <input className="form-control" type="email" name="email" value={email} onChange={handleChange}/>
                        </div>
                        <div className="justify-content-center">
                            <button className="mt-3 btn btn-primary" type="submit"><small>Send Password Reset Link</small></button>
                            <Link className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small>Back</small>
                            </Link>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    );
}

export default ForgotPassword;