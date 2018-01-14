import dva from 'dva';

export default {
  namespace: 'inputs',
  state: {
    input: 'http://www.example.com/fjdshfasdhfdshahasdhasg/fdhjsafhadshfdsahfkhsdakfhsadkhfjksadlfhksdjafhksgahghjfjda.',
  },
  reducers: {
    change(state, { payload: url, shorturl }){
      return  {input: url}
    },
  },
};