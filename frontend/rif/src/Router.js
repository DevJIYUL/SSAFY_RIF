import { createBrowserRouter } from 'react-router-dom'
import ErrorPageComponent from './Pages/ErrorPageComponent'
import LoginPageComponent from './Pages/LoginPageComponent'
import App from './App';


const router = createBrowserRouter([
    {  // main page
        path: "/",
        element: <App />,
        errorElement : <ErrorPageComponent />
    },
    {  // LoginPage
        path: "/login",
        element : <LoginPageComponent />,
    },
])

export default router;