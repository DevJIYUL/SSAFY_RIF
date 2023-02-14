import axiosInterface from "./axiosInterface";

/**
 * This function returns a list of users searched for by name.
 * Response data is that the key is members, value is array.
 * This array consists of objects that is haved properties of id, name, exp, profileImgPath.
 */
async function userSearchAPI(id) {
  const response = await axiosInterface(
    "GET",
    "/api/member/search",
    {},
    {},
    { name: id }
  );

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default userSearchAPI;
