import dva from 'dva';
import './index.css';

// 1. Initialize
//const app = dva();
const app = dva({
    initialState: {
    	products: [
        	{ url: 'http://www.fdjksfjsadkfjdsakf.ivy.com.cn', shorurl:'http://www.IZhome.ivy.com.cn', id: 1 },
       		{ url: 'http://www.fdsfdsjfsajflksadjfklsdj.ziv.com.cn', shorurl:'http://www.IZhome.ziv.com.cn', id: 2 },
     	],
    },
});

// 2. Plugins
// app.use({});

// 3. Model
// app.model(require('./models/example').default);
app.model(require('./models/products').default);
app.model(require('./models/add').default);

// 4. Router
app.router(require('./router').default);

// 5. Start
app.start('#root');
