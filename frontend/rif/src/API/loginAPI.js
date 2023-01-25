import axiosInterface from "./axiosInterface"

/** response status code in RIF is 200 or 401.
 * return is Destructuring assignment. First element is response data object, second element status code integer.
 */
const loginAPI = (id, password) => {
  const response = axiosInterface("get", "user/login", { id, password })
  let status
  if (response.status === 200) {
    status = 200
  } else {
    status = response.status
  }
  return [response, status]
}

export default loginAPI
