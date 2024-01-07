import PeriodCalendar from "./PeriodCalendar";
import CycleLineChart from "./CycleLineChart";
import {useEffect, useRef, useState} from "react";
import {fetchUserByToken} from "../../store/userSlice";
import {fetchPeriods, fetchStats} from "../../store/periodSlice";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import Stats from "./Stats";
import {openAlert} from "../../store/uiSlice";
import PeriodBarChart from "./PeriodBarChart";


function Dashboard() {
    const [height, setHeight] = useState(undefined);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const calendarRef = useRef(null)
    const currentUser = useSelector(state => state.user.currentUser);
    let fetching = false;
    useEffect(() => {
        setHeight(calendarRef.current?.clientHeight);
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


    useEffect(() => {
        if (!calendarRef.current) return;
        const resizeObserver = new ResizeObserver(() => {
            setHeight(calendarRef.current.clientHeight);
        });
        resizeObserver.observe(calendarRef.current);
        return () => resizeObserver.disconnect();
    }, []);
    return <div className={'clearfix m-3 mt-0container-fluid'}>
        <div className="row ms-1 me-2 " >
            <div className=" col-8 mt-3 mb-3 " >
                <div className="calendar-container" ref={calendarRef}>
                    <PeriodCalendar></PeriodCalendar>
                </div>
            </div>
            <div className="col-4 mt-3 mb-3 overflow-scroll" style={{maxHeight: height}}>
                {/*<div className="card" onClick={() => {navigate('/my-account')}}>*/}
                {/*        my account*/}
                {/*    </div>*/}
                <Stats></Stats>
            </div>
        </div>
        <div className="row ms-1 me-1">
                <div className={"chart-container col-6 mt-2"}>
                    <CycleLineChart></CycleLineChart>
                </div>
                <div className={"chart-container col-6 mt-2"}>
                    <PeriodBarChart></PeriodBarChart>
                </div>
        </div>
    </div>;

}



export default Dashboard;