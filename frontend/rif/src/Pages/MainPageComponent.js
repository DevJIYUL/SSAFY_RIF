import { useSelector, useDispatch } from "react-redux"
import {} from "@reduxjs/toolkit"
import React, { useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { mainPageRequestHandler } from "../store/getUserInfo"

// Temporary import
import { Link } from "react-router-dom"
import BtnComponent from "../UI/BtnComponent"

let isInitial = true

const MainPageComponent = () => {
  const navigate = useNavigate()
  const dispatch = useDispatch()

  const token = useSelector((state) => state.auth.authentication.token)
  const id = useSelector((state) => state.auth.authentication.id)
  const userInfo = useSelector((state) => state.user.userInfo)
  const userRefBadges = useSelector((state) => state.user.userRefBadges)
  const userRefAchievements = useSelector(
    (state) => state.user.userRefAchievements
  )

  useEffect(() => {
    if (isInitial) {
      if (!token) {
        navigate("/login")
        return
      }
      isInitial = false
      dispatch(mainPageRequestHandler(id))
    }
  }, [token, id, navigate, dispatch])

  return (
    <div>
      <Link to="/lot" style={{ textDecoration: "none" }}>
        <BtnComponent> 로또 컴포넌트 </BtnComponent>
      </Link>
      {userInfo
        ? Object.entries(userInfo).map(([key, value]) => (
            <p key={key}>
              {key} : {value}
            </p>
          ))
        : null}
      {userRefBadges
        ? userRefBadges.map((userRefBadge) =>
            Object.entries(userRefBadge).map(([key, value]) => (
              <p key={key + "Badge"}>
                {key}:{value}
              </p>
            ))
          )
        : null}
      {userRefAchievements
        ? userRefAchievements.map((userRefAchievement) =>
            Object.entries(userRefAchievement).map(([key, value]) => (
              <p key={key + "Achievement"}>
                {key}:{value}
              </p>
            ))
          )
        : null}
    </div>
  )
}

export default MainPageComponent
