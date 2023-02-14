import { createSlice } from "@reduxjs/toolkit";
import getUserInfoAPI from "../API/getUserInfoAPI";
import getUserRefBadgeAPI from "../API/getUserRefBadgeAPI";
import getUserRefAchievementAPI from "../API/getUserRefAchievementAPI";
import { UIActions } from "./UISlice";
import getBadgesAPI from "../API/getBadgesAPI";
import { authActions } from "./auth";

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
      console.log("유저 대표 뱃지 바뀌었다.");
      state.userRefBadges = [...action.payload];
    },
    setUserRefAchievements(state, action) {
      state.userRefAchievements = [...action.payload];
    },
    setUserPoint(state, action) {
      state.userInfo.point = action.payload;
    },
  },
});

async function getUserRequest(id, dispatch) {
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
}

async function getBadgesRequest(id, dispatch) {
  const badgeResponse = await getUserRefBadgeAPI(id);
  if (badgeResponse.status !== 200) {
    throw new Error("Error is raiesd!");
  }

  const parsedDisplayBadge = badgeResponse.data.onDisplayBadge.map((obj) => {
    const temp = {
      rewardInfo: obj["badgeInfo"],
      onDisplay: obj["onDisplay"],
      achievedAt: obj["achievedAt"],
      hasReward: obj["hasBadge"],
    };
    return temp;
  });

  dispatch(userInfoActions.setUserRefBadges(parsedDisplayBadge));
}

async function getAchievementsRequest(id, dispatch) {
  const achievementResponse = await getUserRefAchievementAPI(id);
  if (achievementResponse.status !== 200) {
    throw new Error("Error is raiesd!");
  }

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

  dispatch(userInfoActions.setUserRefAchievements(parsedDisplayAchievement));
}

async function tokenEffectniessHandler(token, id) {
  const response = await getBadgesAPI(token, id);

  return response;
}

export const mainPageRequestHandler = (id, token) => {
  return async (dispatch) => {
    dispatch(
      UIActions.changeNofication({
        status: "pending",
      })
    );
    const checkTokenResponse = await tokenEffectniessHandler(token, id);

    if (checkTokenResponse.newToken) {
      dispatch(authActions.updateToken(checkTokenResponse.newToken));
    } else if (checkTokenResponse.status === 307) {
      dispatch(authActions.logout());
      return;
    }

    getUserRequest(id, dispatch);

    getBadgesRequest(id, dispatch);

    getAchievementsRequest(id, dispatch);

    dispatch(
      UIActions.changeNofication({
        status: "success",
      })
    );

    dispatch(UIActions.resetNofication());
  };
};

export const userInfoActions = userInfoSlice.actions;

export default userInfoSlice;
