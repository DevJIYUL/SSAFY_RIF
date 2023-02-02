import { Outlet } from "react-router-dom";
import NavigationComponent from "./NavigationComponent";

const RootLayOut = () => {
  return (
    <>
      <main>
        <Outlet />
      </main>
      <NavigationComponent />
    </>
  );
};

export default RootLayOut;
