import React from "react";
import { render } from "react-dom";
import "./GridItem.less";

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
			left: leftNum * widthUnit + this.props.widthOffset,
			top: topNum * heightUnit,
			backgroundColor: "blue",
			textAlign: "center",
			fontSize: 40,
			lineHeight: heightNum * heightUnit + "px"
		};
		return (<div style={gridItemStyle}
		 			className="gridItem"
		 			onMouseDown={this.props.mouseDownHandler}
		 			onMouseMove={this.props.mouseMoveHandler}
		 			onMouseUp={this.props.mouseUpHandler}
		 			>
			{this.props.itemIndex}
		</div>);
	}
}