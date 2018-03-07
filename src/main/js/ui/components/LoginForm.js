import React from "react";
import { render } from "react-dom";
import Form from "antd/lib/form";
import Icon from "antd/lib/icon";
import Input from "antd/lib/input";
import Button from "antd/lib/button";
import Checkbox from "antd/lib/checkbox";
import {withRouter} from 'react-router-dom';
import DogStore from "../stores/DogStore";
import DogActionCreators from "../actions/DogActionCreators";
const FormItem = Form.Item;

class NormalLoginForm extends React.Component {
  constructor() {
    super();
    this.state = {

    };
  }

  componentWillMount() {
    DogStore.addLoginListener(this.handleRedirect.bind(this));
  }

  handleRedirect() {
    location.href = "/home";
  }

  handleSubmit(e) {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        DogActionCreators.login(values);
      }
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSubmit.bind(this)} className="login-form">
        <FormItem>
          {getFieldDecorator('userName', {
            rules: [{ required: true, message: 'Please input your username!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Please input your Password!' }],
          })(
            <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
          )}
        </FormItem>
      </Form>
    );
  }
}

const WrappedNormalLoginForm = Form.create()(NormalLoginForm);

export default withRouter(WrappedNormalLoginForm);