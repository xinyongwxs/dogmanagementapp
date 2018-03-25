import React from "react";
import { render } from "react-dom";

export default class GridItem extends React.Component {
	constructor() {
		super();
	}

	componentDidMount() {

	}

	componentWillMount() {

	}

	componentWillUpdate() {

	}

	render() {
		let widthUnit = this.props.widthUnit;
		let heightUnit = this.props.heightUnit;
		let widthNum = this.props.widthNum;
		let heightNum = this.props.heightNum;
		let leftNum = this.props.leftNum;
		let topNum = this.props.topNum;

		let gridItemStyle = {
			width: widthNum * widthUnit,
			height: heightNum * heightUnit,
			borderStyle: "solid",
			borderWidth: 1,
			position: "absolute",
			marginLeft: leftNum * widthUnit,
			marginTop: topNum * heightUnit,
			backgroundColor: "blue"
		};
		return (<div style={gridItemStyle}>
			
		</div>);
	}
}