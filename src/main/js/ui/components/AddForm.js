import React from "react";
import { render } from "react-dom";
import Button from "antd/lib/button";
import Modal from "antd/lib/modal";
import Form from "antd/lib/form";
import Input from "antd/lib/input";
import Upload from "antd/lib/upload";
import Icon from "antd/lib/icon";
import Message from "antd/lib/message";

class InnerAddForm extends React.Component {
	constructor() {
		super();
		this.state = {};
	}

	componentDidMount() {
	}

	componentWillMount() {
		
	}

	componentWillUpdate() {

	}

	render() {
		return (<Modal visible={this.props.visible}
						title="添加新记录"
						okText="添加"
						cancelText="取消">
			
		</Modal>);
	}
}

const AddForm = Form.create()(InnerAddForm);

export default AddForm;