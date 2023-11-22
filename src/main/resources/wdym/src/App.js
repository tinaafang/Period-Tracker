import logo from './logo.svg';
import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";
import Register from "./components/Register";
import Login from "./components/LogIn";

function App() {
    return (
        <Router>
            <div className="App">
                <nav>
                    <ul>
                        <li>
                            <Link to="/register">Register</Link>
                        </li>
                        <li>
                            <Link to="/login">Log In</Link>
                        </li>
                    </ul>
                </nav>

                <Routes>
                    <Route path="/register" element={<Register />} />
                    <Route path="/login" element={<Login />} />

                </Routes>
            </div>
        </Router>
    );
}

export default App;
