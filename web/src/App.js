import logo from './logo.svg';
import './App.css';
import {Button, TextField} from "@material-ui/core";
import GenerateForm from "./GenerateForm";

function App() {
    return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <GenerateForm></GenerateForm>
      </header>
    </div>
  );
}

export default App;
