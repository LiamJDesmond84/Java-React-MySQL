import React, { useState } from 'react'
import axios from "axios"
import { useNavigate } from 'react-router';

const NewPhoto = () => {
    const navigate = useNavigate();
    const [errors, setErrors] = useState({});

    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")
    const [imgURL, setImgURL] = useState("")



    const createPost = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/createPhoto", {title, description, imgURL})
            .then((res) => {
                console.log(res);
                setTitle("");
                setDescription("");
                setImgURL("");
                navigate("/");
                // setHasBeenSubmitted(!hasBeenSubmitted);
                })
            .catch((err) => {
            // console.log(err.response.data.errors[0]);
            setErrors(err.response.data.errors[0])});
            console.log(errors);

            
        };

    return (

            <div className="modern-form">
    
                <form onSubmit={createPost} method='POST' encType='multipart/form-data' action="..">
                <h4>Add a Post</h4>
                <label>Title</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="title" value={title} onChange={(e) => setTitle(e.target.value)}  />
                    {
                        errors.path === "title"?
                        <p>{errors.message}</p>
                        :null
                    }
                </fieldset>
                <label>Description</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="description" value={description} onChange={(e) => setDescription(e.target.value)} />
                    {
                        errors.path === "description"?
                        <p>{errors.message}</p>
                        :null
                    }
                </fieldset>
                <label>Photo</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="imgURL" value={imgURL} onChange={(e) => setImgURL(e.target.value)} />
                    {
                        errors.path === "imgURL"?
                        <p>{errors.message}</p>
                        :null
                    }
                </fieldset>
                    <input className="button" type="submit" placeholder="Submit" />
                <button className="button" onClick={() => navigate("/")}>Cancel</button>
                </form>
            </div>
        
        

    )
}

export default NewPhoto