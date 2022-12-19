import {createRoot} from 'react-dom/client';
import { BrowserRouter } from "react-router-dom";
import { QueryClientProvider } from 'react-query';

import { queryClient } from './service/queryClient';

import App from './App';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <QueryClientProvider client={queryClient}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </QueryClientProvider>,
);

