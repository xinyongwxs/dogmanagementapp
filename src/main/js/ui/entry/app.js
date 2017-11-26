import Overview from '../components/Overview';
import React from "react";
import { render } from "react-dom";

class App extends React.Component {
	render() {
		return <div className="container"><Overview /></div>;
	}
}

render(<App />, document.getElementById("react"));