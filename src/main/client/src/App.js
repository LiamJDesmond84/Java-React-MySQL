import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import './App.css';
import Dashboard from "./components/Dashboard";
import Login from "./components/Login";
import NewPhoto from "./components/NewPhoto";
import Register from "./components/Register";

function App() {
  return (
<Router>
		<div className="navbar">
		<Link to="/dashboard">Home</Link>
		<Link to="/">Login</Link>
		<Link to="/register">Register</Link>
		<Link to="/newphoto">Add a Photo</Link>
		</div>
    <div className="App">

		<Routes>
                    <Route path="/dashboard" element={<Dashboard />} />
                    <Route path="/" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    {/* <Route path="/posts/edit/:id" element={<UpdatePost />} /> */}

                    <Route path="/newphoto" element={<NewPhoto />} />
					{/* <Route path="/posts/:id" element={<PostDetails />} /> */}
        </Routes>
    </div>
	</Router>
  );
}

export default App;
