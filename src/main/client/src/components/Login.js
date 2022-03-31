import React, { useState } from 'react'
import axios from "axios"
import { useNavigate } from 'react-router';
// import { Link } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});

    // const [firstName, setFirstName] = useState("")
    // const [lastName, setLastName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")


    const loginUser = (e) => {
        e.preventDefault();

        axios.post("http://localhost:8000/api/loginUser", {email, password})
            .then((res) => {
                console.log(res);
                // console.log("test");
                // setFirstName("");
                // setLastName("");
                setEmail("");
                setPassword("");

                navigate("/dashboard");
                // setHasBeenSubmitted(!hasBeenSubmitted);
                })
            .catch((err) => {

            console.log(err);
            setErrors(err.response.data)});
            console.log(errors);

            
        };

    
    return (
        <div className="modern-form">
    
        <form onSubmit={loginUser}>
        <h4>User Login</h4>


        <label>Email</label>
        <fieldset className='float-label-field'>
            <input id="txtName" type="text" name="email" value={email} onChange={(e) => setEmail(e.target.value)}  />
            {
                errors ?
                <p>{errors.error}</p>
                :null
            }
        </fieldset>
        <label>Password</label>
        <fieldset className='float-label-field'>
            <input id="txtName" type="text" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            {
                errors?
                <p>{errors.passError}</p>
                :null
            }
        </fieldset>
            <input className="button" type="submit" placeholder="Submit" />
        <button className="button" onClick={() => navigate("/")}>Cancel</button>
        </form>
        <small className="text-muted">Don't Have An Account? <a class="ml-2" href="/register">Register!</a></small>
    </div>
    )
}

export default Login