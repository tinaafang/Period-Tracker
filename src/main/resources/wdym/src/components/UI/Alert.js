// Import necessary modules and libraries
import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { closeAlert} from "../../store/uiSlice";

// Define the React component
const Alert = () => {
    // Use useSelector to access the state from the Redux store
    const activeAlert = useSelector(state => state.ui.activeAlert);

    // Use useDispatch to get the dispatch function
    const dispatch = useDispatch();

    // Define colorToClassName function
    const colorToClassName = () => {
        if (activeAlert.color === 'red') {
            return 'alert-danger';
        }
        if (activeAlert.color === 'yellow') {
            return 'alert-warning';
        }
        if (activeAlert.color === 'green') {
            return 'alert-success';
        }
    };

    return (
        <div className={`alert alert-dismissible show center-block ${colorToClassName()}`} role="alert">
            {activeAlert.message}
            <button type="button" className="btn-close" onClick={() => dispatch(closeAlert())}></button>
        </div>
    );
};

export default Alert;
