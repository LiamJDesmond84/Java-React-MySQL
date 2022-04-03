import '../App.css';
import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios"

const UpdatePhoto = () => {

    const navigate = useNavigate();
    const { id } = useParams();
    // const navigate = useNavigate();
    // const [hasBeenSubmitted, setHasBeenSubmitted] = useState(false);

    // const [title, setTitle] = useState("")
    // const [description, setDescription] = useState("")
    // const [imgURL, setImgURL] = useState("")

    const [errors, setErrors] = useState({});

    const [photoObject, setPhotoObject] = useState({title: "", description: "", imgURL: ""})

    const inputHandler = (e) => {
        let newPhotoObject = {...photoObject};
        newPhotoObject[e.target.name] = e.target.value;
        console.log(newPhotoObject);
        setPhotoObject(newPhotoObject);
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/api/getPhotoDetails/${id}`)
            .then(res => {console.log(res);
                setPhotoObject(res.data);
                // setTitle(res.data.title);
                // setDescription(res.data.description);
                // setImgURL(res.data.imgURL);
                })
            .catch(err => {console.log(err);navigate('/error');})
    }, [id])

    const updatePhoto = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:8080/api/updatePhoto/${id}`, photoObject)
            .then((res) => {
                console.log(res);
                // setTitle("");
                // setDescription("");
                // setImgURL("");
                navigate("/dashboard");
                // setHasBeenSubmitted(!hasBeenSubmitted);
                })
            .catch((err) => {

            setErrors(err.response.data.errors[0])});
            console.log(errors);

            
        };


    return (
        <div className="modern-form">

                <form onSubmit={updatePhoto}>
                <h4>Add a Post</h4>
                <label>Title</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="title" value={photoObject.title} onChange={inputHandler}  />
                    {
                        errors.path === "title"?
                        <p>{errors.message}</p>
                        :null
                    }
                </fieldset>
                <label>Description</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="description" value={photoObject.description} onChange={inputHandler} />
                    {
                        errors.path === "description"?
                        <p>{errors.message}</p>
                        :null
                    }
                </fieldset>
                <label>UserName</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="username" value={photoObject.imgURL} onChange={inputHandler} />
                    {
                        errors.path === "username"?
                        <p>{errors.message}</p>
                        :null
                    }
                </fieldset>
                    <input className="button" type="submit" placeholder="Update" />
                <button className="button" onClick={() => navigate("/")}>Cancel</button>
                </form>
            </div>
    )
}

export default UpdatePhoto