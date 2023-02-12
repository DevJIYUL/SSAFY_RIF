import axiosInterface from "./axiosInterface";

async function changePasswordAPI(
  currentPassword,
  newPassword,
  newPasswordConfirm,
  token
) {
  const data = {
    currentPassword,
    newPassword,
    newPasswordConfirm,
  };
  const headers = {
    Authorization: "Bearer " + token,
  };
  const response = await axiosInterface(
    "patch",
    "/api/v/user/password",
    data,
    headers
  );
  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default changePasswordAPI;
