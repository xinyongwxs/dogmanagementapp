import Overview from '../components/Overview';
import LoginForm from '../components/LoginForm';
import Layout from '../components/Layout';
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
	constructor(props) {
		super(props);
		this.state = {};
	}

	render() {
		return <div className="container"><Layout /></div>;
	}
}

App = withRouter(App);

render((<Router history={browserHistory}>
	<div>
		<Switch>
			<Route path="/loginDog" component={LoginForm} />
			<Route path="/" component={App} />
			<Redirect to="/" />
		</Switch>
	</div>
</Router>), document.getElementById("react"));