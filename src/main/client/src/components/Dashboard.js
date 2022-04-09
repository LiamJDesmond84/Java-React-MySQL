import React, { useState, useEffect } from 'react'
import axios from "axios"
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router';


const Dashboard = () => {
    const navigate = useNavigate();

    const [hasBeenSubmitted, setHasBeenSubmitted] = useState(false);
    const [photos, setPhotos] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:8080/api/userVerif`)
            .then(res => {
            console.log(res.data);})
            .catch(err => {
            console.log(err.response.data);
            navigate("/");
            })
    }, []);

    useEffect(() => {
        axios.get("http://localhost:8080/api/allPhotos")
            .then((response) => {
            console.log(response.data);
            setPhotos(response.data);
            })
                .catch((err) => console.log(err));
        }, [hasBeenSubmitted]);

    const deletePost = (id) => {
        axios.delete(`http://localhost:8080/api/deletePhoto/${id}`)
            .then(res => {console.log(res)})
            .catch(err => console.log(err))
            setHasBeenSubmitted(!hasBeenSubmitted)
    }

    return (
        <div style={{display: "flex", flexWrap: "wrap"}}>
        {photos.map((x,i) => {

            return (
            <div key={i}>
                    
                    <div className="post" style={{overflow: 'hidden'}}>
                    <div className="title"> {x.title} </div>
                    <div className="body">{x.description}</div>

                    <img src={`${x.imgURL}`} alt="" style={{width: "250px", height: "200px", marginLeft: "50px"}} />
                    <Link to={`/photo/${x.id}`}><button className="link1" style={{marginLeft: "70px", height: "15px"}} type="button">Details</button></Link>
                    <Link to={`/photo/edit/${x.id}`}><button className="link2" style={{marginLeft: "70px", height: "15px"}} type="button">Edit</button></Link>
                    <button  className="delete" style={{marginLeft: "70px"}} onClick={(e)=>{deletePost(x.id)}}>Delete</button>
                    </div>
            </div>
            );
        })}
        </div>
    );
    }


export default Dashboard