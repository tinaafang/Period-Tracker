import React, { useState } from 'react';
import helper from "../helper";
const api = helper.api;

function Register() {
    const [formData, setFormData] = useState({
        userName: '',
        email: '',
        password: '',
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Add your registration logic here
        return helper.api("POST","/auth/post-test",formData)
            .then((response) => {
                if(response) {
                    debugger;
                }
            })
    };

    return (
        <div>
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input type="text" name="userName" value={formData.userName} onChange={handleChange} />
                </label>
                <br />
                <label>
                    Email:
                    <input type="email" name="email" value={formData.email} onChange={handleChange} />
                </label>
                <br />
                <label>
                    Password:
                    <input type="password" name="password" value={formData.password} onChange={handleChange} />
                </label>
                <br />
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;