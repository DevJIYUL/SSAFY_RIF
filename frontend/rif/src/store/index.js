import { configureStore } from "@reduxjs/toolkit"
import infoUI from "./UISlice"
import authSlice from "./auth"

const store = configureStore({
  reducer: { ui: infoUI.reducer, auth: authSlice.reducer },
})

export default store
