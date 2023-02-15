import LogItemComponent from "../Components/LogItemComponent";
import { useSelector, useDispatch } from "react-redux";
import { useRef, useCallback, useState, useEffect } from "react";
import useLogGetAPI from "../API/getLogAPI";
import { useNavigate } from "react-router-dom";

const LogPageComponent = () => {
  const navigate = useDispatch();
  const dispatch = useNavigate();

  const accessToken = useSelector((state) => state.auth.authentication.token);
  const memberId = useSelector((state) => state.auth.authentication.id);

  const [pageNumber, setPageNumber] = useState(0);

  const { loading, error, logs, hasMore, newToken, statusCode } = useLogGetAPI(
    accessToken,
    memberId,
    pageNumber,
    10
  );

  useEffect(() => {
    setPageNumber(0);
  }, [accessToken, newToken, dispatch, statusCode, navigate]);

  const observer = useRef();
  const lastLogComponentRef = useCallback(
    (node) => {
      if (loading) return;
      if (observer.current) {
        observer.current.disconnect();
      }
      observer.current = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && hasMore) {
          setPageNumber((prevPageNumber) => prevPageNumber + 1);
        }
      });
      if (node) observer.current.observe(node);
    },
    [loading, hasMore]
  );

  return (
    <div>
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
            );
          }
          return <LogItemComponent key={logIndex} log={log} id={logIndex} />;
        })}
      <h1 style={{ textAlign: "center", color: "#5D5E58" }}>
        {loading && "loading..."}
      </h1>
      <h1 style={{ textAlign: "center", color: "#5D5E58" }}>
        {error && "error"}
      </h1>
      <h1 style={{ textAlign: "center", color: "#5D5E58" }}>
        {!hasMore && "마지막 페이지입니다."}
      </h1>
    </div>
  );
};

export default LogPageComponent;
