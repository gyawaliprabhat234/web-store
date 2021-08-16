import * as actionTypes from "../constants/orderConstants";
import axios from "axios";
const url = "http://localhost:8080";

export const getOrders = () => async (dispatch) => {
  try {
    dispatch({ type: actionTypes.GET_ORDERS_REQUEST });

    const { data } = await axios.get(url + "/orders");

    dispatch({
      type: actionTypes.GET_ORDERS_SUCCESS,
      payload: data.orders,
    });
  } catch (error) {
    dispatch({
      type: actionTypes.GET_ORDERS_FAIL,
      payload:
        error.response && error.response.data.message
          ? error.response.data.message
          : error.message,
    });
  }
};

export const getOrderDetails = (orderId) => async (dispatch) => {
  try {
    dispatch({ type: actionTypes.GET_ORDER_DETAILS_REQUEST });

    const { data } = await axios.get(url + `/orders/${orderId}`);

    dispatch({
      type: actionTypes.GET_ORDER_DETAILS_SUCCESS,
      payload: data,
    });
  } catch (error) {
    dispatch({
      type: actionTypes.GET_ORDER_DETAILS_FAIL,
      payload:
        error.response && error.response.data.message
          ? error.response.data.message
          : error.message,
    });
  }
};

export const removeOrderDetails = () => (dispatch) => {
  dispatch({ type: actionTypes.GET_ORDER_DETAILS_RESET });
};
