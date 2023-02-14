import axiosInterface from "./axiosInterface";

async function changeProfileInfoAPI(profileImgPath, id, token) {
  const data = {
    id,
    profileImgPath,
  };
  const headers = {
    Authorization: "Bearer " + token,
  };
  const response = await axiosInterface(
    "patch",
    "/api/v/member/profile",
    data,
    headers
  );
  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default changeProfileInfoAPI;
