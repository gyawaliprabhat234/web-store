import * as actionTypes from "../constants/orderConstants";
export const getOrderReducer = (state = { orders: [] }, action) => {
  switch (action.type) {
    case actionTypes.GET_ORDERS_REQUEST:
      return {
        loading: true,
        orders: [],
      };
    case actionTypes.GET_ORDERS_SUCCESS:
      return {
        orders: action.payload,
        loading: false,
      };
    case actionTypes.GET_ORDERS_FAIL:
      return {
        loading: false,
        error: action.payload,
      };
    default:
      return state;
  }
};

export const getOrderDetailsReducer = (state = { order: {} }, action) => {
  switch (action.type) {
    case actionTypes.GET_ORDER_DETAILS_REQUEST:
      return {
        loading: true,
      };
    case actionTypes.GET_ORDER_DETAILS_SUCCESS:
      return {
        loading: false,
        order: action.payload,
      };
    case actionTypes.GET_ORDER_DETAILS_FAIL:
      return {
        loading: false,
        error: action.payload,
      };
    case actionTypes.GET_ORDER_DETAILS_RESET:
      return {
        order: {},
      };
    default:
      return state;
  }
};
