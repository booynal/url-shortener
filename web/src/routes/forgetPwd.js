import React from 'react';
import ReactDOM from 'react-dom';
import { connect } from 'dva';
import { Form, Icon, Input, Button, Checkbox, Select} from 'antd';
import styles from './login.css';
const FormItem = Form.Item;

class NormalLoginForm extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  }
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSubmit} className={styles.loginform}>
        <FormItem>
          <h1>Get password back</h1>
        </FormItem>
        <FormItem>
          {getFieldDecorator('mobile', {
            rules: [{ required: true, message: 'Please input your Mobile!' }],
          })(
            <Input prefix={<Icon type="mobile" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Mobile number" />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('verification', {
            rules: [{ required: true, message: 'Please input your Verification!' }],
          })(
            <Input prefix={<Icon type="key" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Verification code" className={styles.verification} addonAfter="get Verification"/>
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Please input your Password!' }],
          })(
            <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
          )}
        </FormItem>
        <FormItem>
          <Button type="primary" href="./#/IndexPage" className={styles.loginformbutton}>
            submit
          </Button>
        </FormItem>
      </Form>
    );
  }
}

const WrappedNormalLoginForm = Form.create()(NormalLoginForm);

//ReactDOM.render(<WrappedNormalLoginForm />);

export default connect()(WrappedNormalLoginForm);
