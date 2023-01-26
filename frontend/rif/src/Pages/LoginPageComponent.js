import loginAPI from "../API/loginAPI"
import React, { useState } from "react"
import { useNavigate } from "react-router-dom"

const LoginPageComponent = () => {
  const navigate = useNavigate()
  const [id, setId] = useState("")
  const [password, setPassword] = useState("")
  const [loginInProgress, setLoginInProgress] = useState(false)

  const idChangeHandler = (event) => {
    setId(event.target.value)
  }
  const passwordChangeHandler = (event) => {
    setPassword(event.target.value)
  }

  async function formSubmitHandler(event) {
    setLoginInProgress(true)
    event.preventDefault()
    const response = await loginAPI(id, password)

    const resData = response.data
    const resStatus = response.status

    setLoginInProgress(false)
    if (resStatus === 200) {
      console.log(resData)
      navigate("/")
    } else if (resStatus === 401) {
      console.log("아이디 또는 비밀번호 오류!")
      setPassword("")
    } else {
      console.log("알 수 없는 에러")
    }
  }

  return (
    <div>
      <div>Login</div>
      <form action="" method="post" onSubmit={formSubmitHandler}>
        <input
          type="text"
          name="id"
          id="id"
          value={id}
          onChange={idChangeHandler}
          style={{ display: "block" }}
        />
        <input
          type="password"
          name="password"
          id="password"
          value={password}
          onChange={passwordChangeHandler}
          style={{ display: "block" }}
        />
        <button type="submit">
          {loginInProgress ? "로그인 중.." : "로그인"}
        </button>
      </form>
    </div>
  )
}

export default LoginPageComponent
