import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const Navbar = () => {
  const cart = useSelector((state) => state.cart);
  const { cartItems } = cart;

  const getCartCount = () => {
    return cartItems.reduce((quantity, item) => Number(item.quantity) + quantity, 0);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <a className="navbar-brand">WEB STORE</a>
        <div className="collapse navbar-collapse" id="navbarText">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link id="linkToProducts" className="nav-link active" to="/">Products</Link>
            </li>
            <li className="nav-item">
              <Link id="linkToSearch" className="nav-link active" to="/search">Search</Link>
            </li>
            <li className="nav-item">
              <Link id="linkToOrders" className="nav-link active" to="/orders">Orders</Link>
            </li>
            <li id="linkToAddProduct" className="nav-item">
              <Link className="nav-link active" to="/addproduct">Add Product</Link>
            </li>


          </ul>
          <Link id="linkToCart" to="/cart" className="navbar-brand">
            <i className="fas fa-shopping-cart"></i>
            <span>
              Cart <span style={{ color: "yellow" }} className="navbar-brand">{getCartCount()}</span>
            </span>
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
