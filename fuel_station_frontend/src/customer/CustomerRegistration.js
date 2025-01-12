import {useState} from "react";
import axios from "axios";
function CustomerRegistration(){

    const [values,setCustomers] = useState({
      customerName :'',
      customerNIC  : '',
      customerEmail  : '',
      customerUserName : '',
      customerPassword  : ''
    })

    const handleChanges = (e) => {
        setCustomers({...values,[e.target.name]:e.target.value})
    }
 
    const handleSubmit =  async (e) => {
        e.preventDefault()
        try{
       await axios.post('http://localhost:8080/api/customer',values, {
        headers: {"Content-Type": "application/json"},
    });
       setCustomers({
        customerName :'',
      customerNIC  : '',
      customerEmail  : '',
      customerUserName : '',
      customerPassword  : ''
       });
       alert("Customer successfully added");
       console.log(values);
    
    }catch (error){
        alert("Network error occured .plz try again");
    }
}
    return(
        <div className = "container">
            <h1>Registration Form</h1>
            <form onSubmit = {handleSubmit} >
                
                 <label htmlFor ="customerName">Name : </label>  
                <input
                 type = "text" 
                 placeholder="Enter the customername" 
                 name = "customerName" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.customerName}/>
                <br/><br/> 
               
                <label htmlFor ="customerNIC">NIC : </label>
                <input type = "text" 
                placeholder="Enter the NIC No" 
                name = "customerNIC" 
                onChange={(e) =>handleChanges(e)}  
                value = {values.customerNIC} 
                required/>
                <br/><br/>
                
                
                <label htmlFor ="customerEmail">Email : </label>
                <input type = "emailt"
                 placeholder="Enter the email" 
                 name = "customerEmail" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.customerEmail}/>
                <br/><br/>
                
                <label htmlFor ="customerUserName">User Name : </label>
                <input type = "text" 
                placeholder="Enter the username" 
                name = "customerUserName" 
                onChange={(e) =>handleChanges(e)} 
                required 
                value = {values.customerUserName}/>
                <br/><br/>
               
                
               
                <label htmlFor ="customerPassword">Password : </label>
                <input type = "password"
                 placeholder="Enter the Password" 
                 name = "customerPassword" 
                 onChange={(e) =>handleChanges(e)} 
                 required 
                 value = {values.customerPassword }/>
                <br/><br/>
 
                <button type = "submit" >Submit</button>
                </form>
            </div>
            );
}
export default CustomerRegistration;