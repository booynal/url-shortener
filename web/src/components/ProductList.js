import React from 'react';
import PropTypes from 'prop-types';
import { Table, Popconfirm, Button, Icon, Tooltip } from 'antd';

const ProductList = ({ onCopy, products }) => {
  const columns = [{
    dataIndex: 'id',
  },{
    dataIndex: 'url',
  },{
    dataIndex: 'shorturl',
  }, {
    render: (text, record) => {
      return (
        <Popconfirm onConfirm={() => onCopy(record.shorturl)}>
          <Tooltip placement="topLeft" title="Copy" arrowPointAtCenter>
            <Button icon="copy"></Button>
          </Tooltip>
        </Popconfirm>
      );
    },
  }];
  return (
    <Table
      dataSource={products}
      columns={columns}
      pagination = {{ pageSize: 8}}
    />
  );
};

ProductList.propTypes = {
  onCopy: PropTypes.func.isRequired,
  products: PropTypes.array.isRequired,
};

export default ProductList;