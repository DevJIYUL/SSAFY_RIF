import { useSelector, useDispatch } from "react-redux";
import {} from "@reduxjs/toolkit";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { mainPageRequestHandler } from "../store/getUserInfo";

import { Box, Grid, createTheme, Paper, ThemeProvider } from "@mui/material";

let isInitial = true;

const profileTheme = createTheme({
  palette: {
    primary: {
      main: "#9CB59D",
    },
  },
});

const MainPageComponent = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);
  const userInfo = useSelector((state) => state.user.userInfo);
  const userRefBadges = useSelector((state) => state.user.userRefBadges);
  const userRefAchievements = useSelector(
    (state) => state.user.userRefAchievements
  );

  useEffect(() => {
    if (isInitial) {
      if (!token) {
        navigate("/login");
        return;
      }
      isInitial = false;
      dispatch(mainPageRequestHandler(id));
    }
  }, [token, id, navigate, dispatch]);

  //exp, id, name, point, profileImgPath
  return (
    <ThemeProvider theme={profileTheme}>
      {userInfo
        ? Object.entries(userInfo).map(([key, value]) => (
            <p key={key}>
              {key} : {value}
            </p>
          ))
        : null}
      <Paper
        elevation={3}
        sx={{
          display: "flex",
          flexWrap: "wrap",
          m: 1,
          width: "100%",
          minWidth: "100px",
          height: "100%",
          minHeight: "100px",
          backgroundColor: "primary.main",
        }}
      >
        <Grid container>
          <Grid sx={{ width: "50%", padding: "5%" }}>
            <img
              src={userInfo.profileImgPath}
              alt=""
              srcset=""
              style={{ width: "90%" }}
            />
          </Grid>
          <Grid sx={{ width: "50%", padding: "5%" }}>
            <Grid>
              <div>name</div>
              <div>id</div>
            </Grid>
            <Grid>
              <div>exp</div>
              <div>point</div>
            </Grid>
          </Grid>
        </Grid>
      </Paper>
      {userRefBadges
        ? userRefBadges.map((userRefBadge) =>
            Object.entries(userRefBadge).map(([key, value]) => (
              <p key={key + "Badge"}>
                {key}:{value}
              </p>
            ))
          )
        : null}
      {userRefAchievements
        ? userRefAchievements.map((userRefAchievement) =>
            Object.entries(userRefAchievement).map(([key, value]) => (
              <p key={key + "Achievement"}>
                {key}:{value}
              </p>
            ))
          )
        : null}
    </ThemeProvider>
  );
};

export default MainPageComponent;
