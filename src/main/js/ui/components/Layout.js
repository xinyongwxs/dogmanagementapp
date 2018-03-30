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
		this.dragDrop = {
			selectedItem: null,
			isStart: false
		};
		let line = [];
		let gridLayout = [];
		let gridHeightNum = 0;

		// Initialize the width and height of grid items
		for (let i = 0; i < 8; i++) {
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
		gridLayout = this.freshGridLayout();

		this.boxingGridItems(line, gridLayout);

		this.state = {
			gridItemMatrix: line
		};
	}

	freshGridLayout() {
		let gridLayout = [];
		for (let i = 0; i < this.containerHeightNum; i++) {
			let top = i * this.gridHeight;
			let gridLayoutLine = [];
			for (let j = 0; j < this.containerWidthNum; j++) {
				gridLayoutLine.push({
					value: 0,
					left: j * this.gridWidth,
					top: top
				});
			}
			
			gridLayout.push(gridLayoutLine);
		}
		return gridLayout;
	}

	boxingGridItems(line, gridLayout, occupiedGrid, gridItem) {
		if (occupiedGrid && gridItem) {
			let leftNum = occupiedGrid.left / this.gridWidth;
			let topNum = occupiedGrid.top / this.gridHeight;
			//Specific gridItem occupy grid first
			for (let i = 0; i < gridItem.heightNum; i++) {
				for (let j = 0; j < gridItem.widthNum; j++) {
					gridLayout[i + topNum][j + leftNum].value = gridItem.itemIndex + 1;
				}
			}
		}
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
					//Add capacity if unit is empty
					if (unit.value === 0) {
						//Mark the temp start of item
						if (capacity === 0) {
							tempStart = jj;
						}
						capacity++;
						if (capacity >= val.widthNum) {
							//Judge whether the vertical capacity is enough or not.
							for (let a = 0; a < val.heightNum; a++) {
								if (theArray[ii + a][jj].value > 0) {
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
					gridLayout[tempTopStart + ver][tempLeftStart + hor].value = idx + 1;
				}
			}
			theLines[idx].leftNum = tempLeftStart;
			theLines[idx].topNum = tempTopStart;
		});
	}

	componentDidMount() {
		window.onmousemove = this.mouseMoveHandler.bind(this);
		window.onmouseup = this.mouseUpHandler.bind(this);
	}

	componentWillMount() {

	}

	componentWillUpdate() {
		// this.widthOffset = (window.innerWidth - this.containerWidthNum * this.gridWidth) / 2;
	}

	componentDidUpdate() {

	}

	mouseDownHandler(gridItem, event) {
		event.preventDefault();
		event.stopPropagation();
		this.dragDrop.isStart = true;
		this.selectedItem = gridItem;
	}

	mouseMoveHandler(event) {
		event.preventDefault();
		event.stopPropagation();
		if (this.dragDrop.isStart) {
			let gridItem = this.selectedItem;
			let e = event;
			let x = e.clientX;
			let y = e.clientY;
			let leastIdx = 0;
			let widthOffset = (window.innerWidth - this.containerWidthNum * this.gridWidth) / 2;
			let gridLayout = this.freshGridLayout();
			let min = gridLayout[0][0];
			gridLayout.forEach((li, lineNum) => {
				li.forEach((item, idx) => {
					let tempDistance = Math.sqrt((widthOffset + min.left + this.gridWidth / 2 - x) * (widthOffset + min.left + this.gridWidth / 2 - x) + (min.top + this.gridHeight / 2 - y) * (min.top + this.gridHeight / 2 - y));
					let itemDistance = Math.sqrt((widthOffset + item.left + this.gridWidth / 2 - x) * (widthOffset + item.left + this.gridWidth / 2 - x) + (item.top + this.gridHeight / 2 - y) * (item.top + this.gridHeight / 2 - y));
					if (tempDistance > itemDistance) {
						min = item;
					}
				});
			});
			//Pass the specific item object, update the leftNum and topNum and then 
			//occupy the grids according to the grid which is the nearest to the selected item first,
			// the second or the last, reorg the other grid items.
			let currState = Object.assign({}, this.state);
			this.boxingGridItems(currState.gridItemMatrix, gridLayout, min, gridItem);
			this.setState(currState);
		}
	}

	mouseUpHandler(event) {
		event.preventDefault();
		event.stopPropagation();
		this.dragDrop.isStart = false;
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
									mouseDownHandler={this.mouseDownHandler.bind(this, val)}
									// mouseMoveHandler={this.mouseMoveHandler.bind(this)}
									// mouseUpHandler={this.mouseUpHandler.bind(this)} 
									/>));
		});
		return (<div style={containerStyle}>
				{layout}
			</div>);
	}
}


export default Layout;