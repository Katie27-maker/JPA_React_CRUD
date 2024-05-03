import { Routes, Route } from "react-router-dom";
import Login from "./page/Login";
import SignUp from "./page/SignUp";
import { createTheme } from "@mui/material/styles";
import { blue } from "@mui/material/colors";

function App() {
  const theme = createTheme({
    palette: {
      primary: {
        light: blue[300],
        main: blue[500],
        dark: blue[700],
        darker: blue[900],
      },
    },
  });

  return (
    <div>
      {console.log("App 실행")}
      <Routes>
        <Route path="/Login" element={<Login />}></Route>
        <Route path="/SignUp" element={<SignUp />}></Route>
      </Routes>
    </div>
  );
}

export default App;
