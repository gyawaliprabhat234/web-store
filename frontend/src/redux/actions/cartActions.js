import * as actionTypes from "../constants/cartConstants";
import axios from "axios";
const url = "http://localhost:8080";
export const addToCart = (id, quantity) => async (dispatch, getState) => {
  const { data } = await axios.get(url+ `/products/${id}`);

  dispatch({
    type: actionTypes.ADD_TO_CART,
    payload: {
      productNumber: data.productNumber,
      name: data.name,
      description: data.description,
      price: data.price,
      numberInStock: data.numberInStock,
      quantity : quantity
    },
  });

  //localStorage.setItem("cart", JSON.stringify(getState().cart.cartItems));
};

export const removeFromCart = (id) => (dispatch, getState) => {
  dispatch({
    type: actionTypes.REMOVE_FROM_CART,
    payload: id,
  });
};

export const removeAllFromCart = () => (dispatch, getState) => {
  dispatch({
    type: actionTypes.REMOVE_ALL_FROM_CART
  });

 // localStorage.setItem("cart", JSON.stringify(getState().cart.cartItems));
};
