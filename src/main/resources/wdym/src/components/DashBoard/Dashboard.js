import PeriodCalendar from "./PeriodCalendar";
import CycleChart from "./CycleChart";
import {useEffect} from "react";
import {fetchUserByToken} from "../../store/userSlice";
import {fetchPeriods, fetchStats} from "../../store/periodSlice";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import Stats from "./Stats";
import {openAlert} from "../../store/uiSlice";


function Dashboard() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const currentUser = useSelector(state => state.user.currentUser);
    let fetching = false;

    useEffect(() => {
        if(!currentUser && !fetching) {
            fetching = true;
            dispatch(fetchUserByToken()).then((result) => {
                if(result.payload?.status === 500) {
                    dispatch(openAlert({message:"session expired, please log in",color:'red'}));
                    navigate('/');
                } else {
                    dispatch(fetchPeriods({
                        userId: result.payload.id
                    })).then(() => {
                        dispatch(fetchStats(result.payload.id));
                    })
                }
            })
        }

    }, []);
    return <div className={'dashboard row clearfix m-3'}>
        <div className={'dashboard-left col-lg-6'}>
            <Stats></Stats>
        <PeriodCalendar></PeriodCalendar>
        </div>
        <div className={'dashboard-right col-lg-6'}>
        <CycleChart></CycleChart>
        </div>
    </div>;

}



export default Dashboard;