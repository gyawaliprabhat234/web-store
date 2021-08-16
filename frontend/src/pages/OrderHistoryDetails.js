import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";
import { AlertDanger } from "../components/AlertDanger";
import { Spinner } from "../components/Spinner";
import { getOrderDetails } from "../redux/actions/orderActions";


export const OrderHistoryDetails = ({ match, history }) => {
    const orderDetails = useSelector((state) => state.getOrderDetails);
    const dispatch = useDispatch();
    const { loading, error, order } = orderDetails;
    const [status, setStatus] = useState();
    let user = {};
    let payment = {}
    let orderedProducts = [];

    useEffect(() => {
        if (order && match.params.id != order.orderId) {
            dispatch(getOrderDetails(match.params.id));
        }
    }, [dispatch, match, order]);

    if (loading === false && !error) {
        if (order && order.length !== 0) {
            user = order.owner;
            payment = order.payment;
            orderedProducts = order.orderedProducts;
            // setStatus(order.status);
        }
    }
    const getTotalPrice = () => {
        return orderedProducts.reduce((quantity, item) => Number(item.quantity) * Number(item.product.price) + quantity, 0);
    }

    const handleStatusChanged = (e) => {
        const url = "http://localhost:8080";
        axios.put(url + `/orders/${order.orderId}/status?status=` + e.target.value)
            .then(response => {
                if (response.status === 200) {
                    setStatus(e.target.value);
                    history.push("/orders");
                } else {
                    alert("error");
                }
            })
    }

    const handleOnDelete = () => {
        const url = "http://localhost:8080";
        axios.delete(url + `/orders/${order.orderId}`)
            .then(response => {
                if (response.status === 200) {
                    Swal.fire({
                        title: 'DELETED',
                        text: 'Successfully deleted',
                        icon: 'success'
                      })
                    history.push("/orders");
                } else {
                    alert(response.data.message);
                }
            })
    }

    return (
        loading ? (
            <Spinner />
        ) : error ? (
            <AlertDanger message={error} />
        ) : (
            <>
                <br></br>
                <div className="row justify-content-center">

                    <div className="col-lg-9">



                        <div className="row">
                            <div class="col-lg-5 col-md-5">
                                <div className="card">
                                    <div className="card-header">
                                        <h4>User Information</h4>
                                    </div>
                                    <div className="card-body">
                                        <ul className="list-group list-group-flush">
                                            <li className="list-group-item">Name <span style={{ float: "right" }}>{user.name}</span></li>
                                            <li className="list-group-item">Email <span style={{ float: "right" }}>{user.email}</span></li>
                                            <li className="list-group-item">Phone <span style={{ float: "right" }}>{user.phone}</span></li>
                                            <li className="list-group-item">Address <span style={{ float: "right" }}>{user.city}, {user.street}, {user.zip}</span></li>
                                            <li className="list-group-item"><b>Payment</b></li>
                                            <li className="list-group-item">Card Type <span style={{ float: "right" }}>{payment.creditCardType}</span></li>
                                            <li className="list-group-item">Card Number <span style={{ float: "right" }}>{payment.number}</span></li>
                                            <li className="list-group-item">Expiry Date <span style={{ float: "right" }}>{payment.validDate}</span></li>


                                        </ul>
                                    </div>


                                </div>
                            </div>
                            <div className="col-lg-7 col-md-7">

                                {orderedProducts.length === 0 ? (
                                    <div>
                                        Your Cart Is Empty <Link to="/">Go Back</Link>
                                    </div>
                                ) : (<>
                                    <div className="row">
                                        <div class="col-lg-1 col-md-1">Status:</div>


                                        <div className="col-lg-4 col-md-4">
                                            <select type="text" value={order.status} class="form-select" id="orderStatus" name="orderStatus" onChange={handleStatusChanged} >
                                                <option value="PLACED">PLACED</option>
                                                <option value="SHIPPED">SHIPPED</option>
                                                <option value="DELIVERED">DELIVERED</option>
                                            </select>
                                            <br />
                                        </div>
                                        <div className="col-lg-6 col-md-6 ">
                                            <div class="d-grid gap-2">
                                                <button className="btn btn-danger" onClick={handleOnDelete}>Delete Order</button>
                                            </div>
                                        </div>

                                    </div>

                                    <table className="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Product Name</th>
                                                <th>Unit Price</th>
                                                <th>Quantity</th>
                                                <th>Price</th>
                                            </tr>

                                        </thead>
                                        <tbody>
                                            {orderedProducts.map((item) => (
                                                <tr key={item.product.productNumber}>
                                                    <td>{item.product.name}</td>
                                                    <td>${item.product.price}</td>
                                                    <td>{item.quantity}</td>
                                                    <td>${item.quantity * item.product.price}</td>
                                                </tr>
                                            ))}
                                            <tr>
                                                <th colSpan={3}>Total Price</th>
                                                <th>${getTotalPrice()}</th>
                                            </tr>
                                        </tbody>
                                    </table>
                                </>
                                )}
                            </div>


                        </div>
                    </div>
                </div>
            </>
        ));
};

