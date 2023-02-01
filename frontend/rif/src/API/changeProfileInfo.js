import axiosInterface from "./axiosInterface";

async function changeProfileInfo(profileImgPathId, nickname, token) {
  const data = {
    profileImgPathId,
    nickname,
  };
  const headers = {
    Authorization: "Bearer " + token,
  };
  const response = await axiosInterface(
    "patch",
    "/api/user/profile",
    data,
    headers
  );
  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default changeProfileInfo;
