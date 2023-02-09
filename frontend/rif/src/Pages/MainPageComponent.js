import { useSelector, useDispatch } from "react-redux";
import {} from "@reduxjs/toolkit";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { mainPageRequestHandler } from "../store/getUserInfo";
import calLevel from "../API/calLevel";
import SectionTitleComponent from "../UI/SectionTitleComponent";

import { Grid, createTheme, ThemeProvider } from "@mui/material";
import MainProfileComopnent from "../Components/MainProfileComponent";
import RewardComponent from "../Components/RewardComponent";

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

  const [exp, caledExp] = calLevel(userInfo.exp);

  //exp, id, name, point, profileImgPath
  return (
    <ThemeProvider theme={profileTheme}>
      <MainProfileComopnent />
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        <SectionTitleComponent
          sectionTitle="내 대표 뱃지"
          to="/badge"
          sectionDetail="뱃지 관리"
        />
        {userRefBadges && (
          <RewardComponent type="badge" rewards={userRefBadges} isRef={true} />
          // userRefBadges [badgeInfo, onDisplay, acheievedAt]
        )}
        <SectionTitleComponent
          sectionTitle="내 대표 업적"
          to="/achievement"
          sectionDetail="업적 관리"
        />
        {userRefAchievements && (
          <RewardComponent
            type="achievement"
            rewards={userRefAchievements}
            isRef={true}
          />
        )}
      </Grid>
    </ThemeProvider>
  );
};

export default MainPageComponent;
