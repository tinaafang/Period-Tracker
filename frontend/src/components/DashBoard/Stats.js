import {useSelector} from "react-redux";
import DashboardStatsCardItem from "./DashboardStatsCardItem";
import {useState} from "react";


function nextPeriodMessage(daysUntilNextPeriod, latestPeriod) {
    const latestPeriodStart = latestPeriod[0].toDate();
    const latestPeriodEnd = latestPeriod[1].toDate();
    const now = new Date();
    if(now.toDateString() === latestPeriodEnd.toDateString()) {
        const oneDay = 1000*60*60*24;
        return {
            title: "Period:",
            body: `Day ${Math.floor((latestPeriodEnd - latestPeriodStart) / oneDay)}`
        }
    } else if (daysUntilNextPeriod <= -1) {
        return {
            title: "Period:",
            body: `${daysUntilNextPeriod * -1} ${daysUntilNextPeriod === -1 ? 'Day' : 'Days'} late`
        }
    } else if (daysUntilNextPeriod >= 1) {
        return {
            title: "Period in:",
            body: `${daysUntilNextPeriod} ${daysUntilNextPeriod === 1 ? 'Day' : 'Days'}`
        }
    } else if (daysUntilNextPeriod === 0) {
        return {
            title: "Period:",
            body: "Coming Today"
        }
    }
}




function Stats() {
    const [state, setState] = useState(null);
    const latestPeriod = useSelector(state => state.period.latestPeriod);
    const daysUntilNextPeriod = useSelector(state => state.period.stats.daysUntilNextPeriod);
    const avgPeriodLength = useSelector(state => state.period.stats.avgPeriodLength);
    const avgCycleLength =  useSelector(state => state.period.stats.avgCycleLength);
    const cycleLengthRanges =  useSelector(state => state.period.stats.cycleLengthRanges);

    if(!latestPeriod || !nextPeriodMessage(daysUntilNextPeriod, latestPeriod) || !avgPeriodLength || !avgCycleLength) {
        return <div>Loading...</div>
    }

    const message = nextPeriodMessage(daysUntilNextPeriod, latestPeriod);

    return <div className={"card stats"}>
        {message && latestPeriod && avgPeriodLength && avgCycleLength && <div className="card-body">
            <div className={""}>
            <DashboardStatsCardItem
                title={message.title}
                body={message.body}/>
            </div>
            <div className="">
            <div className={"mt-3"}>
                <DashboardStatsCardItem
                    title={"Average Period Duration:"}
                    body={`${avgPeriodLength.toFixed(1)} Days`}>
                </DashboardStatsCardItem>
            </div>
            <div className={"mt-3"}>
                <DashboardStatsCardItem
                    title={"Average Cycle Duration:"}
                    body={`${avgCycleLength.toFixed(1)} Days`}>
                </DashboardStatsCardItem>
            </div><div className={"mt-3"}>
                <DashboardStatsCardItem
                    title={"Periods range from:"}
                    body={`${cycleLengthRanges.min} - ${cycleLengthRanges.max} Days`}>
                </DashboardStatsCardItem>
            </div>
            </div>
        </div>}
    </div>
}

export default Stats;