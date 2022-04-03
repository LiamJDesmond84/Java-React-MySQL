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
    }, [id]);
    console.log(photo)
    return (
    <div>
            <div className="leftSide">
                <div className="post" id="individual">
                    { photo.photo ? 
                        <div>
                            <div className="title"> {photo.photo.title} <p style={{ fontSize: "16px"}}>Added by: {photo.user.email}</p> </div>
        
                            <img src={`${photo.photo.imgURL}`} alt="" style={{width: "250px", marginLeft: "75px"}} />
                            <div className="body">{photo.photo.description}</div>
                        </div>
                    :
                    <span>Loading ...</span>
                }
                </div>
            </div>

    </div>
    )
}
