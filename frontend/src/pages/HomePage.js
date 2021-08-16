// import "./HomeScreen.css";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

// Components
import Product from "../components/Product";

//Actions
import { getProducts as listProducts } from "../redux/actions/productActions";
import { Spinner } from "../components/Spinner";
import { AlertDanger } from "../components/AlertDanger";

const HomePage = () => {
  const dispatch = useDispatch();

  const getProducts = useSelector((state) => state.getProducts);
  const { products, loading, error } = getProducts;

  useEffect(() => {
    dispatch(listProducts());
  }, [dispatch]);

  return (
    <div className="container">
      <h2>Products</h2>
      <div className="row">
        {loading ? (
          <Spinner />
        ) : error ? (
          <AlertDanger message={error} />
        ) : (
          products.map((product) => (
            <Product
              key={product.productNumber}
              product={product}
            />
          ))
        )}
      </div>
    </div>
  );
};

export default HomePage;
