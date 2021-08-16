export const AlertDanger = (props) => {

    return (
        <div className="alert alert-danger d-flex align-items-center" role="alert">
            <div>
                {props.message}
            </div>
        </div>
    );
}
