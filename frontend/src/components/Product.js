// import "./Product.css";
import { Link } from "react-router-dom";

const Product = (props) => {
  const { productNumber, name, price, description, numberInStock } = props.product;
  return (
    <div className="col-md-4 col-sm-4">
      <div className="card" style={{ width: "18rem" }}>
        <div class="card-header bg-dark text-white text-center">
          <h3 className="card-title">{name}</h3>
        </div>
        <div className="card-body">

          <h5 className={"card-text"} >Price: ${price}</h5>
          <p className={"card-text"}>Number In Stock: {numberInStock}</p>
          <p className="card-text">{description.substring(0, 100)}...</p>
          <div class="d-grid gap-2">
            <Link to={"/product/" + productNumber} className="btn btn-primary btn-block">View Product</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Product;
