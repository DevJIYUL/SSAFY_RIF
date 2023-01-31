import { createSlice } from "@reduxjs/toolkit"
import { UIActions } from "./UISlice"
import userSearchAPI from "../API/userSearchAPI"

const searchResultSlice = createSlice({
  name: "searchResult",
  initialState: { recentSearchWord: "", recentSearchResults: null },
  reducers: {
    setRecentSearchWord(state, action) {
      state.recentSearchWord = action.payload
      console.log(state.recentSearchWord)
    },
    setRecentSearchResults(state, action) {
      state.recentSearchResults = action.payload
      console.log(state.recentSearchResults)
    },
  },
})

export const userSearchRequestHandler = (name) => {
  return async (dispatch) => {
    dispatch(
      UIActions.changeNofication({
        status: "pending",
      })
    )
    try {
      const response = await userSearchAPI(name)
      console.log(response)
      if (response.status !== 200) {
        throw new Error("response error!")
      }

      dispatch(searchResultActions.setRecentSearchWord(name))
      dispatch(
        searchResultActions.setRecentSearchResults(response.data.members)
      )
    } catch (error) {
      console.log(error)
    }
  }
}

export const searchResultActions = searchResultSlice.actions

export default searchResultSlice
