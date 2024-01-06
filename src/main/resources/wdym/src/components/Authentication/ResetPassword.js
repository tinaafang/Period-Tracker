import React, { useState } from 'react';
import helper from "../../helper";
import {Link,useLocation} from "react-router-dom";

function ResetPassword() {
    const { state } = useLocation();
    const [password, setPassword] = useState("");
    const [success, setSuccess] = useState(null);

    const handleChange = (e) => {
        setPassword(e.target.value );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        return helper.api("POST","/auth/reset-password",{email: state,password:password})
            .then((response) => {
                if(response.ok) {
                    setSuccess(true);
                } else {
                    setSuccess(false);
                }
            })
    };

    return (
        <div className="container-fluid page">
            <div>
                {/*<h2 className={"mt-5 mb-5"}>Welcome to the period app</h2>*/}
                <div className={"auth-form"}>
                    <h4 className={"mt-3"}>Reset password</h4>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-1">
                            <label className="form-label">
                                Password:
                            </label>
                            <input className="form-control" type="password" name="password" value={password} onChange={handleChange}/>
                        </div>
                        <div className="justify-content-center">
                            <button className="mt-3 btn btn-primary pink" type="submit"><small>Reset Password</small></button>
                            {success && <Link className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small className={"link"}>Success! Go to Login Page</small>
                            </Link>
                            }
                            {success === false && <div className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small className={"link"}>failed!</small>
                            </div>
                            }
                        </div>

                    </form>
                </div>
            </div>
        </div>

    );
}

export default ResetPassword;