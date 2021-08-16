import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

// Actions
import { getProductDetails } from "../redux/actions/productActions";
import { addToCart } from "../redux/actions/cartActions";
import { Spinner } from "../components/Spinner";
import { AlertDanger } from "../components/AlertDanger";

const ProductPage = ({ match, history }) => {
  const [quantity, setQuantity] = useState(1);
  const dispatch = useDispatch();


  const productDetails = useSelector((state) => state.getProductDetails);
  const { loading, error, product } = productDetails;

  useEffect(() => {
    if (product && match.params.id !== product.productNumber) {
      dispatch(getProductDetails(match.params.id));
    }
  }, [dispatch, match, product]);

  const addToCartHandler = () => {
    dispatch(addToCart(product.productNumber, quantity));
    history.push(`/cart`);
  };
  return (
    <div>
      <br></br>
      {loading ? (
        <Spinner />
      ) : error ? (
       <AlertDanger message={error} />
      ) : (
        <div className="row justify-content-center">
          <div className="col-md-6 col-sm-8">
            <div className="card">
              <div class="card-header bg-dark text-white text-center">
                <h3 className="card-title">{product.name}</h3>
              </div>
              <div className="card-body">
                <h5 className={"card-text"} >Price: ${product.price}</h5>
                <hr />
                <p className={"card-text"}>
                  Status: {"       "}
                  <span>
                    {product.numberInStock > 0 ? " In Stock("+product.numberInStock+")" : " Out of Stock"}
                  </span>
                </p>
                <hr />
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon3">Quantity</span>
                  <select id="productQuantity" className="form-select" value={quantity} onChange={(e) => setQuantity(e.target.value)}>
                    {[...Array(product.numberInStock).keys()].map((x) => (
                      <option key={x + 1} value={x + 1}>
                        {x + 1}
                      </option>
                    ))}
                  </select>
                </div>
                <hr />

                <p className="card-text">{product.description}</p>

              </div>
              <div className="card-footer">
                <div class="d-grid gap-2">
                  <button id="addToCart" className="btn btn-primary" disabled={product.numberInStock == 0}  onClick={addToCartHandler}>
                    Add To Cart
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ProductPage;
