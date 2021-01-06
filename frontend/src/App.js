import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import {makeStyles} from "@material-ui/core/styles";
import Register from "./screens/Register/Register";
import Login from "./screens/Login/Login";
import React from "react";

function App() {
  const classes = useStyles();

  return (
    <Router>
      <div className={classes.root}>
        <div className={classes.container}>
          <Switch>
            <Route path="/register">
              <Register />
            </Route>
            <Route path="/">
              <Login />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

const useStyles = makeStyles({
  root: {
    display: "flex",
    justifyContent: "center"
  },
  container: {
    width: "100%",
    maxWidth: 1000
  }
});

export default App;
