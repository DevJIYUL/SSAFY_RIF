import axiosInterface from "./axiosInterface";

async function changePasswordAPI(
  currentPassword,
  newPassword,
  newPasswordConfirm,
  token,
  id
) {
  const params = { memberId: id };
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
    "/api/v/member/password",
    data,
    headers,
    params
  );
  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default changePasswordAPI;
