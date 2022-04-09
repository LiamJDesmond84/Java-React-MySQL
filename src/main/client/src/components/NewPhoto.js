import React, { useState, useEffect} from 'react'
import axios from "axios"
import { useNavigate } from 'react-router';


const NewPhoto = () => {
    const navigate = useNavigate();
    const [errors, setErrors] = useState("");

    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")
    const [imgURL, setimgURL] = useState("")

    const [photoObject, setPhotoObject] = useState({title: "", description: "", imgURL: ""})


    useEffect(() => {
        axios.get(`http://localhost:8080/api/userVerif`)
            .then(res => {
            console.log(res.data);
            })
            .catch(err => {
            console.log(err.response.data);
            navigate("/");
            })
    }, []);

    const inputHandler = (e) => {
        let newPhotoObject = {...photoObject};
        newPhotoObject[e.target.name] = e.target.value;
        console.log(newPhotoObject);
        setPhotoObject(newPhotoObject);
    }



    const createPost = (e) => {
        console.log(title);
        console.log(description);
        console.log(imgURL);
        console.log(imgURL);
        console.log(title);
        console.log(description);
        e.preventDefault();
        axios.post("http://localhost:8080/api/createPhoto", photoObject)
            .then((res) => {
                console.log(res);
                setTitle("");
                setDescription("");
                setimgURL("");
                navigate("/dashboard");

                })
            .catch((err) => {
                console.log(err);
            console.log(err.response.data.errors[0]);
            setErrors(err.response.data.errors[0])
            console.log(errors);
        });

            
        };

    return (

            <div className="modern-form">
    
                <form onSubmit={createPost}>
                <h4>Add a Post</h4>
                <label>Title</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="title" value={photoObject.title} onChange={inputHandler}  />
                    {
                        errors.field === "title" ?
                        <p>{errors.defaultMessage}</p>
                        :null
                    }
                </fieldset>
                <label>Description</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="description" value={photoObject.description}  onChange={inputHandler} />
                    {
                        errors.field === "description" ?
                        <p>{errors.defaultMessage}</p>
                        :null
                    }
                </fieldset>
                <label>Photo</label>
                <fieldset className='float-label-field'>
                    <input id="txtName" type="text" name="imgURL" value={photoObject.imgURL}  onChange={inputHandler} />
                    {
                        errors.field === "imgURL" ?
                        <p>{errors.defaultMessage}</p>
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