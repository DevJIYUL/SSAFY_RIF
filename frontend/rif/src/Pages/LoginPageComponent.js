import loginAPI from "../API/loginAPI"
import React, { useState } from "react"

const LoginPageComponent = () => {
  const [id, setId] = useState("")
  const [password, setPassword] = useState("")

  const idChangeHandler = (event) => {
    setId(event.target.value)
  }
  const passwordChangeHandler = (event) => {
    setPassword(event.target.value)
  }

  const formSubmitHandler = (event) => {
    event.preventDefault()
    console.log("submit")
    const response = loginAPI(id, password)
    response.then((res) => console.log(res))
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
        <button type="submit">로그인</button>
      </form>
    </div>
  )
}

export default LoginPageComponent
