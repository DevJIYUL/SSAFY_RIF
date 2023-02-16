import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import calLevel from "../API/calLevel";
import SectionTitleComponent from "../UI/SectionTitleComponent";

import { Grid, createTheme, ThemeProvider } from "@mui/material";
import MainProfileComopnent from "../Components/MainProfileComponent";
import getUserInfoAPI from "../API/getUserInfoAPI";
import getUserRefBadgeAPI from "../API/getUserRefBadgeAPI";
import getUserRefAchievementAPI from "../API/getUserRefAchievementAPI";

const profileTheme = createTheme({
  palette: {
    primary: {
      main: "#9CB59D",
    },
  },
});

const MemberProfilePageComponet = () => {
  const navigate = useNavigate();
  const params = useParams();

  const memberID = params.memberID;

  console.log(memberID);

  const [userInfo, setUserInfo] = useState("");
  const [userRefBadges, setUserRefBadges] = useState("");
  const [userRefAchievements, setUserRefAchievements] = useState("");

  useEffect(() => {
    async function requestUserInfoAPI(id) {
      try {
        const response = await getUserInfoAPI(id);
        console.log(response);
        if (response.status !== 200) {
          throw new Error(response);
        }
        return setUserInfo(response.data);
      } catch (error) {
        navigate("/error");
      }
    }

    async function requestUserRefBadgeAPI(id) {
      try {
        const response = await getUserRefBadgeAPI(id);
        console.log(response);
        if (response.status !== 200) {
          throw new Error(response);
        }
        return setUserRefBadges(response.data.onDisplayBadge);
      } catch (error) {
        navigate("/error");
      }
    }

    async function requestUserRefAchievementAPI(id) {
      try {
        const response = await getUserRefAchievementAPI(id);
        console.log(response);
        if (response.status !== 200) {
          throw new Error(response);
        }
        return setUserRefAchievements(response.data.onDisplayAchievement);
      } catch (error) {
        navigate("/error");
      }
    }

    requestUserInfoAPI(memberID);
    requestUserRefBadgeAPI(memberID);
    requestUserRefAchievementAPI(memberID);
  }, [memberID, navigate]);

  const [exp, caledExp] = calLevel(userInfo.exp);
  console.log(exp, caledExp);

  //exp, id, name, point, profileImgPath
  return (
    <ThemeProvider theme={profileTheme}>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        {userInfo && (
          <MainProfileComopnent another={true} userInfo={userInfo} />
        )}
        {userRefBadges && userRefAchievements && (
          <Grid
            container
            direction="column"
            justifyContent="center"
            alignItems="center"
          >
            <SectionTitleComponent
              sectionTitle={`${userInfo.name}님의 대표 뱃지`}
            />
            <Grid
              container
              columns={{ xs: 6, sm: 12 }}
              sx={{ display: "flex", margin: "auto", my: "1rem" }}
              rowSpacing={1}
              width="75%"
            >
              {userRefBadges.length ? (
                userRefBadges.map((value, index) => {
                  return (
                    <Grid
                      item
                      xs={2}
                      sm={4}
                      key={`${userRefBadges}index`}
                      display="flex"
                      justifyContent="center"
                      alignContent="center"
                    >
                      <img
                        src={value.badgeInfo.imgPath}
                        alt={value.badgeInfo.title}
                        style={value.badgeInfo.style}
                        onClick={value.badgeInfo.handleModalOpen}
                        height="75px"
                      />
                    </Grid>
                  );
                })
              ) : (
                <Grid
                  item
                  display="flex"
                  justifyContent="space-around"
                  alignContent="center"
                  sx={{ width: "100%", overflow: "auto" }}
                >
                  {[1, 2, 3].map((imgPath, index) => {
                    return (
                      <img
                        src={`/badge/${imgPath}.png`}
                        alt={`/badge/${imgPath}.png`}
                        style={{ opacity: 0.3, filter: "grayscale(100)" }}
                        height="75px"
                      />
                    );
                  })}
                  <h4 style={{ position: "absolute" }}>
                    아직 설정된{" "}
                    <span style={{ color: "#4E9E00", fontWeight: "700" }}>
                      대표 뱃지가
                    </span>
                    없습니다.
                  </h4>
                </Grid>
              )}
            </Grid>
            <SectionTitleComponent
              sectionTitle={`${userInfo.name}님의 대표 업적`}
            />
            <Grid
              container
              columns={{ xs: 6, sm: 12 }}
              sx={{ display: "flex", margin: "auto", my: "1rem" }}
              rowSpacing={1}
              width="75%"
            >
              {userRefAchievements.length ? (
                userRefAchievements.map((value, index) => {
                  return (
                    <Grid
                      item
                      xs={2}
                      sm={4}
                      key={`${userRefAchievements}index`}
                      display="flex"
                      justifyContent="center"
                      alignContent="center"
                    >
                      <img
                        src={value.achievementInfo.imgPath}
                        alt={value.achievementInfo.title}
                        style={value.achievementInfo.style}
                        onClick={value.achievementInfo.handleModalOpen}
                        height="75px"
                      />
                    </Grid>
                  );
                })
              ) : (
                <Grid
                  item
                  display="flex"
                  justifyContent="space-around"
                  alignContent="center"
                  sx={{ width: "100%", overflow: "auto" }}
                >
                  {[1, 2, 3].map((imgPath, index) => {
                    return (
                      <img
                        src={`/achievement/${imgPath}.png`}
                        alt={`/achievement/${imgPath}.png`}
                        style={{ opacity: 0.3, filter: "grayscale(100)" }}
                        height="75px"
                      />
                    );
                  })}
                  <h4 style={{ position: "absolute" }}>
                    아직 설정된{" "}
                    <span style={{ color: "#4E9E00", fontWeight: "700" }}>
                      대표 업적이
                    </span>
                    없습니다.
                  </h4>
                </Grid>
              )}
            </Grid>
          </Grid>
        )}
      </Grid>
    </ThemeProvider>
  );
};

export default MemberProfilePageComponet;
