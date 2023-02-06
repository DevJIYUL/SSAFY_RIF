import { createSlice } from "@reduxjs/toolkit"
import getUserInfoAPI from "../API/getUserInfoAPI"
import getUserRefBadgeAPI from "../API/getUserRefBadgeAPI"
import getUserRefAchievementAPI from "../API/getUserRefAchievementAPI"
import { UIActions } from "./UISlice"

const userInfoSlice = createSlice({
  name: "userInfo",
  initialState: { userInfo: "", userRefBadges: "", userRefAchievements: "" },
  reducers: {
    setUserInfo(state, action) {
      state.userInfo = {
        exp: action.payload.exp,
        id: action.payload.id,
        name: action.payload.name,
        point: action.payload.point,
        profileImgPath: action.payload.profileImgPath,
      }
    },
    setUserRefBadges(state, action) {
      state.userRefBadges = [...action.payload]
      console.log(state.userRefBadges, typeof state.userRefBadges)
    },
    setUserRefAchievements(state, action) {
      state.userRefAchievements = [...action.payload]
      console.log(state.userRefAchievements, typeof state.userRefAchievements)
    },
  },
})

export const mainPageRequestHandler = (id) => {
  return async (dispatch) => {
    dispatch(
      UIActions.changeNofication({
        status: "pending",
      })
    )
    try {
      const response = await getUserInfoAPI(id)
      if (response.status !== 200) {
        throw new Error("Error is raiesd!")
      }
      dispatch(userInfoActions.setUserInfo(response.data))

      const badgeResponse = await getUserRefBadgeAPI(id)
      if (badgeResponse.status !== 200) {
        throw new Error("Error is raiesd!")
      }
      console.log(badgeResponse.data, typeof badgeResponse)
      dispatch(
        userInfoActions.setUserRefBadges(badgeResponse.data.onDisplayBadge)
      )

      const achievementResponse = await getUserRefAchievementAPI(id)
      if (achievementResponse.status !== 200) {
        throw new Error("Error is raiesd!")
      }
      console.log(achievementResponse.data, typeof achievementResponse)
      dispatch(
        userInfoActions.setUserRefAchievements(
          achievementResponse.data.onDisplayAchievement
        )
      )

      dispatch(
        UIActions.changeNofication({
          status: "success",
        })
      )

      dispatch(UIActions.resetNofication())
    } catch (error) {
      console.log(error)
    }
  }
}

export const userInfoActions = userInfoSlice.actions

export default userInfoSlice
