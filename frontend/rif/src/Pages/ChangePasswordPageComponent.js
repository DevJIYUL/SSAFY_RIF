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
  const [errorForm, setErrorForm] = useState(false);

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
        if (response.data.newToken) {
          dispatch(authActions.updateToken(response.data.newToken));
        }
        dispatch(
          authActions.changeAuth({
            token: response.data.accessToken,
            id: authentication.id,
          })
        );
        if (response.status !== 200) {
          throw new Error("비밀번호 변경 오류");
        }
        navigate("/main");
      } catch (error) {
        setErrorForm(true);
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
            <TextField
              fullWidth
              id="currentPassword"
              label="현재 비밀번호"
              type="password"
              sx={{ mb: 2 }}
              defaultValue={currentPassword}
              onChange={inputCurrentPasswordHandler}
            />
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
            {errorForm && (
              <FormHelperText>
                <ErrorIcon />
                아이디 또는 비밀번호가 올바르지 않습니다.
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
