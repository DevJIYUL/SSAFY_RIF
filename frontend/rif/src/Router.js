import { createBrowserRouter } from "react-router-dom";
import LoginPageComponent from "./Pages/LoginPageComponent";
import MainPageComponent from "./Pages/MainPageComponent";
import HomePageComponent from "./Pages/HomePageComponent";
import DescriptionPageComponent from "./Pages/DescriptionPageComponent";
import DescriptionPageComponentTwo from "./Pages/DescriptionPageComponentTwo";
import LotComponent from "./Pages/LotComponent";
import BadgePageComponent from "./Pages/BadgePageComponent";
import TestComponent from "./Pages/TestComponent";

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
  {
    // HomePage
    path: "/home",
    element: <HomePageComponent />,
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
    element: <LotComponent />,
  },
  {
    // sectiontitle test
    path: "/badge",
    element: <BadgePageComponent />,
  },
  {
    //test
    path: "/test",
    element: <TestComponent />,
  },
]);

export default router;
