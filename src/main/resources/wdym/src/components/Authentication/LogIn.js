// Login.js
import React, {useEffect, useState} from 'react';
import helper from "../../helper";
import {Link} from "react-router-dom";
import {loginSuccess} from "../../store/userSlice";
import {useDispatch} from "react-redux";
import { useNavigate } from 'react-router-dom';

function Login() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    // useEffect(() => {
    //     // Simulating a login success event
    //     const user = { id: 1, username: 'exampleUser' };
    //
    //     // Dispatch the loginSuccess action with the user information
    //     dispatch(loginSuccess(user));
    // }, [dispatch]);


    const handleSubmit = (e) => {
        e.preventDefault();

        // Add your registration logic here
        return helper.api("POST", "/auth/login", formData)
            .then((response) => {
                if (response) {
                    localStorage.setItem("jwt",response.token);
                    dispatch(loginSuccess(response.user));
                    navigate('/dashboard');
                }
            })
    };

    const forgotPassword = (e) => {
        e.preventDefault();
        // Add your registration logic here
        return helper.api("GET", "/auth/reset-password")
            .then((response) => {
                if (response) {
                    debugger;
                }
            })
    };

    return (
        <div className="container-fluid page">
            <div className="login">
                <h2 className={"mt-5 mb-5"}>Welcome to the period app</h2>
                <div className={"auth-form"}>
                <h4 className={"mt-3"}>Log in</h4>
                <form onSubmit={handleSubmit}>
                    <div className="mb-1">
                        <label className="form-label">
                            Email:
                        </label>
                        <input className="form-control" type="email" name="email" value={formData.email} onChange={handleChange}/>
                    </div>
                    <div className="mb-1">
                        <label className="form-label">
                            Password:
                        </label>
                        <Link className="form-label float-end no-underline" to={"/forgot-password"}>
                            <small> Forgot Password? </small>
                        </Link>
                        <input className="form-control" type="password" name="password" value={formData.password} onChange={handleChange}/>
                    </div>
                    <div className="justify-content-center">
                    <button className="mt-3 btn btn-primary" type="submit">Login</button>
                    <Link className="mt-4 form-label float-end no-underline" to={"/register"}>
                        <small> Sign up</small>
                    </Link>
                    </div>

                </form>
                </div>
            </div>
        </div>
    );
}

export default Login;
