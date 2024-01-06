import React, { useState } from 'react';
import helper from "../../helper";
import {Link, useNavigate} from "react-router-dom";
import {openAlert} from "../../store/uiSlice";
import {useDispatch} from "react-redux";

function ForgotPassword() {
    const [email, setEmail] = useState("");
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleChange = (e) => {
        setEmail(e.target.value );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        return helper.api("GET","/auth/forgot-password?email="+email)
            .then((response) => {
                if(response.ok) {
                    dispatch(openAlert({message:"A verification email has been sent to "+email,color:'red'}));
                    navigate("/reset-password", { state: email });
                }
            })
    };

    return (
        <div className="container-fluid page">
            <div>
                {/*<h2 className={"mt-5 mb-5"}>Welcome to the period app</h2>*/}
                <div className={"auth-form"}>
                    <h4 className={"mt-3"}>Forget password</h4>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-1 mt-3">
                            <input className="form-control mt-4" type="email" name="email" value={email} onChange={handleChange} placeholder={"Email: "}/>
                        </div>
                        <div className="justify-content-center">
                            <button className="mt-3 btn btn-primary pink" type="submit"><small>Send Password Reset Link</small></button>
                            <Link className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small className={"link"}>Back</small>
                            </Link>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    );
}

export default ForgotPassword;