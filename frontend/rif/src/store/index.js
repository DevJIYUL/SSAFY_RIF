import { configureStore } from "@reduxjs/toolkit"
import UISlice from "./UISlice"
import authSlice from "./auth"
import userInfoSlice from "./getUserInfo"
import searchResultSlice from "./searchResultSlice"
import lottoSlice from "./lottoSlice"

const store = configureStore({
  reducer: {
    ui: UISlice.reducer,
    auth: authSlice.reducer,
    user: userInfoSlice.reducer,
    search: searchResultSlice.reducer,
    lotto: lottoSlice.reducer,
  },
})

export default store
