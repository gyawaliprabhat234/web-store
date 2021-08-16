import { useState } from "react"
import { Validate } from "../components/Validate";
import { UserFormValidate } from "./validations/UserForm";

export const UserInformation = (props) => {
    const cleanUp = { name: "", email: "", phone: "", city: "", zip: "", street:"" }
    const [user, setUser] = useState(cleanUp);
    const handleOnChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });
    const [errorMessage, setErrorMessage] = useState({ isValid: false, errors: {} });
    const handleSubmit = () => {
        const { isValid, errors } = UserFormValidate(user);
        if (!isValid) {
            setErrorMessage({ isValid, errors });
            return;
        }
        setErrorMessage({ isValid: false, errors: [] });
        props.history.push("/payment", { user: user });
    }

    return (
        <div className="row justify-content-center">
            <div className="col-md-6">
                <div className="card">
                    <div className="card-header">
                        User Information
                    </div>
                    <div className="card-body">
                        <div className="mb-3">
                            <label for="name" className="form-label">Name</label>
                            <input type="text" className="form-control" id="name" name="name" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="name" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="email" className="form-label">Email address</label>
                            <input type="text" className="form-control" name="email" onChange={handleOnChange} id="email" />
                            <div className="form-text text-danger">
                                <Validate field="email" errorMessage={errorMessage} />
                            </div>
                        </div>

                        <div className="mb-3">
                            <label for="phone" className="form-label">Phone</label>
                            <input type="text" className="form-control" id="phone" name="phone" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="phone" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="street" className="form-label">Street</label>
                            <input type="text" className="form-control" id="street" name="street" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="street" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="city" className="form-label">City</label>
                            <input type="text" className="form-control" id="city" name="city" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="city" errorMessage={errorMessage} />
                            </div>
                        </div>
                        <div className="mb-3">
                            <label for="zip" className="form-label">Zip</label>
                            <input type="text" className="form-control" id="zip" name="zip" onChange={handleOnChange} />
                            <div className="form-text text-danger">
                                <Validate field="zip" errorMessage={errorMessage} />
                            </div>
                        </div>

                        <div className="d-grid gap-2">
                            <button onClick={handleSubmit} className="btn btn-sm btn-primary">Proceed To Checkout</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    );

}