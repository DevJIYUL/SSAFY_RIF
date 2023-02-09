import axios from "axios";

/** axiosInterface is using axios module.
 * It's just help to fetch easly axios's argument.
 * baseurl is fixed. So if you want to change this, you have to change in axiosInterface.
 * method and url are required. And their type is String.
 * data, headers and params, their type is object, they work like data in axios.
 * If axios returned right response, it return common response.
 * If axios raised an error, it return axios error. If you want to get response, you can use key named 'response'.
 */
export default async function axiosInterface(
  method,
  url,
  data = {},
  headers = {},
  params = {}
) {
  let response = await axios({
    method: method,
    url: url,
    // baseURL: "https://5324c034-b46a-4387-a193-7865dc3869b2.mock.pstmn.io",
    baseURL: "http://i8a501.p.ssafy.io:8080/",
    data: data,
    headers: headers,
    params: params,
  })
    .then((res) => res)
    .catch((err) => err);

  return response;
}
