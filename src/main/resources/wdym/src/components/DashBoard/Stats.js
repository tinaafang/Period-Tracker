import {useSelector} from "react-redux";
import DashboardStatsCardItem from "./DashboardStatsCardItem";


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
            body: "coming today"
        }
    }
}




function Stats() {
    const latestPeriod = useSelector(state => state.period.latestPeriod);
    const daysUntilNextPeriod = useSelector(state => state.period.daysUntilNextPeriod);
    const avgBleedLength = useSelector(state => state.period.avgBleedLength);
    const avgCycleLength =  useSelector(state => state.period.avgCycleLength);

    if(!latestPeriod || !nextPeriodMessage(daysUntilNextPeriod, latestPeriod)) {
        return <div>Loading...</div>
    }

    const message = nextPeriodMessage(daysUntilNextPeriod, latestPeriod);

    return <div className={"card"}>
        <div className="card-body row">
            <div className={"col-md-4"}>
            <DashboardStatsCardItem
                title={message.title}
                body={message.body}/>
        </div>
            <div className={"col-md-4"}>
                <DashboardStatsCardItem
                    title={"Average Period:"}
                    body={`${avgBleedLength.toFixed(1)} Days`}>
                </DashboardStatsCardItem>
            </div>
            <div className={"col-md-4"}>
                <DashboardStatsCardItem
                    title={"Average Cycle:"}
                    body={`${avgCycleLength.toFixed(1)} Days`}>
                </DashboardStatsCardItem>
            </div>
        </div>

    </div>
}

export default Stats;