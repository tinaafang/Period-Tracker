
import {Calendar,} from "react-multi-date-picker";
import {useDispatch, useSelector} from "react-redux";
import {createPeriods, fetchStats, setPeriods} from "../../store/periodSlice";

function PeriodCalendar() {
    const dispatch = useDispatch();
    const currentUser = useSelector(state => state.user.currentUser);
    const periods = useSelector(state => state.period.periods);


    const save = () => {
        if(currentUser) {
            dispatch(createPeriods(currentUser.id)).then(() => {
                dispatch(fetchStats(currentUser.id));
            });
        }
    }


        return <div className={'period-calendar'}>
            <Calendar
                range
                multiple
                numberOfMonths={2}
                value={periods}
                rangeHover={true}
                fullYear={false}
                onChange={(e) => {dispatch(setPeriods(e))}}
            >
                <button
                    className={'btn btn-primary m-1 pink'}
                    onClick={() => save()}
                >
                    save
                </button>
            </Calendar>
        </div>;

}



export default PeriodCalendar;