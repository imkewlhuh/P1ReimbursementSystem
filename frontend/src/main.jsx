import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Auth from './pages/auth';
import EmployeePage from './pages/employee';
import ManagerPage from './pages/manager';
import './App.css'
import Layout from './components/layout';

const router = createBrowserRouter([
    {
        path: "/auth",
        element: <Auth />
    },
    {
        path: "/home",
        element: <Layout />,
        children: [
            {
                path: "employee",
                element: <EmployeePage />
            },
            {
                path: "manager",
                element: <ManagerPage />
            }
        ]
    }
])

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>,
)
