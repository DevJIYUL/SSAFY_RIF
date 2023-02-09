import axiosInterface from "./axiosInterface";

/**
 * Get infos about a User from the user's primary key(id).
 * Will return the users id, name, point, exp, profileImgPath
 */
async function lottoAPI(accessToken, memberId) {
  const response = await axiosInterface(
    "POST",
    "api/gatcha",
    {},
    {
      Authorization: `Baerer ${accessToken}`,
    },
    {
      memberId: memberId,
    }
  );

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default lottoAPI;
