import AuthContainer from "../../components/AuthContainer";
import {Button, TextField} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import React from "react";

const Login = () => {
  const classes = useStyles();

  return (
    <AuthContainer
      title="Logowanie"
      caption={
        <span>
          Nie masz konta? <Link to="/register">Zarejestruj się</Link>
        </span>
      }
    >
      <TextField
        label="Adres email"
        variant="outlined"
        className={classes.input}
      />
      <TextField
        type="password"
        label="Hasło"
        variant="outlined"
        className={classes.input}
      />
      <Button
        variant="contained"
        color="primary"
        className={classes.submitButton}
        type="submit"
      >
        Zaloguj
      </Button>
    </AuthContainer>
  );
};

const useStyles = makeStyles({
  input: {
    margin: 10
  },
  formRow: {
    display: "flex"
  },
  submitButton: {
    marginTop: 15
  }
});

export default Login;
