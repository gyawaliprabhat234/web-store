
export const PaymentFormValidate = (payment)=>{
    const{creditCardType, number, validDate, validationCode} = payment;
    let isValid = true;
    let errors = {creditCardType:[], number:[], validDate:[], validationCode:[] };
    if(!creditCardType && creditCardType.length ===0){
        errors.creditCardType.push("Credit card type cannot be empty");
        isValid = false;
    }
    if(!number || number.length===0){
        isValid = false;
        errors.number.push("Number cannot be empty");
    }else if(!ValidateCreditCardNumber(number)){
        isValid = false;
        errors.number.push("Credit Card Number not valid");
    }
    if(!validDate || validDate.length === 0){
        isValid = false;
        errors.validDate.push("Date cannot be empty");
    }
    if(!validationCode || validationCode.length===0){
        isValid = false;
        errors.validationCode.push("Code cannot be empty");
    }
    return {isValid, errors};
}

function ValidateCreditCardNumber(ccNum) {

    var visaRegEx = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
    var mastercardRegEx = /^(?:5[1-5][0-9]{14})$/;
    var amexpRegEx = /^(?:3[47][0-9]{13})$/;
    var discovRegEx = /^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/;
    var isValid = false;
  
    if (visaRegEx.test(ccNum)) {
      isValid = true;
    } else if(mastercardRegEx.test(ccNum)) {
      isValid = true;
    } else if(amexpRegEx.test(ccNum)) {
      isValid = true;
    } else if(discovRegEx.test(ccNum)) {
      isValid = true;
    }
    return isValid;
  }
  