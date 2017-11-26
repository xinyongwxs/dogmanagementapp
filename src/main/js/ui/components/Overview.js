import React from "react";
import { render } from "react-dom";
import DogActionCreators from "../actions/DogActionCreators";
import DogStore from "../stores/DogStore";
// import UploadForm from "./UploadForm";
import "../styles/Overview.css";
import Table from "antd/lib/table";
import Button from "antd/lib/button";
import Modal from "antd/lib/modal";
import Form from "antd/lib/form";
import Input from "antd/lib/input";
import Row from "antd/lib/row";
import Col from "antd/lib/col";
import Message from "antd/lib/message";
import Popconfirm from "antd/lib/popconfirm";
import Select from "antd/lib/select";

const FormItem = Form.Item;
const Option = Select.Option;
const { Column, ColumnGroup } = Table;

export default class Overview extends React.Component {
	constructor() {
		super();
		this.state = {
			data: null,
			dogType: "BreedingDog",
			dogTypeList: ["BreedingDog", "WorkingDog", "PetDog", "TrainingDog"],
			newDataForm: {
				visible: false
			}
		};
		this.selectedDogType = "BreedingDog";
	}

	componentDidMount() {
		
	}

	componentWillMount() {
		DogStore.addChangeListener(this.handleDogListData.bind(this));
		DogActionCreators.dogList(this.state.dogType);
	}

	refresh() {
		DogActionCreators.dogList(this.state.dogType);
	}

	deleteRecord(record) {

	}

	handleDogTypeChange(value) {
		this.selectedDogType = value;
		DogActionCreators.dogList(value);
	}

	handleDogListData(data) {
		var currState = Object.assign({}, this.state);
		currState.data = data;
		currState.dogType = this.selectedDogType;
		this.setState(currState);
	}

	showNewDogForm() {
		let currState = Object.assign({}, this.state);
		currState.newDataForm.visible = true;
		this.setState(currState);
	}

	render() {
		const { data } = this.state;
		let lines = [];
		let table = null;
		if (data) {
			const dogList = data.entity;
			if (dogList && dogList.forEach) {
				switch (this.state.dogType) {
					case "BreedingDog":
						dogList.forEach((row, idx) => {
							lines.push({
								key: idx,
								id: row.id,
								name: row.name,
								birthday: (new Date(row.birthday)).toLocaleString(),
								kennelId: row.kennelId,
								epDate: (new Date(row.epDate)).toLocaleString(),
								chipId: row.chipId,
								earId: row.earId,
								companyCode: row.companyCode,
								remarks: row.remarks,
								type: row.type,
								matingDate: (new Date(row.matingDate)).toLocaleString(),
								puppyBirthday: (new Date(row.puppyBirthday)).toLocaleString(),
								feeder: row.feeder,
								price: row.price,
								readyForSell: row.readyForSell ? "是" : "否",
								puppyAccount: row.puppyAccount,
								category: row.category
							});
						});
						break;
					case "PetDog":
						dogList.forEach((row, idx) => {
							lines.push({
								key: idx,
								id: row.id,
								name: row.name,
								birthday: (new Date(row.birthday)).toLocaleString(),
								kennelId: row.kennelId,
								epDate: (new Date(row.epDate)).toLocaleString(),
								chipId: row.chipId,
								earId: row.earId,
								companyCode: row.companyCode,
								remarks: row.remarks,
								type: row.type,
								trainingLevel: row.trainingLevel,
								trainer: row.trainer,
								apperance: row.apperance,
								readyForSell: row.readyForSell ? "是" : "否",
								price: row.price,
								category: row.category,
								breeder: row.breeder
							});
						});
						break;
					case "WorkingDog":
						dogList.forEach((row, idx) => {
							lines.push({
								key: idx,
								id: row.id,
								name: row.name,
								birthday: (new Date(row.birthday)).toLocaleString(),
								kennelId: row.kennelId,
								epDate: (new Date(row.epDate)).toLocaleString(),
								chipId: row.chipId,
								earId: row.earId,
								companyCode: row.companyCode,
								remarks: row.remarks,
								type: row.type,
								trainingLevel: row.trainingLevel,
								trainer: row.trainer,
								readyForWorking: row.readyForWorking ? "是" : "否",
								price: row.price,
								category: row.category
							});
						});
						break;
					case "TrainingDog":
						dogList.forEach((row, idx) => {
							lines.push({
								key: idx,
								id: row.id,
								name: row.name,
								birthday: (new Date(row.birthday)).toLocaleString(),
								kennelId: row.kennelId,
								epDate: (new Date(row.epDate)).toLocaleString(),
								chipId: row.chipId,
								earId: row.earId,
								companyCode: row.companyCode,
								remarks: row.remarks,
								type: row.type,
								ownerPhone: row.ownerPhone,
								courses: row.courses,
								startDate: (new Date(row.startDate)).toLocaleString(),
								endDate: (new Date(row.endDate)).toLocaleString(),
								ownerId: row.ownerId,
								gender: row.gender
							});
						});
						break;
				}

			}
		}

		switch (this.state.dogType) {
			case "BreedingDog":
				table = (<Table dataSource={lines}>
							<Column title="序号" dataIndex="id" key="id" />
							<Column title="名字" dataIndex="name" key="name"/>
							<Column title="出生日期" dataIndex="birthday" key="birthday" />
							<Column title="牌号" dataIndex="kennelId" key="kennelId" />
							<Column title="epDate" dataIndex="epDate" key="epDate" />
							<Column title="芯片号" dataIndex="chipId" key="chipId" />
							<Column title="公司编号" dataIndex="companyCode" key="companyCode" />
							<Column title="说明" dataIndex="remarks" key="remarks" />
							<Column title="品种" dataIndex="type" key="type"/>
							<Column title="交配日期" dataIndex="matingDate" key="matingDate" />
							<Column title="幼崽出生日期" dataIndex="puppyBirthday" key="puppyBirthday" />
							<Column title="饲养员" dataIndex="feeder" key="feeder" />
							<Column title="是否可以卖" dataIndex="readyForSell" key="readyForSell" />
							<Column title="幼崽数量" dataIndex="puppyAccount" key="puppyAccount" />
							<Column title="分类" dataIndex="category" key="category" />
							<Column title="操作" key="action" render={
								(text, record) => {
									return (<div>
										<Popconfirm 
											title="是否确定删除此条记录?" 
											onConfirm={this.deleteRecord.bind(this, record)} 
											okText="Yes" cancelText="No">
											<a href="#">删除</a>
										</Popconfirm>
									</div>);
								}
							} />
						</Table>);
				break;
			case "PetDog":
				table = (<Table dataSource={lines}>
							<Column title="序号" dataIndex="id" key="id" />
							<Column title="名字" dataIndex="name" key="name"/>
							<Column title="出生日期" dataIndex="birthday" key="birthday" />
							<Column title="牌号" dataIndex="kennelId" key="kennelId" />
							<Column title="epDate" dataIndex="epDate" key="epDate" />
							<Column title="芯片号" dataIndex="chipId" key="chipId" />
							<Column title="公司编号" dataIndex="companyCode" key="companyCode" />
							<Column title="说明" dataIndex="remarks" key="remarks" />
							<Column title="品种" dataIndex="type" key="type"/>
							<Column title="训练等级" dataIndex="trainingLevel" key="trainingLevel" />
							<Column title="训练者" dataIndex="trainer" key="trainer" />
							<Column title="饲养员" dataIndex="feeder" key="feeder" />
							<Column title="是否可以卖" dataIndex="readyForSell" key="readyForSell" />
							<Column title="价格" dataIndex="price" key="price" />
							<Column title="分类" dataIndex="category" key="category" />
							<Column title="哺乳犬" dataIndex="breeder" key="breeder" />
							<Column title="操作" key="action" render={
								(text, record) => {
									return (<div>
										<Popconfirm 
											title="是否确定删除此条记录?" 
											onConfirm={this.deleteRecord.bind(this, record)} 
											okText="Yes" cancelText="No">
											<a href="#">删除</a>
										</Popconfirm>
									</div>);
								}
							} />
						</Table>);
			break;
			case "TrainingDog":
				table = (<Table dataSource={lines}>
							<Column title="序号" dataIndex="id" key="id" />
							<Column title="名字" dataIndex="name" key="name"/>
							<Column title="出生日期" dataIndex="birthday" key="birthday" />
							<Column title="牌号" dataIndex="kennelId" key="kennelId" />
							<Column title="epDate" dataIndex="epDate" key="epDate" />
							<Column title="芯片号" dataIndex="chipId" key="chipId" />
							<Column title="公司编号" dataIndex="companyCode" key="companyCode" />
							<Column title="说明" dataIndex="remarks" key="remarks" />
							<Column title="品种" dataIndex="type" key="type"/>
							<Column title="主人电话" dataIndex="ownerPhone" key="ownerPhone" />
							<Column title="课程" dataIndex="courses" key="courses" />
							<Column title="起始时间" dataIndex="startDate" key="startDate" />
							<Column title="结束时间" dataIndex="endDate" key="endDate" />
							<Column title="主人编号" dataIndex="ownerId" key="ownerId" />
							<Column title="性别" dataIndex="gender" key="gender" />
							<Column title="操作" key="action" render={
								(text, record) => {
									return (<div>
										<Popconfirm 
											title="是否确定删除此条记录?" 
											onConfirm={this.deleteRecord.bind(this, record)} 
											okText="Yes" cancelText="No">
											<a href="#">删除</a>
										</Popconfirm>
									</div>);
								}
							} />
						</Table>);
			break;
			case "WorkingDog":
				table = (<Table dataSource={lines}>
							<Column title="序号" dataIndex="id" key="id" />
							<Column title="名字" dataIndex="name" key="name"/>
							<Column title="出生日期" dataIndex="birthday" key="birthday" />
							<Column title="牌号" dataIndex="kennelId" key="kennelId" />
							<Column title="epDate" dataIndex="epDate" key="epDate" />
							<Column title="芯片号" dataIndex="chipId" key="chipId" />
							<Column title="公司编号" dataIndex="companyCode" key="companyCode" />
							<Column title="说明" dataIndex="remarks" key="remarks" />
							<Column title="品种" dataIndex="type" key="type"/>
							<Column title="训练等级" dataIndex="trainingLevel" key="trainingLevel" />
							<Column title="训练者" dataIndex="trainer" key="trainer" />
							<Column title="是否可以工作" dataIndex="readyForWorking" key="readyForWorking" />
							<Column title="价格" dataIndex="price" key="price" />
							<Column title="分类" dataIndex="category" key="category" />
							<Column title="操作" key="action" render={
								(text, record) => {
									return (<div>
										<Popconfirm 
											title="是否确定删除此条记录?" 
											onConfirm={this.deleteRecord.bind(this, record)} 
											okText="Yes" cancelText="No">
											<a href="#">删除</a>
										</Popconfirm>
									</div>);
								}
							} />
						</Table>);
			break;
		}

		return (<div>
			<Row className="btn-row" type="flex" justify="start">
				<Col><Button className="btn" type="primary" onClick={this.refresh.bind(this)}>刷新</Button></Col>
				<Col><Button className="btn" type="primary" onClick={this.refresh.bind(this)}>增加</Button></Col>
				<Col><Select className="btn" defaultValue="BreedingDog" style={{ width: 120 }} onChange={this.handleDogTypeChange.bind(this)}>
				      <Option value="BreedingDog">生育犬</Option>
				      <Option value="WorkingDog">工作犬</Option>
				      <Option value="PetDog">宠物犬</Option>
				      <Option value="TrainingDog">训练犬</Option>
				    </Select>
				</Col>
			</Row>
			<Row type="flex" justify="center">
				<Col span={22}>
					{table}
				</Col>
			</Row>
			
		</div>);
	}
}