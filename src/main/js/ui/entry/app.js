import Overview from '../components/Overview';
import LoginForm from '../components/LoginForm';
import React from "react";
import { render } from "react-dom";
import {
	BrowserRouter as Router,
	browserHistory,
	Route,
	Switch,
	Redirect,
	withRouter
} from "react-router-dom";

class App extends React.Component {
	render() {
		return <div className="container"><Overview /></div>;
	}
}

let appCom = withRouter(App);

render((<Router history={browserHistory}>
	<div>
		<Switch>
			<Route path="/loginDog" component={LoginForm} />
			<Route path="/home" component={appCom} />
		</Switch>
	</div>
</Router>), document.getElementById("react"));