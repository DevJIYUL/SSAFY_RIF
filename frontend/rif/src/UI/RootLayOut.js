import { Outlet, useNavigate, useLocation } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { navigationActions } from "../store/navigationSlice";
import useWindowDimensions from "../Components/useWindowDimensions";
import BigWindowComponent from "../Components/BigWindowComponent";

const RootLayOut = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();
  const { width } = useWindowDimensions();
  const [navigationHidden, setNavigationHidden] = useState(false);

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

  useEffect(() => {
    if (width >= 480 || width <= 350) {
      setNavigationHidden(true);
    } else {
      setNavigationHidden(false);
    }
  }, [width, setNavigationHidden]);

  return (
    <div>
      {navigationHidden ? (
        <p>
          <BigWindowComponent width={width} />
        </p>
      ) : (
        <main style={{ marginBottom: "70px" }}>
          <Outlet />
        </main>
      )}
      <NavigationComponent hidden={navigationHidden} />
    </div>
  );
};

export default RootLayOut;
