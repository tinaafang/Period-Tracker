

function DashboardStatsCardItem(props) {
    const {title, body} = props;
    return <div className="card">
        <div className="card-body">
            <p className="card-title">{title}</p>
            <h3 className="card-text stats-number">{body}</h3>
        </div>
    </div>
}

export default DashboardStatsCardItem;