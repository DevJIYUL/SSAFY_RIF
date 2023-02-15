import BtnComponent from "../UI/BtnComponent";
import { useSelector, useDispatch } from "react-redux";
import { authActions } from "../store/auth";
import { useNavigate } from "react-router-dom";
import LogoutIcon from "@mui/icons-material/Logout";

const TopBarComponent = () => {
  const userInfo = useSelector((state) => state.user.userInfo);
  const auth = useSelector((state) => state.auth.authentication);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const logoutBtnHandler = () => {
    dispatch(authActions.logout());
    navigate("/main");
  };

  return (
    <div
      style={{
        // backgroundColor: "#9CB59D",
        margin: "0px 0px 1rem 0px",
        height: "50px",
      }}
    >
      <div
        style={{
          display: "flex",
          direction: "row",
          justifyContent: "space-between",
        }}
      >
        <div
          style={{
            verticalAlign: "middle",
            margin: "0px 1rem",
          }}
        >
          <p style={{ color: "#5D5E58" }}>
            {auth.token ? (
              <span style={{ fontWeight: 700, color: "#3C6255" }}>
                {userInfo.name}님{" "}
              </span>
            ) : (
              <span>RIF에 오신 것을 </span>
            )}
            환영합니다.
          </p>
        </div>
        {auth.token && (
          <BtnComponent
            variant="text"
            color="secondary"
            sx={{ margin: "5px" }}
            onClick={logoutBtnHandler}
          >
            로그아웃 <LogoutIcon />
          </BtnComponent>
        )}
      </div>
    </div>
  );
};

export default TopBarComponent;
