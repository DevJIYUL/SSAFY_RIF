import { createBrowserRouter } from 'react-router-dom'
// import ErrorPageComponent from './Pages/ErrorPageComponent'
// import LoginPageComponent from './Pages/LoginPageComponent'
import HomePageComponent from './Pages/HomePageComponent'
import DescriptionPageComponent from './Pages/DescriptionPageComponent';
import DescriptionPageComponentTwo from './Pages/DescriptionPageComponentTwo';
import App from './App';


const router = createBrowserRouter([
    {  // main page
        path: "/",
        element: <App />,
        // errorElement : <ErrorPageComponent />
    },
    // {  // LoginPage
    //     path: "/login",
    //     element : <LoginPageComponent />,
    // },
    {
        // HomePage
        path : "/home",
        element : <HomePageComponent />,
    },
    {
        // Description Page (why RIF?)
        path : "/description/1",
        element : <DescriptionPageComponent />,
    },
    {
        // Description Page Two (How to Use)
        path : "/description/2",
        element : <DescriptionPageComponentTwo />,
    },
])

export default router;