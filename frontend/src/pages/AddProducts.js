import axios from "axios";
import { useState } from "react"
import { Validate } from "../components/Validate";
import { ProductFormValidate } from "./validations/ProductForm";
import { getProducts as listProducts } from "../redux/actions/productActions";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Swal from "sweetalert2";


export const AddProductPage = () => {
    const cleanUp = { productNumber: "", name: "", price: "", numberInStock: "", description: "" }
    const [product, setProduct] = useState(cleanUp);
    const handleOnChange = (e) => setProduct({ ...product, [e.target.name]: e.target.value });
    const [errorMessage, setErrorMessage] = useState({ isValid: false, errors: {} });
    const dispatch = useDispatch();

    const getProducts = useSelector((state) => state.getProducts);
    const { products, loading, error } = getProducts;

    useEffect(() => {
        dispatch(listProducts());
    }, [dispatch]);



    const handleSubmit = () => {
        const { isValid, errors } = ProductFormValidate(product);
        if (!isValid) {
            setErrorMessage({ isValid, errors });
            return;
        }
        setErrorMessage({ isValid: false, errors: [] });
        const url = `http://localhost:8080/products?productNumber=${product.productNumber}&price=${product.price}&name=${product.name}&description=${product.description}&numberInStock=${product.numberInStock}&operation=addnew`;
        axios.post(url, product).then(response => {
            if (response.status === 200) {
                Swal.fire({
                    title: 'Success',
                    text: 'Successfully created',
                    icon: 'success'
                  })
                setProduct(cleanUp);
                dispatch(listProducts());
            }
        })
    }

    const handleDelete = (e)=>{
        const url = `http://localhost:8080/products/${e.target.value}`;
        axios.delete(url).then(response => {
            if (response.status === 200) {
                dispatch(listProducts());
                Swal.fire({
                    title: 'DELETED',
                    text: 'Successfully deleted',
                    icon: 'success'
                  })
            }
        })
    }
    const handleDecreaseStock = (e)=>{
        const url = `http://localhost:8080/products?productNumber=${e.target.value}&numberInStock=1&operation=decreaseStock`;
        axios.post(url).then(response => {
            if (response.status === 200) {
                dispatch(listProducts());
            }else{
                alert(response.data.message);
            }
        }).catch(err=>{
            console.log(err);
            alert(err.response.data?err.response.data.message: err);
        })
    }
    const handleIncreaseStock = (e)=>{
        const url = `http://localhost:8080/products?productNumber=${e.target.value}&numberInStock=1&operation=increaseStock`;
        axios.post(url).then(response => {
            if (response.status === 200) {
                dispatch(listProducts());
            }
        })
    }

    return (
        <div className="row justify-content-center">
            <div className="col-md-6">
                <div className="card">
                    <div className="card-header">
                        <h3>Product Form</h3>
                    </div>
                    <div className="card-body">
                        <div className="mb-3">
                            <label for="name" className="form-label">Name</label>
                            <input type="text" className="form-control" id="name" value={product.name} name="name" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="name" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="productNumber" className="form-label">Product Number</label>
                            <input type="text" className="form-control" name="productNumber" value={product.productNumber} onChange={handleOnChange} id="productNumber" />
                            <div className="form-text text-danger">
                                <Validate field="productNumber" errorMessage={errorMessage} />
                            </div>
                        </div>

                        <div className="mb-3">
                            <label for="price" className="form-label">Price</label>
                            <input type="text" className="form-control" id="price" value={product.price} name="price" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="price" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="numberInStock" className="form-label">Number In Stock</label>
                            <input type="text" className="form-control" id="numberInStock" value={product.numberInStock} name="numberInStock" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="numberInStock" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="description" className="form-label">Description</label>
                            <textarea type="text" rows={5} className="form-control" id="description" value={product.description} name="description" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="description" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="d-grid gap-2">
                            <button onClick={handleSubmit} id="addProduct" className="btn btn-sm btn-primary">Add Product</button>
                        </div>
                    </div>
                </div>

            </div>

            {products?(
            <div className="col-md-6">
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>In Stock</th>
                            <th>Action</th>
                        </tr>

                    </thead>
                    <tbody>
                        {products.map((item) => (
                            <tr  key={item.productNumber}>
                                <td>{item.productNumber}</td>
                                <td>{item.name}</td>
                                <td>${item.price}</td>
                                <td>
                                    
                                    &nbsp; <button value={item.productNumber} onClick={handleIncreaseStock} className="btn btn-sm btn-success">+</button>
                                    &nbsp; <button  className="btn btn-primary"><b>{item.numberInStock}</b></button>
                                    &nbsp; <button value={item.productNumber} onClick={handleDecreaseStock} className="btn btn-sm btn-danger">-</button>
                                </td>
                                <td>
                                    <button id={"btnDelete_"+item.productNumber} className="btn btn-danger" value={item.productNumber} onClick={handleDelete}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            ):(<></>)}
        </div>

    );

}