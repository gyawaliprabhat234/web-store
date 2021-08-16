// import "./HomeScreen.css";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

// Components
import Product from "../components/Product";

//Actions
import { getProducts as listProducts } from "../redux/actions/productActions";
import { Spinner } from "../components/Spinner";
import { AlertDanger } from "../components/AlertDanger";

const Search = () => {
  const dispatch = useDispatch();
 const [searchQuery, setSearch]= useState("");
  const getProducts = useSelector((state) => state.getProducts);
  const { products, loading, error } = getProducts;
  const [tempProducts, setTempProducts] = useState([...products]); 
  const handleOnChange = (e)=>{
     let temp =products.filter(product=>product.name.toLowerCase().indexOf(e.target.value.toLowerCase())>= 0 )
      setTempProducts(temp);
      setSearch(e.target.value);
  }

  useEffect(() => {
    dispatch(listProducts());
  }, [dispatch]);

  return (
    <div className="container">
      <h5>Browse Products</h5>

      <div className="col-md-4">
      <label for="search" className="form-label">Search</label>
          <input type="text" value={searchQuery} className="form-control" id="search" name="search" onChange={handleOnChange} />
          <br></br>
        </div>
      <div className="row">
        {loading ? (
          <Spinner />
        ) : error ? (
          <AlertDanger message={error} />
        ) : (
          tempProducts.map((product) => (
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

export default Search;
