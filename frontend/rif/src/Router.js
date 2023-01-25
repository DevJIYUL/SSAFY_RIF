import { createBrowserRouter } from "react-router-dom"
import LoginPageComponent from "./Pages/LoginPageComponent"
import MainPageComponent from "./Pages/MainPageComponent"

const router = createBrowserRouter([
  {
    // main page
    path: "/",
    element: <MainPageComponent />,
  },
  {
    // LoginPage
    path: "/login",
    element: <LoginPageComponent />,
  },
])

export default router
