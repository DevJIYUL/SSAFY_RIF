import LogItemComponent from "../Components/LogItemComponent"
import { useSelector } from "react-redux"
import { useRef, useCallback, useState, useEffect } from "react"
import useLogGetAPI from "../API/getLogAPI"

const LogPageComponent = () => {
  const accessToken = useSelector((state) => state.auth.authentication.token)
  const [pageNumber, setPageNumber] = useState(1)
  const { loading, error, logs, hasMore } = useLogGetAPI(
    accessToken,
    pageNumber,
    "sortOption"
  )

  useEffect(() => {
    setPageNumber(1)
  }, [accessToken])

  const observer = useRef()
  const lastLogComponentRef = useCallback(
    (node) => {
      if (loading) return
      if (observer.current) {
        observer.current.disconnect()
      }
      observer.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && hasMore) {
          setPageNumber((prevPageNumber) => prevPageNumber + 1)
        }
      })
      if (node) observer.current.observe(node)
    },
    [loading, hasMore]
  )

  return (
    <div>
      <h1> Log Page Component </h1>
      <span> "access Token" : {accessToken} </span>
      <div> {logs.length}</div>
      {logs &&
        logs.map((log, logIndex) => {
          if (logs.length === logIndex + 1) {
            return (
              <LogItemComponent
                key={logIndex}
                log={log}
                id={logIndex}
                innerRef={lastLogComponentRef}
              />
            )
          }
          return <LogItemComponent key={logIndex} log={log} id={logIndex} />
        })}
      <div> {loading && "loading..."}</div>
      <div> {error && "error"}</div>
      <div> {!hasMore && "Done !"}</div>
    </div>
  )
}

export default LogPageComponent
