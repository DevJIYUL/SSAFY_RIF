import { createSlice } from "@reduxjs/toolkit";

const notification = {
  status: "",
  title: "",
  message: "",
};

const UISlice = createSlice({
  name: "UI",
  initialState: { notification },
  reducers: {
    changeNofication(state, action) {
      state.notification = {
        status: action.payload.status,
        title: action.payload.title,
        message: action.payload.message,
      };
    },
    resetNofication(state) {
      state.notification = {
        status: "",
        title: "",
        message: "",
      };
    },
  },
});

export const UIActions = UISlice.actions;

export default UISlice;
