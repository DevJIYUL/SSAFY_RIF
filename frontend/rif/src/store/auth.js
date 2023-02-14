import { createSlice } from "@reduxjs/toolkit";

import { UIActions } from "./UISlice";
import loginAPI from "../API/loginAPI";

const authSlice = createSlice({
  name: "auth",
  initialState: { authentication: { token: "", refreshToken: "", id: "" } },
  reducers: {
    changeAuth(state, action) {
      state.authentication = {
        token: action.payload.token,
        refreshToken: action.payload.refreshToken,
        id: action.payload.id,
      };
    },
    logout(state) {
      state.authentication = {
        token: "",
        refreshToken: "",
        id: "",
      };
    },
    updateToken(state, action) {
      state.authentication.token = action.payload;
    },
  },
});

export const loginHandler = (data) => {
  return async (dispatch) => {
    try {
      const response = await loginAPI(data.id, data.password);

      if (response.status !== 200) {
        console.log(response);
        throw new Error(response.data.message);
      }

      const userData = {
        token: response.data.accessToken,
        refreshToken: response.data.refreshToken,
        id: data.id,
      };

      dispatch(authActions.changeAuth(userData));

      dispatch(
        UIActions.changeNofication({
          status: "success",
          title: "Success",
          message: "Login request successfully!",
        })
      );

      dispatch(UIActions.resetNofication());
    } catch (error) {
      dispatch(
        UIActions.changeNofication({
          status: "error",
          title: "Login Fail",
          message: "Login is Fail..",
        })
      );
    }
  };
};

export const authActions = authSlice.actions;

export default authSlice;
