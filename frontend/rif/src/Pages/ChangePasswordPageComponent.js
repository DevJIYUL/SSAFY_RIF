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

const loginTheme = createTheme({
  palette: {
    primary: {
      main: "#357a38",
    },
  },
});

const ChangePasswordPageComponent = () => {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordConfirm, setNewPasswordConfirm] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);
  const authentication = useSelector((state) => state.auth.authentication);

  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
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
    if (newPassword !== newPasswordConfirm) {
      setErrorMessage("새로운 비밀번호가 일치하지 않습니다.");
      return;
    }
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
          token,
          id
        );
        console.log(response);
        if (response.data.newToken) {
          dispatch(authActions.updateToken(response.data.newToken));
        }

        if (response.status === 403) {
          console.log(response);
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
            {(!errorMessage ||
              errorMessage === "새로운 비밀번호가 일치하지 않습니다.") && (
              <TextField
                fullWidth
                id="currentPassword"
                label="현재 비밀번호"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={currentPassword}
                onChange={inputCurrentPasswordHandler}
              />
            )}
            {errorMessage === "비밀번호가 올바르지 않습니다." && (
              <TextField
                fullWidth
                error
                id="currentPassword"
                label="현재 비밀번호"
                type="password"
                sx={{ mb: 2 }}
                defaultValue={currentPassword}
                onChange={inputCurrentPasswordHandler}
              />
            )}
            {(!errorMessage ||
              errorMessage === "비밀번호가 올바르지 않습니다.") && (
              <>
                <TextField
                  fullWidth
                  id="newPassword"
                  label="새 비밀번호"
                  type="password"
                  sx={{ mb: 2 }}
                  defaultValue={newPassword}
                  onChange={inputNewPasswordHandler}
                />
                <TextField
                  fullWidth
                  id="newPasswordConfirm"
                  label="새 비밀번호 확인"
                  type="password"
                  sx={{ mb: 2 }}
                  defaultValue={newPasswordConfirm}
                  onChange={inputNewPasswordConfirmHandler}
                />
              </>
            )}
            {errorMessage === "새로운 비밀번호가 일치하지 않습니다." && (
              <>
                <TextField
                  fullWidth
                  error
                  id="newPassword"
                  label="새 비밀번호"
                  type="password"
                  sx={{ mb: 2 }}
                  defaultValue={newPassword}
                  onChange={inputNewPasswordHandler}
                />
                <TextField
                  fullWidth
                  error
                  id="newPasswordConfirm"
                  label="새 비밀번호 확인"
                  type="password"
                  sx={{ mb: 2 }}
                  defaultValue={newPasswordConfirm}
                  onChange={inputNewPasswordConfirmHandler}
                />
              </>
            )}
            {errorMessage && (
              <FormHelperText>
                <ErrorIcon />
                비밀번호가 올바르지 않습니다.
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
