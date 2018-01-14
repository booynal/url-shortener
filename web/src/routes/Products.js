import React from 'react';
import { connect } from 'dva';
import ProductList from '../components/ProductList';
import Add from '../components/Add';

const Products = ({ dispatch, products, inputs }) => {
  function handleCopy(shorturl) {
    dispatch({
      type: 'products/copy',
      payload: shorturl,
    });
  }
  function handleAdd() {
    dispatch({
      type: 'products/add',
      payload: inputs.input,
    });
  }
  function handelChange(e) {
    dispatch({
      type: 'inputs/change',
      payload: e.target.value
    })
  }
  return (
    <div>
      <Add onAdd={handleAdd} onChange={handelChange} input={inputs.input}/>
      <br/>
      <hr/>
      <ProductList onCopy={handleCopy} products={products} />
    </div>
  );
};

// export default Products;
export default connect(({ inputs, products }) => ({
  inputs, products
}))(Products);
