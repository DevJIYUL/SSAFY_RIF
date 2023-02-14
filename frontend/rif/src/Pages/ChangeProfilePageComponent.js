import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { mainPageRequestHandler } from "../store/getUserInfo";
import changeProfileInfoAPI from "../API/changeProfileInfoAPI";
import { useNavigate } from "react-router-dom";
import { Grid, Button, Box } from "@mui/material";
import SectionTitleComponent from "../UI/SectionTitleComponent";
import PageChangerComponent from "../UI/PageChangerComponent";
import { authActions } from "../store/auth";

const ChangeProfilePageComponent = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const id = useSelector((state) => state.auth.authentication.id);
  const token = useSelector((state) => state.auth.authentication.token);
  const profileImgpath = useSelector(
    (state) => state.user.userInfo.profileImgPath
  );

  useEffect(() => {
    if (!profileImgpath) {
      dispatch(mainPageRequestHandler());
    }
  }, [profileImgpath, dispatch]);

  const [selectedProfileImg, setselectedProfileImg] = useState(profileImgpath);

  const profileClickHandler = (event) => {
    setselectedProfileImg(event.target.dataset.profileId);
  };

  const changeProfileSubmitHandler = (event) => {
    event.preventDefault();
    async function changeProfileRequest(profileImgPathId, token) {
      try {
        const response = await changeProfileInfoAPI(
          profileImgPathId,
          id,
          token
        );
        if (response.newToken) {
          dispatch(authActions.updateToken(response.newToken));
          return;
        }
        if (response.status === 307) {
          dispatch(authActions.logout());
          navigate("/login");
          return;
        }
        if (response.status !== 200) {
          throw new Error("Error is comming.");
        }
        navigate("/main");
      } catch (error) {
        console.log(error);
      }
    }
    changeProfileRequest(selectedProfileImg, token);
  };

  return (
    <>
      <PageChangerComponent to="/main">메인 화면</PageChangerComponent>
      <Grid container justifyContent="center" stlye={{ width: "90%" }}>
        <SectionTitleComponent sectionTitle="프로필 사진 수정"></SectionTitleComponent>
        <form
          action=""
          onSubmit={changeProfileSubmitHandler}
          style={{
            width: "90%",
          }}
        >
          <Grid>
            {[1, 4].map((firstValue) => (
              <Grid
                container
                justifyContent="space-evenly"
                key={`changeProfileGrid${firstValue}`}
                style={{
                  marginTop: "20px",
                }}
              >
                {[0, 1, 2].map((value, index) => (
                  <img
                    src={`profile/${value + firstValue}.png`}
                    alt="loading"
                    data-profile-id={value + firstValue}
                    key={`profile${index + firstValue}`}
                    style={{
                      minHeight: "70px",
                      height: "25vw",
                      borderRadius: "50%",
                      margin: "3px 0px",
                      border: "3px solid #4E9E00",
                    }}
                    onClick={profileClickHandler}
                  />
                ))}
              </Grid>
            ))}
          </Grid>
          <Box
            sx={{
              width: window.innerWidth * 0.75 + 32,
              backgroundColor: "#EAE7B1",
              borderRadius: 2,
              boxShadow: 2,
              component: "span",
              display: "flex",
              justifyContent: "space-between",
              minHeight: "46.500px",
              marginTop: "20px",
              height: "15vw",
            }}
          >
            <Button
              style={{
                width: "100%",
                height: "100%",
                color: "#5D5E58",
                fontSize: "6vw",
                fontFamily: "NanumSquareB",
              }}
            >
              변경
            </Button>
          </Box>
        </form>
        <Box
          sx={{
            width: window.innerWidth * 0.75 + 32,
            backgroundColor: "#3C6255",
            borderRadius: 2,
            boxShadow: 2,
            component: "span",
            display: "flex",
            justifyContent: "space-between",
            minHeight: "46.500px",
            marginTop: "40px",
            height: "15vw",
          }}
        >
          <Button
            onClick={() => {
              navigate("/change-password");
            }}
            style={{
              width: "100%",
              height: "100%",
              color: "#F0F0F0",
              fontSize: "6vw",
              fontFamily: "NanumSquareB",
            }}
          >
            비밀번호 변경
          </Button>
        </Box>
      </Grid>
    </>
  );
};

export default ChangeProfilePageComponent;
