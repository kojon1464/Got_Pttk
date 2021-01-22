import {makeStyles} from "@material-ui/core/styles";
import Register from "./screens/Register/Register";
import React, {useState, useEffect} from "react";
import Manage from "./screens/Manage/Manage";
import Login from "./screens/Login/Login";
import {
  BrowserRouter as Router,
  Redirect,
  Switch,
  Route
} from "react-router-dom";
import NavBar from "./components/NavBar";

function App() {
  const classes = useStyles();

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  useEffect(() => {
    if (localStorage.getItem("auth")) {
      setIsLoggedIn(true);
    }
  }, []);

  const logout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem("auth");
  };

  return (
    <Router>
      {isLoggedIn && <NavBar onLogoutRequest={logout} />}
      <div className={classes.root}>
        <Switch>
          <Route path="/manage">
            {isLoggedIn ? <Manage /> : <Redirect to="/login" />}
          </Route>
          <Route path="/register">
            {!isLoggedIn ? <Register /> : <Redirect to="/manage" />}
          </Route>
          <Route path="/">
            {!isLoggedIn ? (
              <Login onLoginSuccess={() => setIsLoggedIn(true)} />
            ) : (
              <Redirect to="/manage" />
            )}
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

const useStyles = makeStyles({
  root: {
    display: "flex",
    justifyContent: "center"
  }
});

export default App;
