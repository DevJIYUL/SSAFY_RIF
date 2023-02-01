import { createBrowserRouter } from "react-router-dom"
import LoginPageComponent from "./Pages/LoginPageComponent"
import MainPageComponent from "./Pages/MainPageComponent"
import HomePageComponent from "./Pages/HomePageComponent"
import DescriptionPageComponent from "./Pages/DescriptionPageComponent"
import DescriptionPageComponentTwo from "./Pages/DescriptionPageComponentTwo"
import ErrorPageComponent from "./Pages/ErrorPageComponent"
import LotPageComponent from "./Pages/LotPageComponent"
import UserSearchComponent from "./Pages/UserSearchComponent"
import BadgePageComponent from "./Pages/BadgePageComponent"
import RankingPageComponent from "./Pages/RankingPageComponent"
import App from "./App"

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPageComponent />,
  },
  {
    // LoginPage
    path: "/login",
    element: <LoginPageComponent />,
  },
  {
    // HomePage
    path: "/home",
    element: <HomePageComponent />,
  },
  {
    // MainPage
    path: "/index",
    element: <MainPageComponent />,
  },
  {
    // Description Page (why RIF?)
    path: "/description/1",
    element: <DescriptionPageComponent />,
  },
  {
    // Description Page Two (How to Use)
    path: "/description/2",
    element: <DescriptionPageComponentTwo />,
  },
  {
    // Lotto Component
    path: "/lot",
    element: <LotPageComponent />,
  },
  {
    // User Search Component
    path: "/search",
    element: <UserSearchComponent />,
  },
  {
    // sectiontitle test
    path: "/badge",
    element: <BadgePageComponent />,
  },
  {
    // Ranking Page Component
    path: "/ranking",
    element: <RankingPageComponent />,
  },
])

export default router
