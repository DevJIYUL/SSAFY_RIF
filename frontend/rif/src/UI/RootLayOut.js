import { Outlet, useNavigate, useLocation } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { navigationActions } from "../store/navigationSlice";

const RootLayOut = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  useEffect(() => {
    if (location.pathname === "/") {
      navigate("/home");
    } else if (location.pathname.endsWith("/")) {
      navigate(location.pathname.slice(0, -1));
    } else {
      if (
        location.pathname !== "/login" &&
        location.pathname !== "/login/" &&
        location.pathname !== "/main"
      )
        dispatch(navigationActions.setPastHistory(location.pathname));
    }
  }, [navigate, location, dispatch]);

  return (
    <>
      <main style={{ marginBottom: "70px" }}>
        <Outlet />
      </main>
      <NavigationComponent />
    </>
  );
};

export default RootLayOut;
