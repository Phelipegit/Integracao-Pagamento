import { useEffect, useState } from "react";
import eye from "./assets/eye.png";
import hidden from "./assets/hidden.png";
import jsonExample from "./example.json";
import "./css/App.css";
import { useFormState } from "react-dom";
function App() {
  const[jsonMessages,setJsonMessages] = useState([]);
  const[passwordStatus,setPasswordStatus] = useState(false);
      useEffect(() => {
        async function loadJsonMessages() {
          const data = await fetch("",{
            method:"GET"
          });

          const response = await response.json();

          setJsonMessages(response.messages);
        }
      },[]) 


  function updateStatusPassword() {
    setPasswordStatus(!passwordStatus);
  }

  function handleSubmmit(event) {
    event.preventDefault();
  }

  return (
    <div id="container-principal">
      <div id="container-form">
        <form onSubmit={handleSubmmit}>
          <label>CPF</label>
          <input placeholder="CPF" />
          <label>E-mail </label>
          <input className="input-form" id="input-form-email" type="text" placeholder="example@gmail.com" onChange={(e => setForm.data(e.target.value))}/>
          <label>Password</label>
          <input className="input-form" id="input-form-password" placeholder="Your password" type={passwordStatus ? "text" : "password" } onChange={(e => setForm.password(e.target.value))}></input>
          <button className="button-img"  onClick={() => updateStatusPassword()} type="button"><img id="img-statusPassword" src={passwordStatus ? eye : hidden}></img></button>
          <button type="submmit">Registrar-se</button>
        </form>
      </div>
      <p>{jsonMessages["ERROR_EMAIL"]}</p>
    </div>
  )
}

export default App;