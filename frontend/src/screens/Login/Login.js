import {Button, TextField, Typography} from "@material-ui/core";
import AuthContainer from "../../components/AuthContainer";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import React, {useState} from "react";
import * as api from "../../api";

const Login = ({onLoginSuccess}) => {
  const classes = useStyles();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isError, setIsError] = useState(false);

  const handleLoginSubmit = () =>
    api
      .login(email, password)
      .then(response => {
        localStorage.setItem("auth", response.data.token);
        setIsError(false);
        onLoginSuccess();
      })
      .catch(() => setIsError(true));

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
        value={email}
        onChange={event => setEmail(event.target.value)}
      />
      <TextField
        type="password"
        label="Hasło"
        variant="outlined"
        className={classes.input}
        value={password}
        onChange={event => setPassword(event.target.value)}
      />
      <Button
        variant="contained"
        color="primary"
        className={classes.submitButton}
        type="submit"
        onClick={handleLoginSubmit}
      >
        Zaloguj
      </Button>
      {isError && (
        <Typography className={classes.error} color="error">
          Nieprawidłowy email lub hasło
        </Typography>
      )}
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
  },
  error: {
    marginTop: 15
  }
});

export default Login;
