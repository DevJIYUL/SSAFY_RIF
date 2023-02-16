import { configureStore } from "@reduxjs/toolkit";
import UISlice from "./UISlice";
import authSlice from "./auth";
import userInfoSlice from "./getUserInfo";
import searchResultSlice from "./searchResultSlice";
import navigationSlice from "./navigationSlice";
import lottoSlice from "./lottoSlice";

import { combineReducers } from "redux";
import {
  persistReducer,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storage from "redux-persist/lib/storage";

console.log = function () {};
console.error = function () {};
console.warn = function () {};

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["auth", "user"],
};

const reducers = combineReducers({
  ui: UISlice.reducer,
  auth: authSlice.reducer,
  user: userInfoSlice.reducer,
  search: searchResultSlice.reducer,
  lotto: lottoSlice.reducer,
  navigationLocation: navigationSlice.reducer,
});

const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

export default store;
