import React from 'react';
import { connect } from 'dva';
import { Form, Icon, Input, Button, Checkbox, Col, Row} from 'antd';
import styles from './main.css';

function IndexPage() {
  return (
    <div class="ant-layout ant-layout-has-sider">
    	<div class={styles.sider}></div>
	</div>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
