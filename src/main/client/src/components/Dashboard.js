import React, { useState, useEffect } from 'react'
import axios from "axios"
import { Link } from "react-router-dom";


const Dashboard = () => {
    const [hasBeenSubmitted, setHasBeenSubmitted] = useState(false);
    const [photos, setPhotos] = useState([])



    useEffect(() => {
        axios.get("http://localhost:8080/api/allPhotos")
            .then((response) => {
            console.log(response.data);
            setPhotos(response.data);
            })
                .catch((err) => console.log(err));
        }, [hasBeenSubmitted]);

    const deletePost = (id) => {
        axios.delete(`http://localhost:8080/api/photo/${id}`)
            .then(res => {console.log(res)})
            .catch(err => console.log(err))
            setHasBeenSubmitted(!hasBeenSubmitted)
    }

    return (
        <div>
        {photos.map((x,i) => {
            const imgPath = x.imgURL
            return (
            <div key={i}>
                    
                    <div className="post">
                    <div className="title"> {x.title} </div>
                    <div className="body">{x.description}</div>
                    {/* <Image source={x.imgURL} /> */}
                    <img src={require(imgPath)} alt="" />
                    <Link to={`/photos/${x.id}`}>Details</Link>
                    <Link to={`/photos/edit/${x.id}`}>Edit</Link>
                    <button onClick={(e)=>{deletePost(x.id)}}>Delete</button>
                    </div>
            </div>
            );
        })}
        </div>
    );
    }

//     return (
//         <div className="post">
//             <Link to="/newPost">Add a Post</Link>
//             <div className="table">
//             <h1>All Posts</h1>
    
    
//             <table>
//                 <thead>
//                         <tr>
//                             <th>Title</th>
//                             <th>Description</th>
//                             <th>Created By</th>
//                             <th>ID</th>
//                             <th>Edit</th>
//                             <th>Delete</th>
//                         </tr>
//                 </thead>
//             {posts.map((x,i) => {
//                 return (
//                         <tbody key={i}>
//                             <tr>
//                                 <td>{x.title}</td>
//                                 <td>{x.description}</td>
//                                 <td>{x.username}</td>
//                                 <td>{x.id}</td>
//                                 <td><Link to={`/posts/${x.id}`}>Details</Link></td>
//                                 <td><Link to={`/posts/edit/${x.id}`}>Edit</Link></td>
//                                 <td><button onClick={(e)=>{deletePost(x.id)}}>Delete</button></td>
//                             </tr>
//                         </tbody>
//     )})}
//             </table>
//         </div>
//         </div>
//     )
// }

export default Dashboard