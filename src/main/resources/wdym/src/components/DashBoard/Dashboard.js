import PeriodCalendar from "./PeriodCalendar";
import IntervalChart from "./IntervalChart";
import {useEffect} from "react";
import {fetchUserByToken} from "../../store/userSlice";
import {fetchIntervals, fetchPeriods} from "../../store/periodSlice";
import {useDispatch, useSelector} from "react-redux";


function Dashboard() {
    const dispatch = useDispatch();
    const currentUser = useSelector(state => state.user.currentUser);
    const periods = useSelector(state => state.period.periods);

    useEffect(() => {
        dispatch(fetchUserByToken()).then((result) => {
            const now = new Date();
            const oneYearBefore = new Date(now.getFullYear()-1,now.getMonth(),now.getDate());
            dispatch(fetchPeriods({
                userId: result.payload.id
            }));
            dispatch(fetchIntervals(result.payload.id));
        })
    }, []);
    return <div>
        <PeriodCalendar></PeriodCalendar>
        <IntervalChart></IntervalChart>
    </div>;

}



export default Dashboard;