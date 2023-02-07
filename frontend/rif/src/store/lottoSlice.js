import { createSlice } from "@reduxjs/toolkit";
import lottoAPI from "../API/LottoAPI";

const lottoSlice = createSlice({
  name: "lotto",
  initialState: {
    badgeTitle: "",
    badgeDesc: "",
    badgeTier: "",
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
      state.badgeTitle = action.payload.badgeTitle;
      state.badgeDesc = action.payload.badgeDesc;
      state.badgeTier = action.payload.badgeTier;
    },
    drawLotteryReset(state, action) {
      state.badgeTitle = "";
      state.badgeDesc = "";
      state.badgeTier = "";
    },
  },
});

export default lottoSlice;

export const lotteryOpenHandler = () => {
  return async (dispatch) => {
    // show loading status at first
    dispatch(lottoSlice.actions.drawLotteryPending());
    console.log("pending");

    // get response
    const response = await lottoAPI("accessToken", "0847647");
    console.log("got response");
    console.log(response);

    if (response.status !== 200) {
      dispatch(lottoSlice.actions.drawLotteryFailed());
      console.log("failed");
    } else {
      const payload = {
        badgeTitle: response.data.badge.title,
        badgeDesc: response.data.badge.description,
        badgeTier: response.data.badge.tier,
      };
      dispatch(lottoSlice.actions.drawLotterySuccess(payload));
      console.log("sucess");
    }
  };
};

export const lotteryCloseHandler = () => {
  return async (dispatch) => {
    // change the status to empty
    dispatch(lottoSlice.actions.drawLotteryReset());
  };
};
