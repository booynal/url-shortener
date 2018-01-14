import React from 'react';
import PropTypes from 'prop-types';
import { Input, Icon, Button } from 'antd';
import styles from '../routes/products.css';

const Add = ({ onAdd, onChange, input }) => {
  return (
    <div className={styles.addbox}>
      <Input
        placeholder="Enter your url"
        prefix={<Icon type="link" />}
        value={input}
        onChange={onChange}
        className={styles.addinput}
      />
      <Button type="primary" onClick={ onAdd } className={styles.addbtn}>Transfer</Button>
    </div>
  )
};

Add.propTypes = {
  onAdd: PropTypes.func.isRequired,
  input: PropTypes.string.isRequired,
};

export default Add;