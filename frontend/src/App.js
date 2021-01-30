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
import Badges from "./screens/Badges/Badges";
import Trip from "./screens/Trip/Trip";

function App() {
  const classes = useStyles();

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    if (localStorage.getItem("auth")) {
      setIsLoggedIn(true);
    }
    setIsLoading(false);
  }, []);

  const logout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem("auth");
  };

  return (
    <Router>
      {(isLoggedIn || isLoading) && <NavBar onLogoutRequest={logout} />}
      <div className={classes.root}>
        <Switch>
          <Route path="/manage">
            {isLoggedIn || isLoading ? <Manage /> : <Redirect to="/login" />}
          </Route>
          <Route path="/badges">
            {isLoggedIn || isLoading ? <Badges /> : <Redirect to="/login" />}
          </Route>
          <Route path="/trip">
            {isLoggedIn || isLoading ? <Trip /> : <Redirect to="/login" />}
          </Route>
          <Route path="/register">
            {!isLoggedIn || isLoading ? (
              <Register />
            ) : (
              <Redirect to="/manage" />
            )}
          </Route>
          <Route path="/">
            {!isLoggedIn || isLoading ? (
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
