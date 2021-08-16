
export const UserFormValidate = (user)=>{
    let isValid = true;
    let errors = {name:[], email:[], phone:[], city:[], zip:[], street:[] };
    if(!user.name || user.name.length === 0){
        errors.name.push("Name cannot be empty");
        isValid = false;
    }
    if(!user.email || user.email.length === 0){
        isValid = false;
        errors.email.push("Email cannot be empty");
    }
    if(!user.phone || user.phone.length === 0){
        isValid = false;
        errors.phone.push("Phone cannot be empty");
    }
    if(!user.street || user.street.length === 0){
        isValid = false;
        errors.street.push("Street cannot be empty");
    }
    if(!user.zip || user.zip.length === 0){
        isValid = false;
        errors.zip.push("Zip cannot be empty");
    }
    if(!user.city || user.city.length === 0){
        isValid = false;
        errors.city.push("City cannot be empty");
    }
    
    return {isValid, errors};
}
  