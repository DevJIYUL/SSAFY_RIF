import axiosInterface from "./axiosInterface";

/**
 * Get infos about a User from the user's primary key(id).
 * Will return the users id, name, point, exp, profileImgPath
 */
async function LottoAPI() {
  const response = await axiosInterface("PUT", "/gatcha");

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default LottoAPI;
