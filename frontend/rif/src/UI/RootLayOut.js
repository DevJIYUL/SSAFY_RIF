import { Outlet } from "react-router-dom";
import NavigationBar from "./NavigationBar";

const RootLayOut = () => {
  return (
    <>
      <main>
        <Outlet />
      </main>
      <NavigationBar />
    </>
  );
};

export default RootLayOut;
