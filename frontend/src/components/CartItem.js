// import "./CartItem.css";
import { Link } from "react-router-dom";

const CartItem = ({ item, qtyChangeHandler,disable, removeHandler }) => {
  return (
    <tr key={item.productNumber}>
      <td>
        <Link to={`/product/${item.productNumber}`} className="cartItem__name">
        <p>{item.name}</p>
      </Link>
      </td>

      <td>
         ${item.price}
      </td>
      <td>
      <select class="form-select"
        value={item.quantity}
        onChange={(e) => qtyChangeHandler(item.productNumber, e.target.value)}
      >
        {[...Array(item.numberInStock).keys()].map((x) => (
          <option key={x + 1} value={x + 1}>
            {x + 1}
          </option>
        ))}
      </select>
      </td>
      <td>
      <button className="btn btn-danger"
        onClick={() => removeHandler(item.productNumber)}
      >
        <i className="fas fa-trash"></i>
      </button>
      </td>
    </tr>

    // <div className="cartitem">
    //   <Link to={`/product/${item.productNumber}`} className="cartItem__name">
    //     <p>{item.name}</p>
    //   </Link>
    //   <p className="cartitem__price">${item.price}</p>
    //   <select
    //     value={item.quantity}
    //     onChange={(e) => qtyChangeHandler(item.productNumber, e.target.value)}
    //     className="cartItem__select"
    //   >
    //     {[...Array(item.numberInStock).keys()].map((x) => (
    //       <option key={x + 1} value={x + 1}>
    //         {x + 1}
    //       </option>
    //     ))}
    //   </select>
    //   <button
    //     className="cartItem__deleteBtn"
    //     onClick={() => removeHandler(item.productNumber)}
    //   >
    //     <i className="fas fa-trash"></i>
    //   </button>
    // </div>
  );
};

export default CartItem;
