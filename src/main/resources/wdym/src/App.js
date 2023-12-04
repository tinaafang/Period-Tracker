import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";
import Register from "./components/Authentication/Register";
import Login from "./components/Authentication/LogIn";
import ForgotPassword from "./components/Authentication/ForgotPassword";
import ResetPassword from "./components/Authentication/ResetPassword";
import Dashboard from "./components/DashBoard/Dashboard";
import Alert from "./components/UI/Alert";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import {fetchUserByToken} from "./store/userSlice";

function AlertComponent() {
    const isAlertActive = useSelector(state => state.ui?.activeAlert);
    if(isAlertActive) {
        return <Alert></Alert>
    } else {
        return null;
    }
}

function App() {
    const dispatch = useDispatch();
    const currentUser = useSelector(state => state.user?.currentUser);
    useEffect(() => {
        console.log("i was here");
        if(!currentUser) {
            console.log("in");
            if(localStorage.getItem("jwt")) {
                console.log("inin");
                dispatch(fetchUserByToken());
            } else {
                // todo: have to login, alert?
            }
        }
    }, []);

    return (
        <Router>
            <div className="App">
                <AlertComponent></AlertComponent>

                <Routes>
                    <Route path="/register" element={<Register />} />
                    <Route path="/" element={<Login />} />
                    <Route path="/forgot-password" element={<ForgotPassword />} />
                    <Route path="/reset-password" element={<ResetPassword />} />
                    <Route path="/dashboard" element={<Dashboard />} />


                </Routes>
            </div>
        </Router>
    );
}

export default App;
