import "./App.css";
import { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

// Components
import Navbar from "./components/Navbar";

// Pages
import HomePage from "./pages/HomePage";
import ProductPage from "./pages/ProductPage";
import CartPage from "./pages/CartPage";
import { UserInformation } from "./pages/PersonalInformationPage";
import { PaymentPage } from "./pages/PaymentPage";
import OrderDetailsPage from "./pages/OrderDetailsPage";
import{AddProductPage} from "./pages/AddProducts";
import {OrderHistoryDetails} from "./pages/OrderHistoryDetails"
import { Orders } from "./pages/Orders";
import Search from "./pages/Search";

function App() {

  return (
    <Router>
      <Navbar  />
      <main className="app">
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route exact path="/product/:id" component={ProductPage} />
          <Route exact path="/addproduct" component={AddProductPage} />
          <Route exact path="/order/:id" component={OrderHistoryDetails} />
          <Route exact path="/cart" component={CartPage} />
          <Route exact path="/user" component={UserInformation} />
          <Route exact path="/payment" component={PaymentPage} />
          <Route exact path="/orders" component={Orders} />
          <Route exact path="/orderdetails" component={OrderDetailsPage} />
          <Route exact path="/search" component={Search} />
        </Switch>
      </main>
    </Router>
  );
}

export default App;
