import AuthContainer from "../../components/AuthContainer";
import {Button, TextField} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import React from "react";

const Register = () => {
  const classes = useStyles();

  return (
    <AuthContainer
      title="Rejestracja"
      caption={
        <span>
          Masz już konto? <Link to="/">Zaloguj się</Link>
        </span>
      }
    >
      <TextField
        label="Adres email"
        variant="outlined"
        className={classes.input}
      />
      <div className={classes.formRow}>
        <TextField
          label="Imię"
          fullWidth
          variant="outlined"
          className={classes.input}
        />
        <TextField
          label="Nazwisko"
          fullWidth
          variant="outlined"
          className={classes.input}
        />
      </div>
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
        Zarejestruj
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

export default Register;
