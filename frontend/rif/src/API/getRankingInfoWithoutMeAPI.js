import axiosInterface from "./axiosInterface";

/**
 * It's important that you have to do not send request with token, it return top 10 user infos without client user info.
 * The return data consists of an object with one properties, it's members.
 * The members's value has array type and ten length.
 * Each element is an object with two keys that returns a rank and a member object whose string is the value.
 */
async function getRankingInfoWithoutMeAPI() {
  const response = await axiosInterface("GET", "/api/ranking");

  if (response.status === 200) {
    return response;
  }

  return response.response;
}

export default getRankingInfoWithoutMeAPI;
