import axios from "axios";
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { removeAllFromCart } from "../redux/actions/cartActions";

// Actions

const OrderDetailsPage = (props) => {
    const { user, payment } = props.location.state;
    const dispatch = useDispatch();

    const cart = useSelector((state) => state.cart);
    const { cartItems } = cart;


    const getCartCount = () => {
        return cartItems.reduce((quantity, item) => Number(item.quantity) + quantity, 0);
    };

    const getCartSubTotal = () => {
        return cartItems
            .reduce((price, item) => price + item.price * item.quantity, 0)
            .toFixed(2);
    };

    const comfirmOrder = () => {
        let orderedProducts = [];
        cartItems.forEach(items => {
            let orderedProduct = {
                product: {
                    productNumber: items.productNumber,
                    name: items.name,
                    description: items.description,
                    price: items.price,
                    numberInStock: items.numberInStock
                },
                quantity: parseInt(items.quantity)
            }
            orderedProducts.push(orderedProduct);

        });
        let data= {
            owner: user,
            payment: payment,
            orderedProducts: orderedProducts
        }
        console.log(data);
        axios.post("http://localhost:8080/orders", data).then(response=>{
            if(response.status===200){
                dispatch(removeAllFromCart());
                props.history.push("/order/"+ response.data.orderId);
            }
        });
    }

    return (
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


                            {cartItems.length === 0 ? (
                                <div>
                                    Your Cart Is Empty <Link to="/">Go Back</Link>
                                </div>
                            ) : (<>
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
                                        {cartItems.map((item) => (
                                            <tr key={item.productNumber}>
                                                <td>{item.name}</td>
                                                <td>${item.price}</td>
                                                <td>{item.quantity}</td>
                                                <td>${item.quantity * item.price}</td>
                                            </tr>
                                        ))}
                                        <tr>
                                            <th colSpan={3}>Total Price</th>
                                            <th>${getCartSubTotal()}</th>
                                        </tr>
                                    </tbody>
                                </table>
                            </>
                            )}
                            <div class="d-grid gap-2">
                                <button disabled={getCartCount() === 0} onClick={comfirmOrder} className="btn btn-sm btn-primary">Confirm Order</button>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </>
    );
};

export default OrderDetailsPage;
