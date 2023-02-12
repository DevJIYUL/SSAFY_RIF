import axiosInterface from "./axiosInterface";

async function changeProfileInfoAPI(profileImgPathId, nickname, token) {
  const data = {
    profileImgPathId,
    nickname,
  };
  const headers = {
    Authorization: "Bearer " + token,
  };
  const response = await axiosInterface(
    "patch",
    "/api/v/user/profile",
    data,
    headers
  );
  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default changeProfileInfoAPI;
