import { createSlice } from "@reduxjs/toolkit";
import { userInfoActions } from "./getUserInfo";
import lottoAPI from "../API/LottoAPI";

const lottoSlice = createSlice({
  name: "lotto",
  initialState: {
    badgeTitle: "",
    badgeDesc: "",
    badgeTier: "",
    badgeImgPath: "",
    showResult: false,
  },
  reducers: {
    drawLotteryPending(state, action) {
      state.badgeTitle = "Loading ... ";
      state.badgeDesc = "Loading ... ";
    },
    drawLotteryFailed(state, action) {
      state.badgeTitle = "Connection Failed";
      state.badgeDesc = "Connection Failed";
    },
    drawLotterySuccess(state, action) {
      state.showResult = true;
      state.badgeTitle = action.payload.badgeTitle;
      state.badgeDesc = action.payload.badgeDesc;
      state.badgeTier = action.payload.badgeTier;
      state.badgeImgPath = action.payload.badgeImgPath;
    },
    drawLotteryReset(state, action) {
      state.showResult = false;
      state.badgeTitle = "";
      state.badgeDesc = "";
      state.badgeTier = "";
      state.badgeImgPath = "";
    },
    drawTierReset(state, action) {
      state.badgeTier = "";
    },
  },
});

export default lottoSlice;

export const lotteryOpenHandler = (accessToken, id) => {
  return async (dispatch) => {
    // show loading status at first
    dispatch(lottoSlice.actions.drawLotteryPending());

    // get response
    const response = await lottoAPI(accessToken, id);

    if (response.status !== 200) {
      dispatch(lottoSlice.actions.drawLotteryFailed());
    } else {
      const payload = {
        badgeTitle: response.data.badge.title,
        badgeDesc: response.data.badge.description,
        badgeTier: response.data.badge.tier,
        badgeImgPath: response.data.badge.imgPath,
      };
      dispatch(lottoSlice.actions.drawLotterySuccess(payload));
    }

    const remainingPoint = response.data.remainingPoint;
    dispatch(userInfoActions.setUserPoint(remainingPoint));
  };
};

export const lotteryCloseHandler = () => {
  return async (dispatch) => {
    // change the status to empty
    dispatch(lottoSlice.actions.drawLotteryReset());
  };
};

export const fireworkCloseHandler = () => {
  return async (dispatch) => {
    // change the status to empty
    dispatch(lottoSlice.actions.drawTierReset());
  };
};
