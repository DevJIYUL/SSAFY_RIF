import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { mainPageRequestHandler } from "../store/getUserInfo";
import changeProfileInfo from "../API/changeProfileInfo";
import { useNavigate } from "react-router-dom";

const ChangeProfilePageComponent = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const token = useSelector((state) => state.auth.authentication.token);
  const profileImgpath = useSelector(
    (state) => state.user.userInfo.profileImgPath
  );
  const nickname = useSelector((state) => state.user.userInfo.nickname);

  useEffect(() => {
    if (!profileImgpath) {
      dispatch(mainPageRequestHandler());
    }
  }, [profileImgpath, dispatch]);

  const [selectedProfileImg, setselectedProfileImg] = useState(profileImgpath);
  const [newNickname, setNewNickname] = useState(nickname);

  const profileClickHandler = (event) => {
    setselectedProfileImg(event.target.dataset.profileId);
  };

  const nicknameInputChangeHandler = (event) => {
    setNewNickname(event.target.value);
  };

  const changeProfileSubmitHandler = (event) => {
    event.preventDefault();
    async function changeProfileRequest(profileImgPathId, nickname, token) {
      try {
        const response = await changeProfileInfo(
          profileImgPathId,
          nickname,
          token
        );

        if (response.status !== 200) {
          throw new Error("Error is comming.");
        }
        navigate("/main");
      } catch (error) {
        console.log(error);
      }
    }
    changeProfileRequest(selectedProfileImg, newNickname, token);
  };

  return (
    <div>
      <p>ChangeProfileComponent</p>
      <form action="" onSubmit={changeProfileSubmitHandler}>
        {[1, 2, 3, 4, 5, 6].map((value, index) => (
          <img
            src={`profile/${value}.png`}
            alt="loading"
            data-profile-id={value}
            key={`profile${index}`}
            onClick={profileClickHandler}
          ></img>
        ))}
        <input
          type="text"
          name="nickname"
          id="nickname"
          value={newNickname}
          onChange={nicknameInputChangeHandler}
        />
        <button type="submit">완료!</button>
      </form>
    </div>
  );
};

export default ChangeProfilePageComponent;
