import BtnComponent from "./UI/BtnComponent"
import { Link } from "react-router-dom"

function App() {
  return (
    <div>
      <h1>app.js</h1>
      <Link to="/lot" style={{ textDecoration: "none" }}>
        <BtnComponent> 로또 컴포넌트 </BtnComponent>
      </Link>
      <Link to="/login" style={{ textDecoration: "none" }}>
        <BtnComponent> 로그인 컴포넌트 </BtnComponent>
      </Link>
    </div>
  )
}

export default App
