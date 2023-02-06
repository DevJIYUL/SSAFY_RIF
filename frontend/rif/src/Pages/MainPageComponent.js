import { useSelector, useDispatch } from "react-redux";
import {} from "@reduxjs/toolkit";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { mainPageRequestHandler } from "../store/getUserInfo";
import calLevel from "../API/calLevel";
import SectionTitleComponent from "../UI/SectionTitleComponent";
import RewardComponent from "../Components/RewardComponent";

import { Grid, createTheme, Paper, ThemeProvider } from "@mui/material";

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

  const [rewards, setRewards] = useState(userRefBadges);

  const [exp, caledExp] = calLevel(userInfo.exp);

  //exp, id, name, point, profileImgPath
  return (
    <ThemeProvider theme={profileTheme}>
      <Paper
        elevation={3}
        sx={{
          border: "3px solid #7DBC2F",
          width: "80%",
          minWidth: "320px",
          height: "auto",
          minHeight: "167px",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          margin: "0% auto",
          borderRadius: "15px",
          color: "#5D5E58",
        }}
      >
        <Grid container>
          <Grid
            sx={{
              width: "45%",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <img
              src={userInfo.profileImgPath}
              alt=""
              srcSet=""
              style={{
                border: "3px solid #7DBC2F",
                width: "101px",
                borderRadius: "50%",
                // margin: "33px 0px 33px 31px",
                padding: "",
              }}
            />
          </Grid>
          <Grid
            sx={{
              width: "55%",
              display: "flex",
              justifyContent: "flex-start",
              alignItems: "center",
            }}
          >
            <Grid
              sx={{
                fontFamily: "NanumSquareR",
                fontSize: "20px",
                color: "#5D5E58",
                // height: "106px",
              }}
            >
              <div
                style={{
                  fontFamily: "NanumSquareB",
                  fontSize: "30px",
                }}
              >
                {userInfo.name}
              </div>
              <div style={{ marginTop: "3px" }}>{userInfo.id}</div>
              <div style={{ marginTop: "11px" }}>포인트 : {userInfo.point}</div>
              {/* <div>레벨 : {userInfo.point}</div> */}
            </Grid>
          </Grid>
        </Grid>
      </Paper>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
        style={{ marginTop: "49px" }}
      >
        <SectionTitleComponent sectionTitle="내 대표 뱃지" />
        {userRefBadges && (
          <RewardComponent type="badge" rewards={userRefBadges} />
        )}
      </Grid>
    </ThemeProvider>
  );
};

export default MainPageComponent;
