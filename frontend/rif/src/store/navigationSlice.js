import { createSlice } from "@reduxjs/toolkit";

const navigationSlice = createSlice({
  name: "navigation",
  initialState: {
    navigationBarValue: "",
    pastHistory: "",
  },
  reducers: {
    setNavigationBarValue(state, action) {
      state.navigationBarValue = action.payload;
    },
    setPastHistory(state, action) {
      state.pastHistory = action.payload;
    },
  },
});

export const navigationActions = navigationSlice.actions;

export default navigationSlice;
