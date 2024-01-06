import React, { useState } from 'react';
import helper from "../../helper";
import {Link} from "react-router-dom";
import {openAlert} from "../../store/uiSlice";
import {useDispatch} from "react-redux";

function Register() {
    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });
    const [emailSent, setEmailSent] = useState(false);
    const dispatch = useDispatch();
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setEmailSent(true);
        return helper.api("POST","/auth/register",formData)
            .then((response) => {
                if(response.ok) {
                    dispatch(openAlert({message:"A verification email has been sent",color:'red'}))
                }
            })
    };

    const resend = () => {
        return helper.api("GET","/auth/register/resend/"+formData.email)
            .then((response) => {
                if(response.ok) {
                    dispatch(openAlert({message:"A verification email has been resent",color:'red'}))
                }
            })
    }

    return (
        <div className="container-fluid page">
            <div className="login">
                {/*<h2 className={"mt-5 mb-5"}>Welcome to the period app</h2>*/}
                <div className={"auth-form"}>
                    <h4 className={"mt-3"}>Register</h4>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-1">
                            <label className="form-label mt-4">
                                Email:
                            </label>
                            {emailSent && <div className={"mt-4 form-label float-end no-underline"}>
                                <small className={"link"} onClick={resend}>Resend Verification Email</small>
                            </div>}


                            <input className="form-control" type="email" name="email" value={formData.email} onChange={handleChange}/>
                        </div>
                        <div className="mb-1">
                            <label className="form-label mt-3">
                                Password:
                            </label>
                            <input className="form-control" type="password" name="password" value={formData.password} onChange={handleChange}/>
                        </div>
                        <div className="justify-content-center">
                            <button className="mt-3 btn btn-primary pink" type="submit">Sign up</button>
                            <Link className="mt-4 form-label float-end no-underline" to={"/"}>
                                <small className={"link"}> Log in</small>
                            </Link>
                        </div>

                    </form>
                </div>
            </div>
        </div>

    );
}

export default Register;