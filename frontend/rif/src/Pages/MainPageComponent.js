import { useSelector, useDispatch } from "react-redux";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { mainPageRequestHandler } from "../store/getUserInfo";
import calLevel from "../API/calLevel";
import SectionTitleComponent from "../UI/SectionTitleComponent";

import { Grid, createTheme, ThemeProvider, Button } from "@mui/material";
import MainProfileComopnent from "../Components/MainProfileComponent";
import RewardComponent from "../Components/RewardComponent";
import { authActions } from "../store/auth";

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

  const [logoutFlag, setLogoutFlag] = useState("");
  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);
  const userInfo = useSelector((state) => state.user.userInfo);
  const userRefBadges = useSelector((state) => state.user.userRefBadges);
  const userRefAchievements = useSelector(
    (state) => state.user.userRefAchievements
  );

  useEffect(() => {
    if (logoutFlag) {
      navigate("/home");
      return;
    }
    if (!token) {
      navigate("/login");
      return;
    }

    dispatch(mainPageRequestHandler(id, token));
  }, [token, id, logoutFlag, navigate, dispatch]);

  const [exp, caledExp] = calLevel(userInfo.exp);
  console.log(exp, caledExp);

  const logoutBtnHandler = () => {
    dispatch(authActions.logout());
    setLogoutFlag("logout");
  };
  const logBtnHandler = () => {
    navigate("/log");
  };

  //exp, id, name, point, profileImgPath
  return (
    <ThemeProvider theme={profileTheme}>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
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
            <RewardComponent type="badge" isRef={true} />
            // userRefBadges [badgeInfo, onDisplay, acheievedAt]
          )}
          <SectionTitleComponent
            sectionTitle="내 대표 업적"
            to="/achievement"
            sectionDetail="업적 관리"
          />
          {userRefAchievements && (
            <RewardComponent type="achievement" isRef={true} />
          )}
        </Grid>
        <Grid
          container
          justifyContent="center"
          alignItems="center"
          style={{ width: window.innerWidth * 0.75 + 32 }}
        >
          <Button
            variant="contained"
            sx={{
              margin: "auto",
              color: "#FFFFFF",
              width: "40%",
              paddingX: "0px",
            }}
            onClick={logoutBtnHandler}
          >
            로그아웃
          </Button>
          <Button
            variant="contained"
            sx={{
              margin: "auto",
              color: "#FFFFFF",
              width: "40%",
              paddingX: "0px",
            }}
            onClick={logBtnHandler}
          >
            로그 보러 가기
          </Button>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default MainPageComponent;
