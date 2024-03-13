import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import { jwtInterceptor } from './components/interceptor/Interceptor';
import {store} from './store-redux/store';
import { PersistGate } from 'redux-persist/integration/react';
import { persistStore } from 'redux-persist';
import { Provider } from 'react-redux';
//test commit
// jwtInterceptor();
const persistor = persistStore(store);
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>   
    <Provider store = {store}>
      <PersistGate loading = {null} persistor={persistor}>
        <App/>
      </PersistGate>  
    </Provider>
  </React.StrictMode>
);


reportWebVitals();

