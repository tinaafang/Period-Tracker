import React, {useState} from 'react';
import helper from "../../helper";
import {Link} from "react-router-dom";
import {loginSuccess} from "../../store/userSlice";
import {useDispatch} from "react-redux";
import { useNavigate } from 'react-router-dom';
import {fetchPeriods, fetchStats} from "../../store/periodSlice";

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


    const handleSubmit = (e) => {
        e.preventDefault();
        return helper.api("POST", "/auth/login", formData)
            .then((response) => {
                if (!response.error) {
                    localStorage.setItem("jwt",response.token);
                    dispatch(loginSuccess(response.user));
                    dispatch(fetchPeriods({userId:response.user.id}));
                    dispatch(fetchStats(response.user.id));
                    navigate('/dashboard');
                }
            })
    };

    return (
        <div className="container-fluid page">
            <div >
                <h2 className={"mt-5 mb-5"}></h2>
                <div className={"auth-form"}>
                <h4 className={"mt-3"}>Log in</h4>
                <form onSubmit={handleSubmit}>
                    <div className="mb-1">
                        <label className="form-label mt-4">
                            Email:
                        </label>
                        <input className="form-control" type="text" name="email" value={formData.email} onChange={handleChange}/>
                    </div>
                    <div className="mb-1">
                        <label className="form-label mt-3">
                            Password:
                        </label>
                        <Link className="form-label float-end no-underline mt-3" to={"/forgot-password"}>
                            <small className={"link"}> Forgot Password? </small>
                        </Link>
                        <input className="form-control" type="password" name="password" value={formData.password} onChange={handleChange}/>
                    </div>
                    <div className="justify-content-center">
                    <button className="mt-3 btn btn-primary pink" type="submit">Login</button>
                    <Link className="mt-4 form-label float-end no-underline" to={"/register"}>
                        <small className={"link"}> Sign up</small>
                    </Link>
                    </div>

                </form>
                </div>
            </div>
        </div>
    );
}

export default Login;
