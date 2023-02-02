import axiosInterface from "./axiosInterface"
import { useEffect, useState } from "react"

async function getLogAPI(accessToken, pageNumber, sortOption) {
  const response = await axiosInterface(
    "GET",
    "api/user/log",
    {},
    {
      Authorization: `Baerer ${accessToken}`,
    },
    {
      page: pageNumber,
      sort: sortOption,
    }
  )
  return response
}

export default function useLogGetAPI(accessToken, pageNumber, sortOption) {
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(false)
  const [logs, setLogs] = useState([])
  const [hasMore, setHasMore] = useState(true)

  useEffect(() => {
    setLoading(true)
    setError(false)

    const response = getLogAPI(accessToken, pageNumber, sortOption)
    response
      .then((res) => {
        const newLogs = res.data.logs // array of Objects [{...},{...},{...}]
        if (newLogs.length) {
          // 새로운 로그가 아직 있다면
          setLogs((prevLogs) => {
            const totalLogs = [...prevLogs, ...newLogs]
            return totalLogs
          })
          setLoading(false)
        } else {
          // 새로운 로그가 없다면
          setHasMore(false)
          setLoading(false)
        }
      })
      .catch((err) => {
        setError(true)
      })
  }, [accessToken, pageNumber, sortOption])

  return { loading, error, logs, hasMore }
}
