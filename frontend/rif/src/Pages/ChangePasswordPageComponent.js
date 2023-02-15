import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import changePasswordAPI from "../API/changePasswordAPI";
import { authActions } from "../store/auth";
import {
  Box,
  TextField,
  Button,
  Grid,
  createTheme,
  ThemeProvider,
  FormHelperText,
} from "@mui/material";
import ErrorIcon from "@mui/icons-material/Error";
import { mainPageRequestHandler } from "../store/getUserInfo";

const loginTheme = createTheme({
  palette: {
    primary: {
      main: "#357a38",
    },
  },
});

const ChangePasswordPageComponent = () => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [errorCurrentPassword, setErrorCurrentPassword] = useState(false);
  const [newPassword, setNewPassword] = useState("");
  const [errorNewPassword, setErrorNewPassword] = useState(false);
  const [newPasswordConfirm, setNewPasswordConfirm] = useState("");
  const [errorNewPasswordConfirm, setErrorNewPasswordConfirm] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const [focusNewPassword, setFocusNewPassword] = useState(false);
  const [focusNewPasswordConfirm, setFocusNewPasswordConfirm] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);

  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
    dispatch(mainPageRequestHandler(id, token));
  }, [dispatch, navigate, id, token]);

  const inputCurrentPasswordHandler = (event) => {
    setCurrentPassword(event.target.value);
  };

  const inputNewPasswordHandler = (event) => {
    setNewPassword(event.target.value);
  };

  const inputNewPasswordConfirmHandler = (event) => {
    setNewPasswordConfirm(event.target.value);
  };

  const newPasswordFocusHandler = () => {
    setFocusNewPassword(true);
  };

  const newPasswordComfirmFocusHandler = () => {
    setFocusNewPasswordConfirm(true);
  };

  const newPasswordBlurHandler = () => {
    setFocusNewPassword(false);
  };
  const newPasswordComfirmBlurHandler = () => {
    setFocusNewPasswordConfirm(false);
  };

  const enterDownCurrentPasswordHandler = (event) => {
    if (event.code === "Enter") {
      if (!newPassword || !newPasswordConfirm) {
        event.preventDefault();
        document.querySelector("#newPassword").focus();
      }
    }
  };

  const enterDownNewPasswordHandler = (event) => {
    if (event.code === "Enter") {
      if (!newPasswordConfirm) {
        event.preventDefault();
        document.querySelector("#newPasswordConfirm").focus();
      }
    }
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
        if (!currentPassword) {
          setErrorCurrentPassword(true);
          setErrorMessage("현재 비밀번호를 입력해주세요.");
          return;
        }
        setErrorCurrentPassword(false);
        if (!newPassword) {
          setErrorNewPassword(true);
          setErrorMessage("변경하실 비밀번호를 입력해주세요.");
          return;
        } else if (newPassword.length < 4) {
          setErrorNewPassword(true);
          setErrorMessage("비밀번호가 너무 짧습니다.");
          return;
        } else if (newPassword.length > 12) {
          setErrorNewPassword(true);
          setErrorMessage("비밀번호가 너무 깁니다.");
          return;
        } else {
          setErrorNewPassword(false);
        }
        if (!newPasswordConfirm) {
          setErrorNewPasswordConfirm(true);
          setErrorMessage("비밀번호 확인란을 입력해주세요.");
          return;
        }
        setErrorNewPasswordConfirm(false);
        if (newPassword !== newPasswordConfirm) {
          setErrorNewPassword(true);
          setErrorNewPasswordConfirm(true);
          setErrorMessage("새로운 비밀번호가 일치하지 않습니다.");
          return;
        }
        const response = await changePasswordAPI(
          currentPassword,
          newPassword,
          newPasswordConfirm,
          token,
          id
        );
        console.log(response);
        if (response.newToken) {
          dispatch(authActions.updateToken(response.data.newToken));
        }

        if (response.status === 403) {
          setErrorCurrentPassword(true);
          setErrorMessage("비밀번호가 올바르지 않습니다.");
        } else if (response.status === 307) {
          console.log(response);
          dispatch(authActions.logout());
          navigate("/login");
        } else {
          navigate("/main");
        }
      } catch (error) {
        navigate("/error");
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
    <ThemeProvider theme={loginTheme}>
      <Grid
        container
        className="grid-container"
        direction="column"
        alignItems="center"
        justifyContent="center"
        sx={{ my: "8rem" }}
      >
        <Grid item className="grid-header">
          <img
            src="/rif-logo-96.png"
            alt="rif logo 96"
            className="rif-logo-96"
          />
        </Grid>
        <Grid item className="grid-body" sx={{ mx: 2 }}>
          <Box
            component="form"
            noValidate
            autoComplete="off"
            onSubmit={changePasswordSubmitHandler}
          >
            {!errorCurrentPassword && (
              <TextField
                fullWidth
                id="currentPassword"
                label="현재 비밀번호"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={currentPassword}
                onChange={inputCurrentPasswordHandler}
                onKeyDown={enterDownCurrentPasswordHandler}
              />
            )}
            {errorCurrentPassword && (
              <TextField
                fullWidth
                error
                id="currentPassword"
                label="현재 비밀번호"
                type="password"
                sx={{ mb: focusNewPassword || focusNewPasswordConfirm ? 0 : 2 }}
                defaultValue={currentPassword}
                onChange={inputCurrentPasswordHandler}
                onKeyDown={enterDownCurrentPasswordHandler}
              />
            )}
            {(focusNewPassword || focusNewPasswordConfirm) && (
              <FormHelperText style={{ marginBottom: "10px" }}>
                비밀번호는 4~12자리의 숫자 또는 영어입니다.
              </FormHelperText>
            )}
            {!errorNewPassword && (
              <TextField
                fullWidth
                id="newPassword"
                label="새 비밀번호"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={newPassword}
                onChange={inputNewPasswordHandler}
                onFocus={newPasswordFocusHandler}
                onBlur={newPasswordBlurHandler}
                onKeyDown={enterDownNewPasswordHandler}
              />
            )}
            {errorNewPassword && (
              <TextField
                fullWidth
                error
                id="newPassword"
                label="새 비밀번호"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={newPassword}
                onChange={inputNewPasswordHandler}
                onFocus={newPasswordFocusHandler}
                onBlur={newPasswordBlurHandler}
                onKeyDown={enterDownNewPasswordHandler}
              />
            )}
            {!errorNewPasswordConfirm && (
              <TextField
                fullWidth
                id="newPasswordConfirm"
                label="새 비밀번호 확인"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={newPasswordConfirm}
                onChange={inputNewPasswordConfirmHandler}
                onFocus={newPasswordComfirmFocusHandler}
                onBlur={newPasswordComfirmBlurHandler}
              />
            )}
            {errorNewPasswordConfirm && (
              <TextField
                fullWidth
                error
                id="newPasswordConfirm"
                label="새 비밀번호 확인"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={newPasswordConfirm}
                onChange={inputNewPasswordConfirmHandler}
                onFocus={newPasswordComfirmFocusHandler}
                onBlur={newPasswordComfirmBlurHandler}
              />
            )}
            {errorMessage && (
              <FormHelperText>
                <ErrorIcon />
                {errorMessage}
              </FormHelperText>
            )}
            <Button
              fullWidth
              variant="contained"
              type="submit"
              sx={{ height: "56px" }}
            >
              변경하기
            </Button>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default ChangePasswordPageComponent;
