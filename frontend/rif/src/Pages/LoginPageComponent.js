import React, { useState, useEffect } from "react";
import { loginHandler } from "../store/auth";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const LoginPageComponent = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [userInputId, setUserInputId] = useState("");
  const [userInputPassword, setUserInputPassword] = useState("");
  const status = useSelector((state) => state.ui.notification.status);
  const token = useSelector((state) => state.auth.authentication.token);

  const idChangeHandler = (event) => {
    setUserInputId(event.target.value);
  };
  const passwordChangeHandler = (event) => {
    setUserInputPassword(event.target.value);
  };

  function formSubmitHandler(event) {
    event.preventDefault();
    dispatch(loginHandler({ userInputId, userInputPassword }));
  }

  useEffect(() => {
    if (token) {
      navigate("/index");
    }
  }, [token, navigate]);

  let btnMessage;

  if (!status) {
    btnMessage = "로그인";
  } else if (status === "pending") {
    btnMessage = "로그인 중 ..";
  } else if (status === "success") {
    btnMessage = "로그인 성공!";
  } else {
    btnMessage = "로그인 실패";
  }

  return (
    <div>
      <div>Login</div>
      <form action="" method="post" onSubmit={formSubmitHandler}>
        <input
          type="text"
          name="id"
          id="id"
          value={userInputId}
          onChange={idChangeHandler}
          style={{ display: "block" }}
        />
        <input
          type="password"
          name="password"
          id="password"
          value={userInputPassword}
          onChange={passwordChangeHandler}
          style={{ display: "block" }}
        />
        <button type="submit">{btnMessage}</button>
      </form>
    </div>
  );
};

export default LoginPageComponent;
