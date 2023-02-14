import axiosInterface from "./axiosInterface";

/**
 * Get infos about a User from the user's primary key(id).
 * Will return the users id, name, point, exp, profileImgPath
 */
async function lottoAPI(accessToken, memberId) {
  const response = await axiosInterface(
    "POST",
    "/api/v/gatcha",
    {},
    {
      Authorization: `Bearer ${accessToken}`,
    },
    {
      memberId: memberId,
    }
  );

  if (response.status === 200) {
    return response;
  }

  console.log(response, "LottopAPI");
  return response.response;
}

export default lottoAPI;
