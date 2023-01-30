import { createSlice } from "@reduxjs/toolkit"

import { UIActions } from "./UISlice"
import loginAPI from "../API/loginAPI"

const authSlice = createSlice({
  name: "auth",
  initialState: { authentication: { token: "", id: "" } },
  reducers: {
    changeAuth(state, action) {
      state.authentication = {
        token: action.payload.token,
        id: action.payload.id,
      }
    },
  },
})

export const loginHandler = (data) => {
  return async (dispatch) => {
    dispatch(
      UIActions.changeNofication({
        status: "pending",
        title: "Sending",
        message: "Sending Login Request!",
      })
    )

    try {
      const response = await loginAPI(data.id, data.password)

      if (response.status !== 200) {
        throw new Error(response.data.message)
      }

      console.log(response)

      const userData = {
        token: response.data.accessToken,
        id: response.data.id,
      }

      dispatch(authActions.changeAuth(userData))

      dispatch(
        UIActions.changeNofication({
          status: "success",
          title: "Success",
          message: "Login request successfully!",
        })
      )
    } catch (error) {
      dispatch(
        UIActions.changeNofication({
          status: "error",
          title: "Login Fail",
          message: "Login is Fail..",
        })
      )
      console.log(error)
    }
  }
}

export const authActions = authSlice.actions

export default authSlice
