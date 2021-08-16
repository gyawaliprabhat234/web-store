import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux"
import { Link } from "react-router-dom";
import { getOrders as listOrders } from "../redux/actions/orderActions";

import { Spinner } from "../components/Spinner";
import { AlertDanger } from "../components/AlertDanger";

export const Orders = () => {
    const dispatch = useDispatch();

    const getOrders = useSelector((state) => state.getOrders);
    const { orders, loading, error } = getOrders;

    useEffect(() => {
        dispatch(listOrders());
    }, [dispatch]);

    return (
        <div className="container">
            <h2>ORDERS</h2>
            <div className="row">
                {loading ? (
                    <Spinner />
                ) : error ? (
                    <AlertDanger message={error} />
                ) : (
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th>OrderId</th>
                                <th>Order Status</th>
                                <th>User Name</th>
                                <th>Payment Type</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {orders.map((order, index) => (
                                    <tr key={order.orderId}>
                                        <td>{index +1}</td>
                                        <td>{order.status}</td>
                                        <td>{order.owner.name}</td>
                                        <td>{order.payment.creditCardType}</td>
                                        <td>

                                            <Link to={"/order/" + order.orderId} className="btn btn-primary">View</Link>
                                        </td>
                                    </tr>
                                ))}
                        </tbody>
                    </table>



                )}
            </div>
        </div>
    );
};
