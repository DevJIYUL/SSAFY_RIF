import axiosInterface from "./axiosInterface"

/**
 * It's important that you have to send request with token, it return top 10 user infos + client user info.
 * The return data consists of an object with two properties, members and me.
 * The members's value has array type and ten length.
 * Each element is an object with two keys that returns a rank and a member object whose string is the value.
 */
async function getRankingInfoWithMeAPI(token) {
  const response = await axiosInterface(
    "GET",
    "api/user/ranking",
    {},
    { "access-token": `${"bearer " + token}` }
  )

  if (response.status === 200) {
    return response
  }

  return response.response
}

export default getRankingInfoWithMeAPI
