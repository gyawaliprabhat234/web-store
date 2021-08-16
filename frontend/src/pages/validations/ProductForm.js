
export const ProductFormValidate = (product)=>{
    const{productNumber ,name, price, numberInStock, description} = product;
    let isValid = true;
    let errors = {productNumber:[], name:[], price:[], numberInStock:[], description:[] };
    if(!productNumber && productNumber.length ===0){
        errors.productNumber.push("Product Number cannot be empty");
        isValid = false;
    }
    if(!name || name.length===0){
        isValid = false;
        errors.name.push("Name cannot be empty");
    }
    if(!price || price.length === 0){
        isValid = false;
        errors.price.push("Price cannot be empty");
    }
    if(!numberInStock || numberInStock.length===0){
        isValid = false;
        errors.numberInStock.push("Stock cannot be empty");
    }
    if(!description || description.length===0){
        isValid = false;
        errors.description.push("Description cannot be empty");
    }

    return {isValid, errors};
}
  