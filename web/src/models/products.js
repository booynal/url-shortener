import dva from 'dva';

export default {
  namespace: 'products',
  state: [],
  reducers: {
    add(state, { payload: url }){
      let id = state.reduce( (previous,current) => previous.id > current.id ? previous : current).id;
      id++;
      return [...state, { url, id}];
    }
  },
};