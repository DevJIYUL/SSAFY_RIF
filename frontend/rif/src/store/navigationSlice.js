import { createSlice } from "@reduxjs/toolkit";

const navigationSlice = createSlice({
  name: "navigation",
  initialState: {
    navigationBarValue: "",
  },
  reducers: {
    setNavigationBarValue(state, action) {
      state.navigationBarValue = action.payload;
    },
  },
});

export const navigationActions = navigationSlice.actions;

export default navigationSlice;
