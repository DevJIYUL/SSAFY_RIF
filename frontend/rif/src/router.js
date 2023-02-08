import { createBrowserRouter } from "react-router-dom";

import LoginPageComponent from "./Pages/LoginPageComponent";
import MainPageComponent from "./Pages/MainPageComponent";
import HomePageComponent from "./Pages/HomePageComponent";
import DescriptionPageComponent from "./Pages/DescriptionPageComponent";
import DescriptionPageComponentTwo from "./Pages/DescriptionPageComponentTwo";
import ErrorPageComponent from "./Pages/ErrorPageComponent";
import LotPageComponent from "./Pages/LotPageComponent";
import UserSearchComponent from "./Pages/UserSearchComponent";
import BadgePageComponent from "./Pages/BadgePageComponent";
import RankingPageComponent from "./Pages/RankingPageComponent";
import ChangeProfilePageComponent from "./Pages/ChangeProfilePageComponent";
import ChangePasswordPageComponent from "./Pages/ChangePasswordPageComponent";
import LogPageComponent from "./Pages/LogPageComponent";
import AchievementPageComponent from "./Pages/AchievementPageComponent";
import RootLayOut from "./UI/RootLayOut";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayOut />,
    // errorElement: <ErrorPageComponent />,
    children: [
      {
        // LoginPage
        path: "/login",
        element: <LoginPageComponent />,
      },
      {
        // HomePage
        index: true,
        path: "/home",
        element: <HomePageComponent />,
      },
      {
        // MainPage
        path: "/main",
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
      {
        // Change Profile Component
        path: "/change-profile",
        element: <ChangeProfilePageComponent />,
      },
      {
        // Change Password Page Component
        path: "/change-password",
        element: <ChangePasswordPageComponent />,
      },
      {
        // Log Page Component
        path: "/log",
        element: <LogPageComponent />,
      },
      {
        // Achievement Page Component
        path: "/achievement",
        element: <AchievementPageComponent />,
      },
    ],
  },
]);

export default router;
