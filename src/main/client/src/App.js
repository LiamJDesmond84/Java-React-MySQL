import { Routes, Route, Link } from "react-router-dom";
import './App.css';
import Dashboard from "./components/Dashboard";
import Login from "./components/Login";
import NewPhoto from "./components/NewPhoto";
import { PhotoDetails } from "./components/PhotoDetails";
import Register from "./components/Register";
import UpdatePhoto from "./components/UpdatePhoto";
import { useNavigate } from 'react-router';
import axios from "axios";

function App() {

	const navigate = useNavigate();

	const logout = () => {
		axios.get(`http://localhost:8080/api/logout`)
		.then(res => {
			navigate("/");
		})
		.catch(err => {console.log(err);})
};
	

	
	return (
	<div>
			<div className="topnav">
			<Link to="/dashboard">Home</Link>
			<Link to="/">Login</Link>
			<Link to="/register">Register</Link>
			<Link to="/newphoto">Add a Photo</Link>
				<div  className="topnav-right">
					<button onClick={logout}>Logout</button>
				</div>
			</div>

			<div className="App">

			<Routes>
						<Route path="/dashboard" element={<Dashboard />} />
						<Route path="/" element={<Login />} />
						<Route path="/register" element={<Register />} />
						<Route path="/newphoto" element={<NewPhoto />} />
						<Route path="/photo/:id" element={<PhotoDetails />} />
						<Route path="/photo/edit/:id" element={<UpdatePhoto />} />
			</Routes>
		</div>
		</div>
	);
}

export default App;
