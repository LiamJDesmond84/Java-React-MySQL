import React, { useEffect, useState } from 'react'
import { Link, useParams } from "react-router-dom";
import axios from 'axios';

export const PhotoDetails = () => {

    const { id } = useParams();
    // const navigate = useNavigate();

    const [photo, setPhoto] = useState({})


    useEffect(() => {
        axios.get(`http://localhost:8080/api/getPhoto/${id}`)
            .then(res => {
                console.log(res.data);
                setPhoto(res.data);})
            .catch(err => {console.log(err);})
    }, []);

    return (
    <div className="postPage">
            <div className="leftSide">
                <div className="post" id="individual">
                    <img src={`${photo.imgURL}`} alt="" />
                    <div className="title"> {photo.title} </div>
                    <div className="body">{photo.description}</div>
                </div>
            </div>
            <Link to={`/photo/edit/${photo.title}`}>Add a Photo</Link>
    </div>
    )
}
