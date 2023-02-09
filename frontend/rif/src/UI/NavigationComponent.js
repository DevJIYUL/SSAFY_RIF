import { useNavigate } from "react-router-dom";
import { BottomNavigation, BottomNavigationAction, Paper } from "@mui/material";
import {
  Equalizer,
  Search,
  Home,
  Person,
  LocalActivity,
} from "@mui/icons-material";
import { useState } from "react";

const NavigationComponent = () => {
  const [navigateTarget, setNavigateTarget] = useState("");
  const navigate = useNavigate();

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
    <Paper
      sx={{ position: "fixed", bottom: 0, left: 0, right: 0 }}
      elevation={3}
    >
      <BottomNavigation
        showLabels
        value={navigateTarget}
        onChange={(event, newValue) => {
          console.log(newValue);
          navigate(`/${newValue}`);
          setNavigateTarget(newValue);
        }}
      >
        {navigationURLList.map((value, index) => (
          <BottomNavigationAction
            label={navigationName[index]}
            value={value}
            key={`mui-nav-${index}`}
            icon={navigationMUIIconList[index]}
            style={{
              width: "10vw",
              padding: "0px",
              minWidth: "56px",
            }}
          />
        ))}
      </BottomNavigation>
    </Paper>
  );
};

export default NavigationComponent;
