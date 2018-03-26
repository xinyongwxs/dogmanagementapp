import React from "react";
import { render } from "react-dom";
import {withRouter} from 'react-router-dom';
import GridItem from "./GridItem";

class Layout extends React.Component {
	constructor() {
		super();
		this.gridWidth = 300;
		this.gridHeight = 180;
		this.containerWidthNum = 4;
		let line = [];
		let gridLayout = [];
		let gridHeightNum = 0;

		//Initialize the width and height of grid items
		for (let i = 0; i < 5; i++) {
			let widthNumTemp = Math.ceil(Math.random() * 3);
			let heightNumTemp = Math.ceil(Math.random() * 3);
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
		for (let i = 0; i < this.containerHeightNum; i++) {
			let gridLayoutLine = [];
			for (let j = 0; j < this.containerWidthNum; j++) {
				gridLayoutLine.push(0);
			}
			
			gridLayout.push(gridLayoutLine);
		}

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
			theLines[idx].itemIndex = idx;
			gridLayout.some((gridLine, ii, theArray) => {
				let capacity = 0;
				let tempStart = 0;
				let isAvailable = false;
				gridLine.some((unit, jj) => {
					//Mark the temp start of item
					if (unit === 0 && capacity === 0) {
						tempStart = jj;
						capacity++;
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

		this.state = {
			gridItemMatrix: line
		};
	}

	componentDidMount() {

	}

	componentWillMount() {

	}

	componentWillUpdate() {

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
			borderStyle: "solid",
			borderWidth: 1,
			borderColor: "red"
		};
		this.state.gridItemMatrix.forEach((val, idx) => {
			layout.push((<GridItem widthUnit={this.gridWidth}
									heightUnit={this.gridHeight}
									widthNum={val.widthNum}
									heightNum={val.heightNum} 
									leftNum={val.leftNum}
									topNum={val.topNum}
									itemIndex={val.itemIndex} />));
		});
		return (<div style={containerStyle}>
				{layout}
			</div>);
	}
}


export default Layout;