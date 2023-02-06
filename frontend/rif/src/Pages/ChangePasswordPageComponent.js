import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import changePasswordAPI from "../API/changePasswordAPI";
import { authActions } from "../store/auth";

const ChangePasswordPageComponent = () => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordConfirm, setNewPasswordConfirm] = useState("");

  const navigate = useNavigate();
  const dispatch = useDispatch();

  // const token = useSelector((state) => state.auth.authentication.token);
  const token = 1239986;
  const authentication = useSelector((state) => state.auth.authentication);

  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
    console.log(authentication);
  }, [token, navigate, authentication]);

  const inputCurrentPasswordHandler = (event) => {
    setCurrentPassword(event.target.value);
  };

  const inputNewPasswordHandler = (event) => {
    setNewPassword(event.target.value);
  };

  const inputNewPasswordConfirmHandler = (event) => {
    setNewPasswordConfirm(event.target.value);
  };

  const changePasswordSubmitHandler = (event) => {
    event.preventDefault();
    async function changePasswordRequest(
      currentPassword,
      newPassword,
      newPasswordConfirm,
      token
    ) {
      try {
        const response = await changePasswordAPI(
          currentPassword,
          newPassword,
          newPasswordConfirm,
          token
        );
        dispatch(
          authActions.changeAuth({
            token: response.data.accessToken,
            id: authentication.id,
          })
        );
        if (response.status !== 200) {
          throw new Error("비밀번호 변경 오류");
        }
        console.log(response);
        navigate("/index");
      } catch (error) {
        console.log(error);
      }
    }
    changePasswordRequest(
      currentPassword,
      newPassword,
      newPasswordConfirm,
      token
    );
  };

  return (
    <div>
      <div>ChangePasswordPageComponent</div>
      <form action="" onSubmit={changePasswordSubmitHandler}>
        <input
          type="text"
          name="currentPassword"
          id="currentPassword"
          value={currentPassword}
          style={{ display: "block", margin: "10px" }}
          onChange={inputCurrentPasswordHandler}
        />
        <input
          type="text"
          name="newPassword"
          id="newPassword"
          value={newPassword}
          style={{ display: "block", margin: "10px" }}
          onChange={inputNewPasswordHandler}
        />
        <input
          type="text"
          name="newPasswordConfirm"
          id="newPasswordConfirm"
          value={newPasswordConfirm}
          style={{ display: "block", margin: "10px" }}
          onChange={inputNewPasswordConfirmHandler}
        />
        <button type="submit">제출</button>
      </form>
    </div>
  );
};

export default ChangePasswordPageComponent;
