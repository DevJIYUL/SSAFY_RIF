import { useState } from "react"
import { useDispatch, useSelector } from "react-redux"

import { userSearchRequestHandler } from "../store/searchResultSlice"

import UserSearchResultBoxComponent from "../Components/UserSearchResultBoxComponent"

const UserSearchComponent = () => {
  const dispatch = useDispatch()
  const [name, setName] = useState("")
  const recentSearchWord = useSelector((state) => state.search.recentSearchWord)
  const recentSearchResults = useSelector(
    (state) => state.search.recentSearchResults
  )
  const inputChangeHandler = (event) => {
    setName(event.target.value)
    console.log(event.target.value)
  }

  const formSubmitHandler = (event) => {
    event.preventDefault()
    if (name === recentSearchWord) {
      console.log("same")
      return
    }
    dispatch(userSearchRequestHandler(name))
  }

  return (
    <div>
      <form action="" onSubmit={formSubmitHandler}>
        <input
          type="text"
          name="name"
          id="name"
          value={name}
          onChange={inputChangeHandler}
        ></input>
        <button type="submit">제출</button>
      </form>
      {recentSearchResults && (
        <UserSearchResultBoxComponent
          recentSearchResults={recentSearchResults}
          key="UserSearchResultBoxComponent"
        />
      )}
    </div>
  )
}

export default UserSearchComponent
