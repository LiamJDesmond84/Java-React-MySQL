import React, { useState } from 'react'
import axios from "axios"
import { useNavigate } from 'react-router';


const Login = () => {
    const navigate = useNavigate();
    const [errors, setErrors] = useState("");

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")


    const loginUser = (e) => {
        e.preventDefault();

        axios.post("http://localhost:8080/api/loginUser", {email, password})
            .then((res) => {
                console.log(res);

                setEmail("");
                setPassword("");

                navigate("/dashboard");

                })
            .catch((err) => {

            console.log(err.response.data);
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
                <p>{errors}</p>
                :null
            }
        </fieldset>
        <label>Password</label>
        <fieldset className='float-label-field'>
            <input id="txtName" type="text" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            {
                errors?
                <p>{errors}</p>
                :null
            }
        </fieldset>
            <input className="button" type="submit" placeholder="Submit" />
        <button className="button" onClick={() => navigate("/")}>Cancel</button>
        </form>
        <small className="text-muted">Don't Have An Account? <a className="ml-2" href="/register">Register!</a></small>
    </div>
    )
}

export default Login