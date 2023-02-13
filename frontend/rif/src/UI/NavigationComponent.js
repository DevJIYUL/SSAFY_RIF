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
import { useState } from "react";

const outerTheme = createTheme({
  palette: {
    primary: {
      main: "#7DBC2F",
    },
  },
});

const NavigationComponent = () => {
  const navigate = useNavigate();
  const location = useLocation();

  let locationPath = location.pathname.substring(1).split("/")[0];
  console.log(locationPath);

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
  console.log(locationPath);
  const [navigateTarget, setNavigateTarget] = useState(locationPath);
  if (locationPath !== navigateTarget) {
    setNavigateTarget(locationPath);
  }
  console.log(locationPath, navigateTarget);

  const navigationURLList = ["search", "ranking", "home", "main", "lot"];
  const navigationName = ["검색", "랭킹", "홈페이지", "프로필", "뽑기"];
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
      >
        <BottomNavigation
          showLabels
          value={navigateTarget}
          onChange={(event, newValue) => {
            // console.log(newValue);
            navigate(`/${newValue}`);
            setNavigateTarget(newValue);
          }}
          // sx={{ color: "#000000" }}
        >
          {navigationURLList.map((value, index) => (
            <BottomNavigationAction
              // sx={{ color: "#000000" }}
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
