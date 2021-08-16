import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";

// Components
import CartItem from "../components/CartItem";

// Actions
import { addToCart, removeFromCart } from "../redux/actions/cartActions";

const CartPage = (props) => {
  const dispatch = useDispatch();

  const cart = useSelector((state) => state.cart);
  const { cartItems } = cart;

  useEffect(() => { }, []);

  const qtyChangeHandler = (id, quantity) => {
    dispatch(addToCart(id, quantity));
  };

  const removeFromCartHandler = (id) => {
    dispatch(removeFromCart(id));
  };

  const getCartCount = () => {
    return cartItems.reduce((quantity, item) => Number(item.quantity) + quantity, 0);
  };

  const getCartSubTotal = () => {
    return cartItems
      .reduce((price, item) => price + item.price * item.quantity, 0)
      .toFixed(2);
  };

  const goToNextPage = ()=>{
    props.history.push("/user");
  }

  return (
    <>
      <br></br>
      <div className="row justify-content-center">

        <div className="col-lg-9">
        <h2>Shopping Cart</h2>
          <div className="row">
            <div className="col-lg-6 col-md-6">
             

              {cartItems.length === 0 ? (
                <div>
                  Your Cart Is Empty <Link to="/">Go Back</Link>
                </div>
              ) : (<>
                <table className="table table-bordered">
                  <thead>
                    <tr>
                      <th>Product Name</th>
                      <th>Price</th>
                      <th>Quantity</th>
                      <th>Action</th>
                    </tr>

                  </thead>
                  <tbody>
                    {cartItems.map((item) => (
                      <CartItem
                        key={item.productNumber}
                        item={item}
                        disable={false}
                        qtyChangeHandler={qtyChangeHandler}
                        removeHandler={removeFromCartHandler}
                      />
                    ))}
                  </tbody>
                </table>
              </>
              )}
            </div>

            <div class="col-lg-3 col-md-3">
              <div className="card">
                <div className="card-header">
                  <h4>Subtotal (<span id="getCountItemInCart">{getCartCount()}</span>) items</h4>
                </div>
                <div className="card-body">
                  <h5>${getCartSubTotal()}</h5>
                </div>
               
                <div class="d-grid gap-2">
                  <button disabled={getCartCount() === 0} onClick={goToNextPage} className="btn btn-sm btn-primary">Proceed To Checkout</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default CartPage;
