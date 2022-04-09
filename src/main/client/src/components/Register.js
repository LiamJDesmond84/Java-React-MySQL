import React, { useState } from 'react'
import axios from "axios"
import { useNavigate } from 'react-router';


const Register = () => {

    const navigate = useNavigate();
    const [errors, setErrors] = useState("");
    const [passError, setPasserror] = useState("");

    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [confirm, setConfirm] = useState("")

    const registerUser = (e) => {
        e.preventDefault();
        if (password !== confirm) {
            setPasserror("Password & Confirm Password must match");
        }
        else {
        axios.post("http://localhost:8080/api/registerUser", {firstName, lastName, email, password})
            .then((res) => {
                console.log(res);
                setFirstName("");
                setLastName("");
                setEmail("");
                setPassword("");
                setConfirm("");
                navigate("/dashboard");

                })
            .catch((err) => {
            console.log("Register Error");
            
            // console.log(err.response.data[0]);
            console.log(err.response.data.errors[0]);
            // console.log(err);
            console.log("asfasfsadf")
            setErrors(err.response.data.errors[0])});
            
            console.log(errors);

            
        };
    }


    return (
    <div className="modern-form">
    
    <form onSubmit={registerUser}>
    <h4>Register New User</h4>
    <label>First Name</label>
    <fieldset className='float-label-field'>
        <input id="txtName" type="text" name="firstName" value={firstName} onChange={(e) => setFirstName(e.target.value)}  />
        {
            errors.field === "firstName" ?
            <p>{errors.defaultMessage}</p>
            :null
        }
    </fieldset>
    <label>Last Name</label>
    <fieldset className='float-label-field'>
        <input id="txtName" type="text" name="lastName" value={lastName} onChange={(e) => setLastName(e.target.value)}  />
        {
            errors.field === "lastName" ?
            <p>{errors.defaultMessage}</p>
            :null
        }
    </fieldset>
    <label>Email</label>
        <fieldset className='float-label-field'>
            <input id="txtName" type="text" name="email" value={email} onChange={(e) => setEmail(e.target.value)}  />
            {
                errors.field === "email" ?
                <p>{errors.defaultMessage}</p>
                :null
            }
        </fieldset>
    <label>Password</label>
    <fieldset className='float-label-field'>
        <input id="txtName" type="text" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />

    </fieldset>
    <label>Confirm Password</label>
    <fieldset className='float-label-field'>
        <input id="txtName" type="text" name="confirm" value={confirm} onChange={(e) => setConfirm(e.target.value)} />
        {
            passError !== ""?
            <p>{passError}</p>
            :null
        }
    </fieldset>
        <input className="button" type="submit" placeholder="Submit" />
    <button className="button" onClick={() => navigate("/")}>Cancel</button>
    </form>
    <small className="text-muted">Already a User? <a className="ml-2" href="/">Sign In!</a></small>
</div>
)
};

export default Register;