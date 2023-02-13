import { createSlice } from "@reduxjs/toolkit";
import getUserInfoAPI from "../API/getUserInfoAPI";
import getUserRefBadgeAPI from "../API/getUserRefBadgeAPI";
import getUserRefAchievementAPI from "../API/getUserRefAchievementAPI";
import { UIActions } from "./UISlice";

const userInfoSlice = createSlice({
  name: "userInfo",
  initialState: { userInfo: "", userRefBadges: "", userRefAchievements: "" },
  reducers: {
    setUserInfo(state, action) {
      state.userInfo = {
        exp: action.payload.exp,
        id: action.payload.id,
        name: action.payload.name,
        point: action.payload.point,
        profileImgPath: action.payload.profileImgPath,
      };
    },
    setUserRefBadges(state, action) {
      state.userRefBadges = [...action.payload];
      // console.log(state.userRefBadges, typeof state.userRefBadges);
    },
    setUserRefAchievements(state, action) {
      state.userRefAchievements = [...action.payload];
      // console.log(state.userRefAchievements, typeof state.userRefAchievements);
    },
  },
});

export const mainPageRequestHandler = (id) => {
  return async (dispatch) => {
    dispatch(
      UIActions.changeNofication({
        status: "pending",
      })
    );
    try {
      const response = await getUserInfoAPI(id);

      if (response.status !== 200) {
        throw new Error("Error is raiesd!");
      }

      const parsedUserInfoAPI = {
        id: response.data.id,
        uid: response.data.uid,
        name: response.data.name,
        profileImgPath: response.data.profileImgPath,
        point: response.data.point,
        exp: response.data.exp,
      };

      dispatch(userInfoActions.setUserInfo(parsedUserInfoAPI));

      const badgeResponse = await getUserRefBadgeAPI(id);
      if (badgeResponse.status !== 200) {
        throw new Error("Error is raiesd!");
      }

      // console.log(badgeResponse, "badge");

      // remove id and change the Key
      const parsedDisplayBadge = badgeResponse.data.onDisplayBadge.map(
        (obj) => {
          const temp = {
            rewardInfo: obj["badgeInfo"],
            onDisplay: obj["onDisplay"],
            achievedAt: obj["achievedAt"],
            hasReward: obj["hasBadge"],
          };
          return temp;
        }
      );

      dispatch(userInfoActions.setUserRefBadges(parsedDisplayBadge));

      const achievementResponse = await getUserRefAchievementAPI(id);
      if (achievementResponse.status !== 200) {
        throw new Error("Error is raiesd!");
      }

      // console.log(achievementResponse, "achievementResponse");

      // remove id and change the Key
      const parsedDisplayAchievement =
        achievementResponse.data.onDisplayAchievement.map((obj) => {
          const temp = {
            rewardInfo: obj["achievementInfo"],
            onDisplay: obj["onDisplay"],
            achievedAt: obj["achievedAt"],
            hasReward: obj["hasAchievement"],
          };
          return temp;
        });

      dispatch(
        userInfoActions.setUserRefAchievements(parsedDisplayAchievement)
      );

      dispatch(
        UIActions.changeNofication({
          status: "success",
        })
      );

      dispatch(UIActions.resetNofication());
    } catch (error) {
      console.log(error);
    }
  };
};

export const userInfoActions = userInfoSlice.actions;

export default userInfoSlice;
