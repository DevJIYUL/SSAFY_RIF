import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { mainPageRequestHandler } from "../store/getUserInfo";
import changeProfileInfoAPI from "../API/changeProfileInfoAPI";
import { useNavigate } from "react-router-dom";
import { Grid } from "@mui/material";
import SectionTitleComponent from "../UI/SectionTitleComponent";
import PageChangerComponent from "../UI/PageChangerComponent";

const ChangeProfilePageComponent = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

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
        const response = await changeProfileInfoAPI(profileImgPathId, token);

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
      <Grid container>
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
        </form>
      </Grid>
    </>
  );
};

export default ChangeProfilePageComponent;
