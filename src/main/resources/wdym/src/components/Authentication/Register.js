import React, { useState } from 'react';
import helper from "../../helper";
import {Link} from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux'
import {registerState} from "../../store/authSlice";
import {updateState} from "../../store/authSlice";

function Register() {
    const state = useSelector(registerState);
    const dispatch = useDispatch()


    const handleSubmit = (e) => {
        e.preventDefault();
        // Add your registration logic here
        return helper.api("POST","/auth/register",state)
            .then((response) => {
                if(response) {
                    debugger;
                }
            })
    };

    return (
        <div className="container-fluid page">
            <div className="login">
                <h2 className={"mt-5 mb-5"}>Welcome to the period app</h2>
                <div className={"auth-form"}>
                    <h4 className={"mt-3"}>Register</h4>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-1">
                            <label className="form-label">
                                Username:
                            </label>
                            <input
                                className="form-control"
                                type="text" name="userName"
                                value={state.userName}
                                onChange={(e) =>
                                    dispatch(updateState({
                                        path:["register",e.target.name],
                                        data:e.target.value}
                                ))}
                            />
                        </div>
                        <div className="mb-1">
                            <label className="form-label">
                                Email:
                            </label>
                            <input
                                className="form-control"
                                type="email"
                                name="email"
                                value={state.email}
                                onChange={(e) =>
                                    dispatch(updateState({
                                        path:["register",e.target.name],
                                        data:e.target.value}
                                    ))}
                            />
                        </div>
                        <div className="mb-1">
                            <label className="form-label">
                                Password:
                            </label>
                            <input
                                className="form-control"
                                type="password"
                                name="password"
                                value={state.password}
                                onChange={(e) =>
                                    dispatch(updateState({
                                        path:["register",e.target.name],
                                        data:e.target.value}
                                    ))}
                            />
                        </div>
                        <div className="justify-content-center">
                            <button className="mt-3 btn btn-primary" type="submit">Sign up</button>
                            <Link className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small> Log in</small>
                            </Link>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    );
}

export default Register;