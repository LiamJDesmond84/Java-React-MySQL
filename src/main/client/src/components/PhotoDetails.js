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
    console.log(photo)
    return (
    <div>
            <div className="leftSide">
                <div className="post" id="individual">
                    <div className="title"> {photo.title} added by: {photo.owner} </div>

                    <img src={`${photo.imgURL}`} alt="" style={{width: "250px", marginLeft: "75px"}} />
                    <div className="body">{photo.description}</div>
                </div>
            </div>

    </div>
    )
}
