import React, { useState } from "react";
import _ from "lodash"
import {Calendar,DateObject} from "react-multi-date-picker";
import {useDispatch, useSelector} from "react-redux";
import {createPeriods, fetchPeriods} from "../../store/periodSlice";


function Dashboard() {
    const dispatch = useDispatch();
    const [values, setValues] = useState(undefined);
    const currentUser = useSelector(state => state.user.currentUser);

    const save = () => {
        const periodsCreate = {
            user: {id:currentUser.id},
            periods: _.map(values, (value) => {
                return {
                    startDate: value[0].format(),
                    endDate:value[1].format()
                }
            })
        };
        dispatch(createPeriods(periodsCreate));
    }

    // useEffect(() => {
    //     dispatch(fetchPeriods()).getItems().then(response => {
    //         setItems(response.data);
    //     });
    // }, []);
    return <div>
        {
            // _.map(values,(value) => {
            //     return <div>{value[3]} {value[0].format()}   {value[1].format()}</div>
            // })
        }


        <Calendar
            range
            multiple
            numberOfMonths={2}
            values={values}
            onChange={setValues}
        >
            <button
                className={'btn btn-primary m-1'}
                onClick={() => save()}
            >
                save
            </button>
        </Calendar>
    </div>;
}



export default Dashboard;