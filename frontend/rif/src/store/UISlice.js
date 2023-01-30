import { createSlice } from "@reduxjs/toolkit"

const notification = {
  status: "",
  title: "",
  message: "",
}

const infoUI = createSlice({
  name: "UI",
  initialState: { notification },
  reducers: {
    changeNofication(state, action) {
      state.notification = {
        status: action.payload.status,
        title: action.payload.title,
        message: action.payload.message,
      }
    },
    resetNofication(state) {
      state.notification = {
        status: "",
        title: "",
        message: "",
      }
    },
  },
})

export const UIActions = infoUI.actions

export default infoUI
