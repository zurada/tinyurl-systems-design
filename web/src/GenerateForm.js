import {Button, TextField} from "@material-ui/core";
import {Component} from "react";
import {red} from "@material-ui/core/colors";

export default class GenerateForm extends Component {
    constructor(props) {
        super(props);
        this.state = {value: '', result: '', isGenerated: false};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {    this.setState({value: event.target.value});  }
    handleSubmit(event) {
        console.log(process.env.REACT_APP_REDIRECT_URL);
        fetch("/create", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({url : this.state.value})

        }).then(response => {
            return response.json();
        }).then(response => {
            console.log(response);
            this.setState({
                result: response.key,
                isGenerated: true
            });
        });

        event.preventDefault();
    }

    render() {
        const { isGenerated, result } = this.state;

        if(!isGenerated){
            return (
                <div>
                <form onSubmit={this.handleSubmit}>
                    <TextField  label="Your URL" type="text" value={this.state.value} onChange={this.handleChange} />
                    <Button type="submit" variant="contained" color="primary"> GENERATE</Button>
                </form>
                </div>
            );
        }else{
            return (
                <div>
                <form onSubmit={this.handleSubmit}>
                    <TextField  label="Your URL" type="text" value={this.state.value} onChange={this.handleChange} />
                    <Button type="submit" variant="contained" color="primary"> GENERATE</Button>
                    <br/>
                    <br/>
                </form>
                    <br/>
                    <br/>
                    <p>Generated link:</p>
                    <h2>{process.env.REACT_APP_REDIRECT_URL}{result}</h2>
                </div>

            );
        }

    }
}