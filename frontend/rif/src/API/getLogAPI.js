import axiosInterface from "./axiosInterface"

/**
 * Get infos about badges
 */
async function getLogAPI(accessToken, page, sortOption) {
  const response = await axiosInterface(
    "GET",
    "api/user/log",
    {
      page: page,
      sort: sortOption,
    },
    {
      Authorization: `Baerer ${accessToken}`,
    }
  )

  if (response.status === 200) {
    return response
  }

  return response.response
}

export default getLogAPI
