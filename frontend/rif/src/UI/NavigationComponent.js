import { useNavigate, useLocation } from "react-router-dom";
import {
  BottomNavigation,
  BottomNavigationAction,
  Paper,
  createTheme,
  ThemeProvider,
} from "@mui/material";
import {
  Equalizer,
  Search,
  Home,
  Person,
  LocalActivity,
} from "@mui/icons-material";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";

const outerTheme = createTheme({
  palette: {
    primary: {
      main: "#7DBC2F",
    },
  },
});

const NavigationComponent = (props) => {
  const navigate = useNavigate();
  const location = useLocation();

  console.log(location);
  const refreshToken = useSelector(
    (state) => state.auth.authentication.refreshToken
  );

  const [profileNavName, setProfileNavName] = useState("프로필");

  useEffect(() => {
    if (refreshToken) {
      setProfileNavName("프로필");
    } else {
      setProfileNavName("로그인");
    }
  }, [refreshToken]);

  let locationPath = location.pathname.substring(1).split("/")[0];

  if (
    [
      "login",
      "main",
      "badge",
      "change-profile",
      "change-password",
      "log",
      "achievement",
    ].includes(locationPath)
  ) {
    locationPath = "main";
  } else if (["description", "usage"].includes(locationPath)) {
    locationPath = "home";
  }

  const [navigateTarget, setNavigateTarget] = useState(locationPath);

  if (locationPath !== navigateTarget) {
    setNavigateTarget(locationPath);
  }

  const navigationURLList = ["search", "ranking", "home", "main", "lot"];
  const navigationName = ["검색", "랭킹", "홈페이지", profileNavName, "뽑기"];
  const navigationMUIIconList = [
    <Search />,
    <Equalizer />,
    <Home />,
    <Person />,
    <LocalActivity />,
  ];

  return (
    <ThemeProvider theme={outerTheme}>
      <Paper
        sx={{ position: "fixed", bottom: 0, left: 0, right: 0 }}
        elevation={3}
        id="navigationbar-paper"
        hidden={props.hidden}
      >
        <BottomNavigation
          showLabels
          value={navigateTarget}
          onChange={(event, newValue) => {
            console.log(event);
            if (event.type === "click") {
              navigate(`/${newValue}`);
            }
            setNavigateTarget(newValue);
          }}
        >
          {navigationURLList.map((value, index) => (
            <BottomNavigationAction
              label={navigationName[index]}
              value={value}
              key={`mui-nav-${index}`}
              icon={navigationMUIIconList[index]}
              style={{ width: "10vw", padding: "0px", minWidth: "56px" }}
            />
          ))}
        </BottomNavigation>
      </Paper>
    </ThemeProvider>
  );
};

export default NavigationComponent;
