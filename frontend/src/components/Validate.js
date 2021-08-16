export const Validate=(props)=>{
    const {field, errorMessage} = props;
    const errors = errorMessage.errors;
    let hasErrors = Object.keys(errors).filter(key=> key === field).length > 0;
    if(hasErrors){
        return errors[field].map(error=>(
           <li key={error} style={{color: "red", listStyle: "none"}}>{error}</li>
       ))
    }
    return (<></>);
}