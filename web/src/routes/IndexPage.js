import React from 'react';
import { connect } from 'dva';
import styles from './IndexPage.css';

function IndexPage() {
  return (
    <div className={styles.normal}>
      <h1 className={styles.title}>Welcome to IZ home!</h1>
      <div className={styles.welcome} />
      <div><a href="./#">logout</a></div>
      <div><a href="./#/products">products</a></div>
    </div>
  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
