import { useState } from "react";
import { Validate } from "../components/Validate";
import { PaymentFormValidate } from "./validations/PaymentForm";

export const PaymentPage =(props)=>{
    const user = props.location.state.user;
    console.log(user);
    const cleanUp = {creditCardType : "", number: "", validDate: "", validationCode:"" }
    const [payment, setPayment] = useState(cleanUp);
    const handleOnChange = (e) => setPayment({ ...payment, [e.target.name]: e.target.value });
    const [errorMessage, setErrorMessage] = useState({ isValid: false, errors: {} });

    const handleSubmit = () => {
        const { isValid, errors } = PaymentFormValidate(payment);
        if (!isValid) {
            setErrorMessage({ isValid, errors });
            return;
        }
        setErrorMessage({ isValid: false, errors: [] });
        props.history.push("/orderdetails", {user: user, payment: payment });
    }

    return (
        <div className="row justify-content-center">
            <div className="col-md-6">
                <div className="card">
                    <div className="card-header">
                        Payment Information
                    </div>
                    <div className="card-body">
                        <div class="mb-3">
                            <label for="creditCardType" class="form-label">Card Type</label>
                            <select type="text" class="form-select" id="creditCardType" name="creditCardType" onChange={handleOnChange} >
                                <option value="">-Select Card Type-</option>
                                <option value="VISA">VISA</option>
                                <option value="MASTER">MASTER</option>
                                <option value="DEBIT">DEBIT</option>
                                </select>
                            <div class="form-text text-danger">
                                <Validate field="creditCardType" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="number" class="form-label">Card Number</label>
                            <input type="text" class="form-control" name="number" id="number" onChange={handleOnChange} />
                            <div class="form-text text-danger">
                                <Validate field="number" errorMessage={errorMessage} />
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="validDate" class="form-label">Expiry Date</label>
                            <input type="date" class="form-control" id="validDate" name="validDate" onChange={handleOnChange} />
                            <div class="form-text text-danger">
                                <Validate field="validDate" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="validationCode" class="form-label">Code</label>
                            <input type="text" class="form-control" id="validationCode" name="validationCode" onChange={handleOnChange} />
                            <div class="form-text text-danger">
                                <Validate field="validationCode" errorMessage={errorMessage} />
                            </div>
                        </div>

                        <div class="d-grid gap-2">
                            <button onClick={handleSubmit} className="btn btn-sm btn-primary">Proceed To Checkout</button>
                        </div>


                    </div>
                </div>

            </div>
        </div>

    );

}