import React from "react";
import { render } from "react-dom";
import {withRouter} from 'react-router-dom';
import GridItem from "./GridItem";

class Layout extends React.Component {
	constructor() {
		super();
		this.gridWidth = 150;
		this.gridHeight = 100;
		this.containerWidthNum = 7;
		this.widthOffset = 0;
		let line = [];
		let gridLayout = [];
		let gridHeightNum = 0;

		// Initialize the width and height of grid items
		for (let i = 0; i < 8; i++) {
			let widthNumTemp = Math.ceil(Math.random() * 3);
			let heightNumTemp = Math.ceil(Math.random() * 3);
			// if (i === 0) {
			// 	widthNumTemp = 3;
			// 	heightNumTemp = 3;
			// } else if (i === 1) {
			// 	widthNumTemp = 1;
			// 	heightNumTemp = 1;
			// } else if (i === 2) {
			// 	widthNumTemp = 3;
			// 	heightNumTemp = 3;
			// } else if (i === 3) {
			// 	widthNumTemp = 1;
			// 	heightNumTemp = 1;
			// } else if (i === 4) {
			// 	widthNumTemp = 2;
			// 	heightNumTemp = 2;
			// }

			gridHeightNum += heightNumTemp;
			line.push({
				widthNum: widthNumTemp,
				heightNum: widthNumTemp,
				leftNum: 0,
				topNum: 0,
				itemIndex: i
			});
		}


		this.containerHeightNum = gridHeightNum;

		//Initialize grid layout
		gridLayout = this.freshGridLayout();

		this.boxingGridItems(line, gridLayout);

		this.state = {
			gridItemMatrix: line
		};
	}

	freshGridLayout() {
		let gridLayout = [];
		for (let i = 0; i < this.containerHeightNum; i++) {
			let gridLayoutLine = [];
			for (let j = 0; j < this.containerWidthNum; j++) {
				gridLayoutLine.push(0);
			}
			
			gridLayout.push(gridLayoutLine);
		}
	}

	boxingGridItems(line, gridLayout) {
		/*
			exception:
			0 3*3
			1 1*1
			2 3*3
			3 1*1
			4 2*2
		*/
		line.forEach((val, idx, theLines) => {
			let leftNumber = 0;
			let rightNumber = 0;
			let tempLeftStart = 0;
			let tempTopStart = 0;
			// theLines[idx].itemIndex = idx;
			gridLayout.some((gridLine, ii, theArray) => {
				let capacity = 0;
				let tempStart = 0;
				let isAvailable = false;
				gridLine.some((unit, jj, theLine) => {
					//Mark the temp start of item
					if (unit === 0 && capacity === 0) {
						tempStart = jj;
						capacity++;
						if (jj === theLine.length - 1) {
							if (capacity >= val.widthNum) {
								//Judge whether the vertical capacity is enough or not.
								for (let a = 0; a < val.heightNum; a++) {
									if (gridLayout[ii + a][jj] > 0) {
										return false;
									}
								}
								isAvailable = true;
								return true;
							}
						}
					}
					//Add capacity if unit is empty
					else if (unit === 0) {
						capacity++;
						if (capacity >= val.widthNum) {
							//Judge whether the vertical capacity is enough or not.
							for (let a = 0; a < val.heightNum; a++) {
								if (gridLayout[ii + a][jj] > 0) {
									return false;
								}
							}
							isAvailable = true;
							return true;
						}
					}
					//Empty capacity
					 else {
					 	tempStart = 0;
						capacity = 0;
					}
				});

				if (isAvailable) {
					tempLeftStart = tempStart;
					tempTopStart = ii;
					return true;
				}
			});

			//Occupy the space with item number.
			for (let ver = 0; ver < val.heightNum; ver++) {
				for (let hor = 0; hor < val.widthNum; hor++) {
					gridLayout[tempTopStart + ver][tempLeftStart + hor] = idx + 1;
				}
			}
			theLines[idx].leftNum = tempLeftStart;
			theLines[idx].topNum = tempTopStart;
		});
	}

	componentDidMount() {

	}

	componentWillMount() {

	}

	componentWillUpdate() {
		// this.widthOffset = (window.innerWidth - this.containerWidthNum * this.gridWidth) / 2;
	}

	componentDidUpdate() {

	}

	mouseDownHandler(event) {
		let e = event;
	}

	mouseMoveHandler(event) {
		let e = event;
	}

	mouseUpHandler(event) {
		let e = event;
		let x = e.clientX;
		let y = e.clientY;
		let leastIdx = 0;

		let gridLayout = this.freshGridLayout();
		
	}

	render() {
		let itemMatrix = this.state.gridItemMatrix;
		let layout = [];
		let containerWidth = this.containerWidthNum * this.gridWidth;
		let containerHeight = 0;

		let containerStyle = {
			width: containerWidth,
			height: "auto",
			margin: "0 auto",
			border: "none",
			borderColor: "red",
			display: "block"
		};
		this.state.gridItemMatrix.forEach((val, idx) => {
			layout.push((<GridItem widthUnit={this.gridWidth}
									heightUnit={this.gridHeight}
									widthNum={val.widthNum}
									heightNum={val.heightNum} 
									leftNum={val.leftNum}
									topNum={val.topNum}
									itemIndex={val.itemIndex}
									widthOffset={(window.innerWidth - this.containerWidthNum * this.gridWidth) / 2} 
									mouseDownHandler={this.mouseDownHandler.bind(this)}
									mouseMoveHandler={this.mouseMoveHandler.bind(this)}
									mouseUpHandler={this.mouseUpHandler.bind(this)} />));
		});
		return (<div style={containerStyle}>
				{layout}
			</div>);
	}
}


export default Layout;